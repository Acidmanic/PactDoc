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
package com.acidmanic.pactdoc.dcoumentstructure.renderers.pagecontexts;

import com.acidmanic.pactdoc.dcoumentstructure.renderers.PageContext;
import com.acidmanic.pactdoc.services.wiki.interpreter.contexts.MarkdownPageBuilder;
import java.util.HashMap;

/**
 *
 * @author diego
 */
public class MarkdownContext implements PageContext<String> {

    private MarkdownPageBuilder markdownPageBuilder;

    public MarkdownContext() {
        this.markdownPageBuilder = new MarkdownPageBuilder();
    }

    @Override
    public PageContext title(String text) {

        this.markdownPageBuilder.title(text);

        return this;
    }

    @Override
    public PageContext subTitle(String text) {
        this.markdownPageBuilder.subTitle(text);

        return this;
    }

    @Override
    public PageContext paragraph(String text) {

        this.markdownPageBuilder.paragraph(text);

        return this;
    }

    @Override
    public PageContext table(HashMap<String, String> table) {

        this.markdownPageBuilder.table(table);

        return this;
    }

    @Override
    public PageContext table(String leftHeader, String rightHeader, HashMap<String, String> table) {

        this.markdownPageBuilder.table(leftHeader, rightHeader, table);

        return this;
    }

    @Override
    public PageContext openBold() {

        this.markdownPageBuilder.openBold();

        return this;
    }

    @Override
    public PageContext closeBold() {

        this.markdownPageBuilder.closeBold();

        return this;
    }

    @Override
    public PageContext openItalic() {

        this.markdownPageBuilder.openItalic();

        return this;
    }

    @Override
    public PageContext closeItalic() {

        this.markdownPageBuilder.closeItalic();

        return this;
    }

    @Override
    public PageContext append(String text) {

        this.markdownPageBuilder.append(text);

        return this;
    }

    @Override
    public PageContext newLine() {

        this.markdownPageBuilder.newLine();

        return this;
    }

    @Override
    public PageContext openLink(String reference) {

        this.markdownPageBuilder.openLink(reference);

        return this;
    }

    @Override
    public PageContext closeLink() {

        this.markdownPageBuilder.closeLink();

        return this;
    }

    @Override
    public PageContext json(String json) {

        this.markdownPageBuilder.json(json);

        return this;
    }

    @Override
    public String output() {
        return this.markdownPageBuilder.output();
    }

    @Override
    public PageContext horizontalLine() {

        this.markdownPageBuilder.newLine().horizontalLine();

        return this;
    }

    @Override
    public PageContext badge(String text) {

        this.markdownPageBuilder
                .openBold()
                .openCode()
                .append(text)
                .closeCode()
                .closeBold();
        return this;
    }

}
