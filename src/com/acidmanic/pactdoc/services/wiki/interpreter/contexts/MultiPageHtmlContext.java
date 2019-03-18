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

import com.acidmanic.pactdoc.services.contractindexing.ContractIndexer;
import com.acidmanic.pactdoc.utility.IOHelper;
import com.acidmanic.pactdoc.utility.TextReformater;
import com.acidmanic.pactdoc.utility.jsonparsing.ClearerJsonParserMachine;
import com.acidmanic.pactdoc.utility.jsonparsing.HtmlWrapperJsonParserMachine;
import java.util.HashMap;
/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class MultiPageHtmlContext extends HierarchicalWikiContext{

    private StringBuilder sb;
    
    private final String ENDTAG = "</div></body></html>";

    public MultiPageHtmlContext(boolean referrerRelativeLinking,
            boolean linkWithExtensions,
            String output,
            ContractIndexer indexer,
            String apiBase,
            boolean singleDirectory,
            String singleDirectoryDelimiter) {
        super(referrerRelativeLinking, 
                linkWithExtensions,"html",
                output,indexer,apiBase,
                singleDirectory,singleDirectoryDelimiter);
        
        this.sb = new StringBuilder();
    }
    
    @Override
    protected void initializeContextForNewPage(){
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
    public WikiContext title(String text) {
        sb.append("<h1>").append(text).append("</h1>");
        return this;
    }
    
    @Override
    public WikiContext subTitle(String text) {
        sb.append("<h3>").append(text).append("</h3>");
        return this;
    }

    @Override
    public WikiContext paragraph(String text) {
        sb.append("<div>").append(text).append("</div>");
        return this;
    }

    @Override
    public WikiContext table(HashMap<String, String> table) {
        sb.append("<table class=\"table table-bordered col-lg-4\">");
        
        for(String key:table.keySet()){
            appendRow(sb,key,table.get(key));
        }
        sb.append("</table></row>");
        return this;
    }

    @Override
    public WikiContext table(String leftHeader, String rightHeader, HashMap<String, String> table) {
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
    public WikiContext openBold() {
        sb.append("<b>");
        return this;
    }

    @Override
    public WikiContext closeBold() {
        sb.append("</b>");
        return this;
    }

    @Override
    public WikiContext openItalic() {
         sb.append("<i>");
        return this;
    }

    @Override
    public WikiContext closeItalic() {
         sb.append("</i>");
        return this;
    }

    @Override
    public WikiContext append(String text) {
        sb.append(htmlEncode(text));
        return this;
    }

    
    @Override
    public WikiContext newLine() {
        sb.append("<br>");
        return this;
    }

    @Override
    public WikiContext json(String json) {
        
        json = new ClearerJsonParserMachine().parse(json);
        
        HtmlWrapperJsonParserMachine wrapper = new HtmlWrapperJsonParserMachine();
        
        wrapper.setRawValueClass("text-info");
        
        wrapper.setStringClass("text-danger");
        
        json =  wrapper.parse(json);
  
        json = new TextReformater().pritifyJson(json, true);
        
        sb.append("<pre><code>").append(json).append("</code></pre>");
        
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
    public WikiContext openLink(String[] contentKey) {
        String src=getInternalLinkFor(contentKey);
        sb.append("<a href=\"").append(src)
                .append("\" >");
        return this;
    }

    @Override
    public WikiContext closeLink() {
        sb.append("</a>");
        return this;
    }

    @Override
    public WikiContext horizontalLine() {
        sb.append("<hr>");
        return this;
    }
    
    @Override
    protected void deliverThisPage(String[] currentPageKey){
        
            if(!endsWith(ENDTAG)){
            
                sb.append(ENDTAG);
            }
            
            String content = sb.toString();
            
            String path = getPageWriteLinkFor(currentPageKey);
            
            IOHelper.ensureParents(path);
            
            IOHelper.writeAllText(path, content);
    }

   
}
