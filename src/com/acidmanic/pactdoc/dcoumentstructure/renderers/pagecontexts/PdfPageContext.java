/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.dcoumentstructure.renderers.pagecontexts;

import com.acidmanic.pactdoc.dcoumentstructure.renderers.PageContext;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.draw.LineSeparator;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;
import java.util.stream.Stream;

/**
 *
 * @author diego
 */
public class PdfPageContext implements PageContext<PdfPage> {

    private final PdfPage page = new PdfPage();

    private final Stack<Palette> paletteStack = new Stack<>();

    private String linkInprocess = null;

    private static final char LIST_BULLET = '\u2022';

    public PdfPageContext() {

        paletteStack.add(Palettes.NORMAL.clone());

    }

    @Override
    public PageContext openTitle() {

        paletteStack.push(Palettes.TITLE.clone());

        return this;
    }

    @Override
    public PageContext closeTitle() {

        this.paletteStack.pop();

        return this;
    }

    @Override
    public PageContext openSubtitle() {
        this.paletteStack.push(Palettes.SUB_TITLE.clone());

        return this;
    }

    @Override
    public PageContext closeSubtitle() {

        this.paletteStack.pop();

        return this;
    }

    @Override
    public PageContext paragraph(String text) {

        Paragraph paragraph = new Paragraph(text, Palettes.NORMAL.getFont());

        addElement(paragraph);

        return this;
    }

    @Override
    public PageContext table(HashMap<String, String> data) {

        PdfPTable pdfTable = new PdfPTable(2);

        addRows(pdfTable, data);

        this.addElement(pdfTable);

        return this;
    }

    @Override
    public PageContext table(String leftHeader, String rightHeader, HashMap<String, String> data) {

        PdfPTable pdfTable = new PdfPTable(2);

        Stream.of(leftHeader, rightHeader)
                .forEach(columnTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    header.setBorderWidth(2);
                    header.setPhrase(new Phrase(columnTitle));
                    pdfTable.addCell(header);
                });

        addRows(pdfTable, data);

        this.addElement(pdfTable);

        return this;
    }

    @Override
    public PageContext openBold() {

        this.paletteStack.peek().setBold();

        return this;
    }

    @Override
    public PageContext closeBold() {

        this.paletteStack.peek().resetBold();

        return this;
    }

    @Override
    public PageContext openItalic() {

        this.paletteStack.peek().setItalic();

        return this;
    }

    @Override
    public PageContext closeItalic() {

        this.paletteStack.peek().resetItalic();

        return this;
    }

    @Override
    public PageContext append(String text) {

        Chunk chunk = new Chunk(text, this.paletteStack.peek().getFont());

        this.addElement(chunk);

        return this;
    }

    @Override
    public PageContext newLine() {

        this.append("\n");

        return this;
    }

    @Override
    public PageContext openLink(String reference) {

        this.linkInprocess = reference;

        return this;
    }

    @Override
    public PageContext closeLink() {

        this.linkInprocess = null;

        return this;
    }

    @Override
    public PageContext json(String json) {
        //TODO: Improve
        append(json.toUpperCase());

        return this;
    }

    @Override
    public PdfPage output() {

        return this.page;
    }

    @Override
    public PageContext horizontalLine() {

        LineSeparator separator = new LineSeparator(3, 1.0f, BaseColor.DARK_GRAY, LineSeparator.ALIGN_JUSTIFIED, 0);

        separator.setPercentage(59500f / 523f);

        Chunk linebreak = new Chunk(separator);

        this.addElement(linebreak);

        return this;
    }

    @Override
    public PageContext badge(String text) {
//      TODO: Improve
        append(text.toUpperCase());

        return this;
    }

    @Override
    public PageContext openList() {

        return this;
    }

    @Override
    public PageContext closeList() {
        return this;
    }

    @Override
    public PageContext openListItem() {

        append("\t" + LIST_BULLET + "\t");

        return this;
    }

    @Override
    public PageContext closeListItem() {
        return this;
    }

    private void addElement(Element e) {

        if (this.linkInprocess != null) {
            List<Chunk> childs = e.getChunks();

            if (childs != null) {
                childs.forEach(c -> c.setLocalGoto(linkInprocess));
            }
        }
        this.page.add(e);
    }

    private void addRows(PdfPTable table, HashMap<String, String> data) {

        data.forEach((left, right) -> {
            table.addCell(left);
            table.addCell(right);
        });

    }

}
