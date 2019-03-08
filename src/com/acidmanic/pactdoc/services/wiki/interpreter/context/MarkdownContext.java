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
public class MarkdownContext implements PageContext{

    private final StringBuilder mainContent;
    
    private StringBuilder currentContent;

    private StringBuilder linkContent;
    
    private String linkSrc;
    
    public MarkdownContext() {
    
        this.mainContent  = new StringBuilder();
        
        this.currentContent = this.mainContent;
    }
    
    

    @Override
    public PageContext title(String text) {
        
        currentContent.append("##").append(text).append("\n");
        
        return this;
    }

    @Override
    public PageContext paragraph(String text) {
        
        currentContent.append("\n").append(text).append("\n");
        
        return this;
    }

        
    @Override
    public PageContext table(HashMap<String, String> table) {
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

    @Override
    public PageContext table(String leftHeader, String rightHeader, HashMap<String, String> table) {
        currentContent.append("|").append(leftHeader).append("|").append(rightHeader).append("|\n");
        currentContent.append("|:------|:------------|\n");
        return this.table(table);
    }

    @Override
    public PageContext newLine() {
        this.currentContent.append("\n\n");
        return this;
    }


    @Override
    public PageContext json(String json) {
        this.currentContent.append("```json\n").
                append(new TextReformater().pritifyJson(json))
                .append("\n```\n");
        return this;
    }

    @Override
    public String output() {
        return currentContent.toString();
    }

    @Override
    public PageContext link(Link link) {
        appendLink(link.getSrc(),link.getCaption());
        return this;
    }

    @Override
    public PageContext append(String text) {
        this.currentContent.append(text);
        return this;
    }

    @Override
    public PageContext openBold() {
        currentContent.append("__");
        return this;
    }

    @Override
    public PageContext closeBold() {
        currentContent.append("__");
        return this;
    }

    @Override
    public PageContext openItalic() {
        currentContent.append("_");
        return this;
    }

    @Override
    public PageContext closeItalic() {
        currentContent.append("_");
        return this;
    }

    @Override
    public PageContext openLink(String src) {
        this.linkContent = new StringBuilder();
        this.currentContent = this.linkContent;
        this.linkSrc=src;
        return this;
    }

    @Override
    public PageContext closeLink() {
        
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

    @Override
    public PageContext horizontalLine() {
        this.currentContent.append("___________________\n");
        return this;
    }
    
    
    
}
