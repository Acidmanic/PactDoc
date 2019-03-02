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
public class MarkdownProvider implements PageItemsProvider{

    @Override
    public StringBuilder appendTitle(StringBuilder sb, String text) {
        return sb.append("##").append(text).append("\n");
    }

    @Override
    public StringBuilder appendParagraph(StringBuilder sb, String text) {
        return sb.append("\n").append(text).append("\n");
    }

    @Override
    public StringBuilder appendTable(StringBuilder sb, HashMap<String, String> table) {
        for(String key:table.keySet()){
            String value = table.get(key);
            sb.append("|")
                    .append(key)
                    .append("|")
                    .append(value)
                    .append("|\n");
        }
        return sb;
    }

    @Override
    public String bold(String text) {
        return "__"+text+"__";
    }

    @Override
    public String italic(String text) {
        return "_"+text+"_";
    }

    @Override
    public StringBuilder appendJson(StringBuilder sb, String json) {
        return sb.append("```json\n").
                append(new TextReformater().pritifyJson(json))
                .append("\n```\n");
    }

    @Override
    public String link(Link link) {
        return "[**" + link.getCaption() + "**]("
                + link.getSrc() + ")";
    }

    @Override
    public String newLine() {
        return "\n\n";
    }

    @Override
    public StringBuilder appendTable(StringBuilder sb, String leftHeader, String rightHeader, HashMap<String, String> table) {
        sb.append("|").append(leftHeader).append("|").append(rightHeader).append("|\n");
        sb.append("|:------|:------------|\n");
        return appendTable(sb, table);
        
    }
    
    
    
}
