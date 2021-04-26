/*
 * The MIT License
 *
 * Copyright 2021 diego.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.acidmanic.pactdoc.wiki;

import com.acidmanic.pactdoc.dcoumentstructure.PageStore;
import com.acidmanic.pactdoc.dcoumentstructure.pagestores.FilesystemPageStore;
import com.acidmanic.pactdoc.dcoumentstructure.renderers.PageContextProvider;
import com.acidmanic.pactdoc.dcoumentstructure.renderers.pagecontexts.HtmlContext;
import com.acidmanic.pactdoc.dcoumentstructure.renderers.pagecontexts.MarkdownContext;
import com.acidmanic.pactdoc.wiki.format.WikiFormat;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author diego
 */
public class WebWikiFormatBuilder {

    private PageContextProvider contextProvider;
    private FilesystemPageStore pageStore;

    private String filesExtension;
    private String flatFileSystemDelimiter;
    private boolean flatFileSystem;
    private boolean relativiseLinks;
    private boolean extensionPresentInLinks;
    private Path outputDirectory;
    private Path subDirectory;
    private String homeDefaultFileName;

    public WebWikiFormatBuilder() {

        this.defaults();
    }

    public WebWikiFormatBuilder defaults() {
        this.filesExtension = ".html";
        this.flatFileSystem = false;
        this.flatFileSystemDelimiter = "-";
        this.relativiseLinks = true;
        this.outputDirectory = new File(".").toPath().toAbsolutePath().normalize();
        this.subDirectory = Paths.get("apis");
        this.homeDefaultFileName = "index";
        this.extensionPresentInLinks = true;
        return this;
    }

    public WebWikiFormatBuilder formatHtml() {
        this.filesExtension = ".html";
        this.contextProvider = () -> new HtmlContext();
        this.extensionPresentInLinks = true;
        return this;
    }

    public WebWikiFormatBuilder formatMarkdown() {
        this.filesExtension = ".md";
        this.contextProvider = () -> new MarkdownContext();

        return this;
    }

    public WebWikiFormatBuilder format(String formatName) {

        if ("markdown".equals(formatName.toLowerCase())) {

            this.formatMarkdown();
        } else {

            this.formatHtml();
        }
        return this;
    }

    /**
     * Causes generated wiki to have all its file at output directory along side
     * each other
     *
     * @return
     */
    public WebWikiFormatBuilder flatFileSystem() {

        this.flatFileSystem = true;

        return this;
    }

    /**
     * Causes generated wiki to have its files placed hiererchically regarding
     * links structure.
     *
     * @return
     */
    public WebWikiFormatBuilder hierarchical() {

        this.flatFileSystem = true;

        return this;
    }

    public WebWikiFormatBuilder flatFileSystem(boolean flat) {

        this.flatFileSystem = flat;

        return this;
    }

    public WebWikiFormatBuilder flatFileSystem(boolean flat, String delimiter) {

        this.flatFileSystem = flat;

        this.flatFileSystemDelimiter = delimiter;

        return this;
    }

    public WebWikiFormatBuilder relativisedLinks() {

        this.relativiseLinks = true;

        return this;
    }

    public WebWikiFormatBuilder relativisedLinks(boolean relativize) {

        this.relativiseLinks = relativize;

        return this;
    }

    public WebWikiFormatBuilder absoluteLinks() {

        this.relativiseLinks = false;

        return this;
    }

    public WebWikiFormatBuilder outputDirectory(Path directory) {

        this.outputDirectory = directory;

        return this;
    }

    public WebWikiFormatBuilder subDirectory(Path directory) {

        this.subDirectory = directory;

        return this;
    }

    public WebWikiFormatBuilder defaultRootFileName(String defaultName) {

        this.homeDefaultFileName = defaultName;

        return this;
    }

    public WebWikiFormatBuilder defaultRootFileIndex() {

        this.homeDefaultFileName = "index";

        return this;
    }

    public WebWikiFormatBuilder gitlab() {

        this.filesExtension = ".md";
        this.flatFileSystem = false;
        this.flatFileSystemDelimiter = "-";
        this.relativiseLinks = false;
        this.outputDirectory = Paths.get(".");
        this.subDirectory = Paths.get("apis");
        this.homeDefaultFileName = "index";
        this.extensionPresentInLinks = false;
        this.contextProvider = () -> new MarkdownContext();

        return this;
    }

    public WikiFormat build() {

        this.pageStore = new FilesystemPageStore(
                this.filesExtension,
                this.outputDirectory.toAbsolutePath().normalize(),
                this.flatFileSystem,
                this.flatFileSystemDelimiter,
                this.relativiseLinks,
                this.extensionPresentInLinks,
                this.homeDefaultFileName,
                this.subDirectory);

        WikiFormat format = new WikiFormat() {

            @Override
            public PageContextProvider getContextProvider() {
                return contextProvider;
            }

            @Override
            public PageStore getPageStore() {
                return pageStore;
            }

            @Override
            public String getName() {
                return "Generic Web Wiki";
            }
        };
        return format;
    }

}
