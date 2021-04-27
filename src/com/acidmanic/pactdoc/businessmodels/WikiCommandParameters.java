/* 
 * The MIT License
 *
 * Copyright 2019 Mani Moayedi (acidmanic.moayedi@gmail.com).
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
package com.acidmanic.pactdoc.businessmodels;

import com.acidmanic.pactdoc.contractverification.ContractVerifier;
import com.acidmanic.pactdoc.contractverification.DefaultContractVerifier;
import com.acidmanic.pactdoc.wiki.WebWikiFormatBuilder;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class WikiCommandParameters {

    private String pactsRoot;

    private String outputDirectory;

    private String documentsSubDirectory;

    private String repository;

    private String username;

    private String password;

    private String remote;

    private boolean linksWithExtensions;

    private String wikiFormat;

    private boolean rootRelativeLinks;

    private boolean singleDirectory;

    private String singleDirectoryDelimiter;

    private final WebWikiFormatBuilder webWikiFormatBuilder;

    private ContractVerifier contractVerifier;

    public WikiCommandParameters() {
        this.pactsRoot = ".";
        this.outputDirectory = "wiki";
        this.documentsSubDirectory = "";
        this.linksWithExtensions = true;
        this.remote = "origin";
        this.rootRelativeLinks = false;
        this.singleDirectory = false;
        this.singleDirectoryDelimiter = " ";
        this.webWikiFormatBuilder = new WebWikiFormatBuilder();
        this.contractVerifier = new DefaultContractVerifier();
    }

    public String getPactsRoot() {
        return pactsRoot;
    }

    public void setPactsRoot(String pactsRoot) {
        this.pactsRoot = pactsRoot;
    }

    public String getOutputDirectory() {
        return outputDirectory;
    }

    public void setOutputDirectory(String outputDirectory) {
        this.outputDirectory = outputDirectory;
    }

    public String getDocumentsSubDirectory() {
        return documentsSubDirectory;
    }

    public void setDocumentsSubDirectory(String documentsSubDirectory) {
        this.documentsSubDirectory = documentsSubDirectory;
    }

    public boolean isLinksWithExtensions() {
        return linksWithExtensions;
    }

    public void setLinksWithExtensions(boolean linksWithExtensions) {
        this.linksWithExtensions = linksWithExtensions;
    }

    public String getRepository() {
        return repository;
    }

    public void setRepository(String repository) {
        this.repository = repository;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean hasValidUserPass() {
        if (this.username == null || this.username.length() == 0) {
            return false;
        }
        if (this.password == null || this.password.length() == 0) {
            return false;
        }
        return true;
    }

    public String getRemote() {
        return remote;
    }

    public void setRemote(String remote) {
        this.remote = remote;
    }

    public WebWikiFormatBuilder getWebWikiFormatBuilder() {
        return webWikiFormatBuilder;
    }

    public String getWikiFormat() {
        return wikiFormat;
    }

    public void setWikiFormat(String wikiFormat) {
        this.wikiFormat = wikiFormat;
    }

    public boolean isRootRelativeLinks() {
        return rootRelativeLinks;
    }

    public void setRootRelativeLinks(boolean rootRelativeLinks) {
        this.rootRelativeLinks = rootRelativeLinks;
    }

    public boolean isSingleDirectory() {
        return singleDirectory;
    }

    public void setSingleDirectory(boolean singleDirectory) {
        this.singleDirectory = singleDirectory;
    }

    public String getResolvedWikiPath() {
        Path path = Paths.get(this.outputDirectory);
        if (this.documentsSubDirectory != null && this.documentsSubDirectory.length() > 0) {
            path = path.resolve(this.documentsSubDirectory);
        }
        return path.normalize().toString();
    }

    public String getSingleDirectoryDelimiter() {
        return singleDirectoryDelimiter;
    }

    public void setSingleDirectoryDelimiter(String singleDirectoryDelimiter) {
        this.singleDirectoryDelimiter = singleDirectoryDelimiter;
    }

    public ContractVerifier getContractVerifier() {
        return contractVerifier;
    }

    public void setContractVerifier(ContractVerifier contractVerifier) {
        this.contractVerifier = contractVerifier;
    }

}
