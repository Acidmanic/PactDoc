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
import com.acidmanic.pactdoc.utility.TextReformater;
import com.acidmanic.pactdoc.utility.jsonparsing.ClearerJsonParserMachine;
import com.acidmanic.pactdoc.utility.jsonparsing.HtmlWrapperJsonParserMachine;
import java.util.HashMap;

/**
 *
 * @author diego
 */
public class HtmlContext implements PageContext<String> {

    private StringBuilder contentBuilder;

    public HtmlContext() {

        initialize();
    }

    private void initialize() {
        contentBuilder = new StringBuilder();

        contentBuilder.append("<html>").append("<head>")
                .append("<link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css\" integrity=\"sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS\" crossorigin=\"anonymous\">\n"
                        + "<script src=\"https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js\" integrity=\"sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k\" crossorigin=\"anonymous\"></script>")
                .append("<style>")
                .append("table{margin:10px}")
                .append("</style>")
                .append("</head><body>");
        contentBuilder.append("<nav class=\"navbar navbar-default\"> </div>")
                .append("<div class=\"container-fluid\">")
                .append("<div class=\"navbar-header\">")
                .append("</div></div></nav>");

        contentBuilder.append("<div class=\"container\">");
    }

    @Override
    public PageContext openTitle() {
        this.contentBuilder.append("<h1>");

        return this;
    }

    @Override
    public PageContext closeTitle() {
        this.contentBuilder.append("</h1>");

        return this;
    }

    @Override
    public PageContext openSubtitle() {
        this.contentBuilder.append("<h3>");

        return this;
    }

    @Override
    public PageContext closeSubtitle() {
        this.contentBuilder.append("</h3>");

        return this;
    }

    @Override
    public PageContext paragraph(String text) {
        this.contentBuilder.append("<div>").append(text).append("</div>");
        return this;
    }

    @Override
    public PageContext table(HashMap<String, String> table) {
        this.contentBuilder.append("<table class=\"table table-bordered col-lg-4\">");

        for (String key : table.keySet()) {
            appendRow(this.contentBuilder, key, table.get(key));
        }
        this.contentBuilder.append("</table></row>");
        return this;
    }

    @Override
    public PageContext table(String leftHeader, String rightHeader, HashMap<String, String> table) {
        contentBuilder.append("<table class=\"table table-bordered col-lg-4\">");
        contentBuilder.append("<thead class=\"thead-light\">");
        appendTags(contentBuilder, "th", new String[]{leftHeader, rightHeader});
        contentBuilder.append("</thead><tbody>");
        for (String key : table.keySet()) {
            appendRow(contentBuilder, key, table.get(key));
        }
        contentBuilder.append("</tbody></table>");
        return this;
    }

    @Override
    public PageContext openBold() {
        this.contentBuilder.append("<b>");

        return this;
    }

    @Override
    public PageContext closeBold() {
        this.contentBuilder.append("</b>");

        return this;
    }

    @Override
    public PageContext openItalic() {
        this.contentBuilder.append("<i>");

        return this;
    }

    @Override
    public PageContext closeItalic() {
        this.contentBuilder.append("</i>");

        return this;
    }

    @Override
    public PageContext append(String text) {
        this.contentBuilder.append(htmlEncode(text));

        return this;
    }

    @Override
    public PageContext newLine() {
        this.contentBuilder.append("<br/>");

        return this;
    }

    @Override
    public PageContext openLink(String reference) {
        this.contentBuilder.append("<a href=\"")
                .append(reference)
                .append("\">");

        return this;
    }

    @Override
    public PageContext closeLink() {
        this.contentBuilder.append("</a>");

        return this;
    }

    @Override
    public PageContext json(String json) {

        json = new ClearerJsonParserMachine().parse(json);

        HtmlWrapperJsonParserMachine wrapper = new HtmlWrapperJsonParserMachine();

        wrapper.setRawValueClass("text-info");

        wrapper.setStringClass("text-danger");

        json = wrapper.parse(json);

        json = new TextReformater().pritifyJson(json, true);

        contentBuilder.append("<pre>").append(json).append("</pre>");

        return this;
    }

    @Override
    public String output() {

        return this.contentBuilder.toString();
    }

    @Override
    public PageContext horizontalLine() {
        contentBuilder.append("<hr>");
        return this;
    }

    @Override
    public PageContext badge(String text) {
        contentBuilder.append("<kbd><kbd>").append(text)
                .append("</kbd></kbd>");
        return this;
    }

    private String htmlEncode(String text) {
        String[] search = {"&", "\"", "<", ">"};
        String[] replace = {"&amp;", "&quot;", "&lt;", "&gt;"};

        int count = search.length;

        for (int i = 0; i < count; i++) {
            text = text.replaceAll(search[i], replace[i]);
        }

        return text;
    }

    private StringBuilder appendTags(StringBuilder sb, String tag, String[] values) {
        for (String value : values) {
            appendTag(sb, tag, value);
        }
        return sb;
    }

    private StringBuilder appendTag(StringBuilder sb, String tag, String value) {
        return sb.append("<").append(tag).append(">")
                .append(value).append("</").append(tag).append(">");
    }

    private void appendRow(StringBuilder sb, String left, String right) {
        sb.append("<tr><td>")
                .append(left).append("</td><td>")
                .append(right).append("</td></tr>");
    }

    @Override
    public PageContext openList() {

        this.contentBuilder.append("<ul>");

        return this;
    }

    @Override
    public PageContext closeList() {

        this.contentBuilder.append("</ul>");

        return this;
    }

    @Override
    public PageContext openListItem() {

        this.contentBuilder.append("<li>");

        return this;
    }

    @Override
    public PageContext closeListItem() {
        this.contentBuilder.append("</li>");

        return this;
    }

    @Override
    public PageContext image(String url) {
        
        this.contentBuilder.append("<img src=\"")
                .append(url)
                .append("\">");
        
        return this;
    }
}
