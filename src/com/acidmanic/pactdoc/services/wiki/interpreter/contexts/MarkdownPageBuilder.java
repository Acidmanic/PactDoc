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
package com.acidmanic.pactdoc.services.wiki.interpreter.contexts;

import com.acidmanic.pactdoc.utility.TextReformater;
import java.util.HashMap;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class MarkdownPageBuilder{
    
    private final StringBuilder mainContent;
    
    private StringBuilder currentContent;

    private StringBuilder linkContent;
    
    private String linkSrc;
    
    public MarkdownPageBuilder() {
    
        this.mainContent  = new StringBuilder();
        
        this.currentContent = this.mainContent;
    }

    public MarkdownPageBuilder title(String text) {
        
        currentContent.append("##").append(text).append("\n");
        
        return this;
    }

    public MarkdownPageBuilder paragraph(String text) {
        
        currentContent.append("\n").append(text).append("\n");
        
        return this;
    }
        
    public MarkdownPageBuilder table(HashMap<String, String> table) {
        for(String key:table.keySet()){
            String value = table.get(key);
            currentContent.append("|")
                    .append(key)
                    .append("|")
                    .append(value)
                    .append("|\n");
        }
        return this;
    }

    public MarkdownPageBuilder table(String leftHeader, String rightHeader, HashMap<String, String> table) {
        currentContent.append("|").append(leftHeader).append("|").append(rightHeader).append("|\n");
        currentContent.append("|:------|:------------|\n");
        return this.table(table);
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

    public MarkdownPageBuilder openLink(String src) {
        this.linkContent = new StringBuilder();
        this.currentContent = this.linkContent;
        this.linkSrc=src;
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
}
