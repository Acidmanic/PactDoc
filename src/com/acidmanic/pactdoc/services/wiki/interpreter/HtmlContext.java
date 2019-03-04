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

    private final String ENDTAG = "\"</div></body></html>\"";
    public HtmlContext() {  
        sb= new StringBuilder();
        
        sb.append("<html>").append("<head>")
                .append("<link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css\" integrity=\"sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS\" crossorigin=\"anonymous\">\n" +
"<script src=\"https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js\" integrity=\"sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k\" crossorigin=\"anonymous\"></script>")
                .append("<style>")
                .append("table{margin:10px}")
                .append("</style>")
                .append("</head><body>");
        sb.append("<nav class=\"navbar navbar-default\"> </div>")
                .append("<div class=\"container-fluid\">")
                .append("<div class=\"navbar-header\">")
                .append("</div></div></nav>");
        
        sb.append("<div class=\"container\">");
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
        sb.append("<table class=\"table table-bordered col-lg-4\">");
        
        for(String key:table.keySet()){
            appendRow(sb,key,table.get(key));
        }
        sb.append("</table></row>");
        return this;
    }

    @Override
    public PageContext table(String leftHeader, String rightHeader, HashMap<String, String> table) {
        sb.append("<table class=\"table table-bordered col-lg-4\">");
        sb.append("<thead class=\"thead-light\">");
        appendTags(sb, "th", new String[]{leftHeader,rightHeader});
        sb.append("</thead><tbody>");
        for(String key:table.keySet()){
            appendRow(sb,key,table.get(key));
        }
        sb.append("</tbody></table>");
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

    
    private boolean endsWith(String value){
        
        int count = value.length();
        int charsAtSb = sb.length();
        
        if(count>charsAtSb){
            return false;
        }
        
        int firstChar = charsAtSb-count;
        
        for(int i=0;i<count;i++){
            if(value.charAt(i)!=sb.charAt(i+firstChar)){
                return false;
            }
        }
        
        return true;
    }
    
    @Override
    public String output() {
        if(!endsWith(ENDTAG)){
            sb.append(ENDTAG);
        }
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

    private StringBuilder appendTags(StringBuilder sb, String tag,String[] values){
        for(String value:values){
            appendTag(sb, tag, value);
        }
        return sb;
    }
    
    private StringBuilder appendTag(StringBuilder sb, String tag,String value){
        return sb.append("<").append(tag).append(">")
                .append(value).append("</").append(tag).append(">");
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

    @Override
    public PageContext horizontalLine() {
        sb.append("<hr>");
        return this;
    }
}
