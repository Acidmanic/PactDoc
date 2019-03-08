/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.services.wiki.interpreter.context;

import com.acidmanic.pactdoc.services.wiki.contentproviders.Link;
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

    public MarkdownPageBuilder link(Link link) {
        appendLink(link.getSrc(),link.getCaption());
        return this;
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

    private void appendLink(String src,String caption){
        this.currentContent.append("[").append(caption)
                .append("](").append(src).append(")");
    }

    public MarkdownPageBuilder horizontalLine() {
        this.currentContent.append("___________________\n");
        return this;
    }
}
