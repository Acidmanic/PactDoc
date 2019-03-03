/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.services.pages;

import com.acidmanic.pactdoc.services.contentproviders.Link;
import com.acidmanic.pactdoc.utility.TextReformater;
import java.util.HashMap;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class MarkdownContext implements PageContext{

    
    private final StringBuilder sb;

    public MarkdownContext() {
    
        this.sb  = new StringBuilder();
    }
    
    

    @Override
    public PageContext title(String text) {
        
        sb.append("##").append(text).append("\n");
        
        return this;
    }

    @Override
    public PageContext paragraph(String text) {
        
        sb.append("\n").append(text).append("\n");
        
        return this;
    }

        
    @Override
    public PageContext table(HashMap<String, String> table) {
        for(String key:table.keySet()){
            String value = table.get(key);
            sb.append("|")
                    .append(key)
                    .append("|")
                    .append(value)
                    .append("|\n");
        }
        return this;
    }

    @Override
    public PageContext table(String leftHeader, String rightHeader, HashMap<String, String> table) {
        sb.append("|").append(leftHeader).append("|").append(rightHeader).append("|\n");
        sb.append("|:------|:------------|\n");
        return this.table(table);
    }

    @Override
    public PageContext newLine() {
        this.sb.append("\n\n");
        return this;
    }


    @Override
    public PageContext json(String json) {
        this.sb.append("```json\n").
                append(new TextReformater().pritifyJson(json))
                .append("\n```\n");
        return this;
    }

    @Override
    public String output() {
        return sb.toString();
    }

    @Override
    public PageContext link(Link link) {
        this.sb.append("[**").append(link.getCaption())
                .append("**](").append(link.getSrc()).append(")");
        return this;
    }

    @Override
    public PageContext append(String text) {
        this.sb.append(text);
        return this;
    }

    @Override
    public PageContext openBold() {
        sb.append("__");
        return this;
    }

    @Override
    public PageContext closeBold() {
        sb.append("__");
        return this;
    }

    @Override
    public PageContext openItalic() {
        sb.append("_");
        return this;
    }

    @Override
    public PageContext closeItalic() {
        sb.append("_");
        return this;
    }

    
    
    
}
