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
package com.acidmanic.pactdoc.utility;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class MarkdownPageBuilder {

    private final StringBuilder mainContent;

    private StringBuilder currentContent;

    private StringBuilder linkContent;

    private String linkSrc;

    public MarkdownPageBuilder() {

        this.mainContent = new StringBuilder();

        this.currentContent = this.mainContent;
    }

    public MarkdownPageBuilder closeTitle() {

        currentContent.append("\n")
                .append("====").append("\n");

        return this;
    }

    public MarkdownPageBuilder openTitle() {

        currentContent.append("\n");

        return this;
    }

    public MarkdownPageBuilder openSubtitle() {

        currentContent.append("\n");

        return this;
    }

    public MarkdownPageBuilder closeSubtitle() {
        currentContent.append("\n")
                .append("----").append("\n");

        return this;
    }

    public MarkdownPageBuilder paragraph(String text) {

        currentContent.append("\n").append(text).append("\n");

        return this;
    }

    public MarkdownPageBuilder newLine() {
        this.currentContent.append("\n\n");
        return this;
    }

    public MarkdownPageBuilder json(String json) {
        this.currentContent.append("```json\n").
                append(new TextReformater().pritifyJson(json))
                .append("\n```\n");
        return this;
    }

    public String output() {
        return currentContent.toString();
    }

    public MarkdownPageBuilder append(String text) {
        this.currentContent.append(text);
        return this;
    }

    public MarkdownPageBuilder openBold() {
        currentContent.append("__");
        return this;
    }

    public MarkdownPageBuilder closeBold() {
        currentContent.append("__");
        return this;
    }

    public MarkdownPageBuilder openItalic() {
        currentContent.append("_");
        return this;
    }

    public MarkdownPageBuilder closeItalic() {
        currentContent.append("_");
        return this;
    }

    public MarkdownPageBuilder openCode() {
        currentContent.append("```");
        return this;
    }

    public MarkdownPageBuilder openCode(String language) {
        currentContent.append("```" + language);
        return this;
    }

    public MarkdownPageBuilder closeCode() {
        currentContent.append("```");
        return this;
    }

    public MarkdownPageBuilder openLink(String src) {
        this.linkContent = new StringBuilder();
        this.currentContent = this.linkContent;
        this.linkSrc = src;
        return this;
    }

    public MarkdownPageBuilder closeLink() {

        this.currentContent = this.mainContent;

        this.currentContent.append("[")
                .append(this.linkContent)
                .append("](")
                .append(linkSrc)
                .append(")");

        return this;
    }

    public MarkdownPageBuilder horizontalLine() {
        this.currentContent.append("___________________\n");
        return this;
    }

    public MarkdownPageBuilder openList() {

        this.currentContent.append("\n");

        return this;
    }

    public MarkdownPageBuilder closeList() {

        return this;
    }

    public MarkdownPageBuilder openListItem() {
        this.currentContent.append("  * ");

        return this;
    }

    public MarkdownPageBuilder closeListItem() {

        this.currentContent.append("\n");

        return this;
    }

    public MarkdownPageBuilder image(String url) {
        this.currentContent.append("![Image](")
                .append(url)
                .append(")");

        return this;
    }

    public MarkdownPageBuilder table(HashMap<String, String> table) {
        
        return table(" "," ",table);
    }

    public MarkdownPageBuilder table(String leftHeader, String rightHeader, HashMap<String, String> table) {
        currentContent.append("\n\n").append("|").append(leftHeader).append("|").append(rightHeader).append("|\n");
        currentContent.append("|:------|:------------|\n");
        for (String key : table.keySet()) {
            String value = table.get(key);
            currentContent.append("|")
                    .append(key)
                    .append("|")
                    .append(value)
                    .append("|\n");
        }
        return this;
    }

    private int tableColumns = 0;
    private int putTableColumns = 0;
    private boolean isAtTableCell = false;

    public MarkdownPageBuilder openTable(int columns) {

        ArrayList<String> headers = new ArrayList<>();

        for (int i = 0; i < columns; i++) {
            headers.add(" ");
        }

        return this.openTable(headers);
    }

    public MarkdownPageBuilder openTable(Collection<String> headers) {

        this.tableColumns = headers.size();

        currentContent.append("\n\n|");

        headers.forEach(header -> currentContent.append(header).append("|"));

        currentContent.append("\n").append("|");

        headers.forEach(header -> currentContent.append(":--------").append("|"));

        currentContent.append("\n");

        return this;

    }

    public MarkdownPageBuilder openTableRow() {

        this.putTableColumns = 0;

        currentContent.append("|");

        return this;
    }

    public MarkdownPageBuilder closeTableRow() {

        for (int i = this.putTableColumns; i < this.tableColumns; i++) {

            this.currentContent.append("|");
        }

        currentContent.append("\n");

        return this;
    }

    public MarkdownPageBuilder closeTable() {
        return this;
    }

    public MarkdownPageBuilder openCell() {
        
        this.isAtTableCell = true;
        
        this.putTableColumns +=1;
        
        return this;
    }

    public MarkdownPageBuilder closeCell() {
        
        this.isAtTableCell = false;
        
        this.currentContent.append("|");
        
        return this;
    }
}
