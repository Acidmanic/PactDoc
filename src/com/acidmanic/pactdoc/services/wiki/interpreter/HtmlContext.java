/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.services.wiki.interpreter;

import com.acidmanic.pactdoc.services.wiki.contentproviders.Link;
import com.acidmanic.pactdoc.utility.TextReformater;
import java.util.HashMap;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class HtmlContext implements PageContext{

    StringBuilder sb;

    public HtmlContext() {  
        sb= new StringBuilder();
    }
    
    @Override
    public PageContext title(String text) {
        sb.append("<h1>").append(text).append("</h1>");
        return this;
    }

    @Override
    public PageContext paragraph(String text) {
        sb.append("<div>").append(text).append("</div>");
        return this;
    }

    @Override
    public PageContext table(HashMap<String, String> table) {
        sb.append("<table>");
        
        for(String key:table.keySet()){
            appendRow(sb,key,table.get(key));
        }
        sb.append("</table>");
        return this;
    }

    @Override
    public PageContext table(String leftHeader, String rightHeader, HashMap<String, String> table) {
        sb.append("<table>");
        appendRow(sb,leftHeader,rightHeader);
        for(String key:table.keySet()){
            appendRow(sb,key,table.get(key));
        }
        sb.append("</table>");
        return this;
    }

    @Override
    public PageContext openBold() {
        sb.append("<b>");
        return this;
    }

    @Override
    public PageContext closeBold() {
        sb.append("</b>");
        return this;
    }

    @Override
    public PageContext openItalic() {
         sb.append("<i>");
        return this;
    }

    @Override
    public PageContext closeItalic() {
         sb.append("</i>");
        return this;
    }

    @Override
    public PageContext append(String text) {
        sb.append(htmlEncode(text));
        return this;
    }

    @Override
    public PageContext link(Link link) {
        sb.append("<a href=\"")
                .append(link.getSrc())
                .append("\">")
                .append(link.getCaption())
                .append("</a>");
        return this;
    }

    @Override
    public PageContext newLine() {
        sb.append("<br>");
        return this;
    }

    @Override
    public PageContext json(String json) {
        sb.append("<pre><code>").append(new TextReformater().pritifyJson(json))
                .append("</code></pre>");
        return this;
    }

    @Override
    public String output() {
        return sb.toString();
    }

    private String htmlEncode(String text) {
        String[] search = {"&", "\"", "<", ">"};
        String[] replace = {"&amp;", "&quot;", "&lt;", "&gt;"};
        
        int count = search.length;
        
        for(int i=0;i<count;i++){
            text=text.replaceAll(search[i], replace[i]);
        }
        
        return text;
    }

    private void appendRow(StringBuilder sb, String left, String right) {
        sb.append("<tr><td>")
                .append(left).append("</td><td>")
                .append(right).append("</td></tr>");
    }

    @Override
    public PageContext openLink(String src) {
        sb.append("<a href=\"").append(src)
                .append("\" >");
        return this;
    }

    @Override
    public PageContext closeLink() {
        sb.append("</a>");
        return this;
    }
}
