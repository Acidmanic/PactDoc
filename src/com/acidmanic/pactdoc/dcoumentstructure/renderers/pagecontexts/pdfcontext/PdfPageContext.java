/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.dcoumentstructure.renderers.pagecontexts.pdfcontext;

import com.acidmanic.pactdoc.dcoumentstructure.renderers.PageContext;
import com.acidmanic.pactdoc.utility.jsonparsing.PdfJsonParser;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.draw.LineSeparator;
import java.util.ArrayList;
import java.util.Collection;
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

    private BaseColor textColor = BaseColor.BLACK;

    private static final char LIST_BULLET = '\u2022';

    public PdfPageContext() {

        paletteStack.add(Palettes.NORMAL.clone());
     
        this.addElement(new Paragraph());
    }

    @Override
    public PageContext openTitle() {

        paletteStack.push(Palettes.TITLE.clone());

        return this;
    }

    @Override
    public PageContext closeTitle() {

        this.paletteStack.pop();

        newLine();

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

        newLine();

        return this;
    }

    @Override
    public PageContext paragraph(String text) {

        Paragraph paragraph = new Paragraph(text, Palettes.NORMAL.getFont());

        addElement(paragraph);

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

        if (text != null && text.length() > 0) {

            Font font = new Font(this.paletteStack.peek().getFont());

            font.setColor(textColor);

            Chunk chunk = new Chunk(text, font);

            this.addElement(chunk);
        }
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

        this.textColor = Palettes.LINK_COLOR;

        return this;
    }

    @Override
    public PageContext closeLink() {

        this.linkInprocess = null;

        this.textColor = BaseColor.BLACK;

        return this;
    }

    @Override
    public PageContext json(String json) {

        json = "\n" + json.trim() + "\n";

        PdfJsonParser parser = new PdfJsonParser();

        parser.parse(json);

        Element jsonElement = parser.getJsonElement();

        this.addElement(jsonElement);

        return this;
    }

    @Override
    public PdfPage output() {

        return this.page;
    }

    @Override
    public PageContext horizontalLine() {

        LineSeparator separator = new LineSeparator(1.5f, 100f, BaseColor.DARK_GRAY, LineSeparator.ALIGN_JUSTIFIED, 2);

//        separator.setPercentage(59500f / 523f);
        Chunk linebreak = new Chunk(separator);

        Paragraph horizontalLine = new Paragraph(linebreak);

        this.addElement(horizontalLine);

        return this;
    }

    @Override
    public PageContext badge(String text) {

        Chunk chunk = new Chunk(text, Palettes.FONT_BADGE);

        chunk.setBackground(BaseColor.LIGHT_GRAY, 3, 3, 3, 3);

        this.addElement(chunk);

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

        newLine();

        return this;
    }

    private void addElement(Element e) {

        if (this.linkInprocess != null) {

            List<Chunk> childs = e.getChunks();

            if (linkInprocess.toLowerCase().startsWith("http://")
                    || linkInprocess.toLowerCase().startsWith("https://")) {
                childs.forEach(c -> c.setAnchor(linkInprocess));
            } else {
                if (childs != null) {
                    childs.forEach(c -> c.setLocalGoto(linkInprocess));
                }
            }
        }
        if (this.cellInProgress == null) {
            this.page.add(e);
        } else {
            this.cellInProgress.addElement(e);
        }

    }

    private void addRows(PdfPTable table, HashMap<String, String> data) {

        data.forEach((left, right) -> {
            table.addCell(new Phrase(left, Palettes.FONT_TABLE_CONTENT));
            table.addCell(new Phrase(right, Palettes.FONT_TABLE_CONTENT));
        });

    }

    @Override
    public PageContext image(String imageUrl) {

        try {

            Image image = Image.getInstance(imageUrl);

            image.scalePercent(60f);

            this.addElement(new Chunk(image, 2, -2));

            return this;
        } catch (Exception e) {
        }
        this.addElement(new Chunk("[ Image Ommited ]"));

        return this;
    }

    private PdfPTable tableInProgress = null;
    private PdfPCell cellInProgress = null;
    private int tableColumns = 0;
    private int putColumnsInCurrentRow = 0;
    private float[] columnsWidth;

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
                    header.setBorderWidth(1);
                    header.setPhrase(new Phrase(columnTitle, Palettes.FONT_TABLE_HEADER));
                    pdfTable.addCell(header);
                });

        addRows(pdfTable, data);

        this.addElement(pdfTable);

        return this;
    }

    @Override
    public PageContext openTable(int columns) {

        tableInProgress = new PdfPTable(columns);
        this.tableColumns = 0;
        this.putColumnsInCurrentRow = 0;
        this.columnsWidth = new float[columns];
        return this;
    }

   

    @Override
    public PageContext openTable(Collection<String> headers) {

        this.tableColumns = 0;
        this.putColumnsInCurrentRow = 0;
        this.columnsWidth = new float[headers.size()];

        this.tableInProgress = new PdfPTable(headers.size());

        float totalChars = 0;
        
        
        
        headers.forEach(columnTitle -> {
            PdfPCell header = new PdfPCell();
            header.setPadding(10);
            header.setBackgroundColor(BaseColor.LIGHT_GRAY);
            header.setBorderWidth(1);
            header.setPhrase(new Phrase(columnTitle, Palettes.FONT_TABLE_HEADER));
            this.tableInProgress.addCell(header);
            
        });
        
        ArrayList<String> titlesAsList = new ArrayList<>();
        titlesAsList.addAll(headers);
        
        for(String header:headers ){
            totalChars += header.length();
        }
        
        for(int i=0;i<headers.size();i++){
            
            String title = titlesAsList.get(i);
            
            this.columnsWidth[i]= (title.length())/totalChars;
        }
        
        try {
            this.tableInProgress.setWidths(this.columnsWidth);
        } catch (Exception e) {
        }

        return this;
    }

    @Override
    public PageContext openTableRow() {
        this.tableColumns = 0;
        this.putColumnsInCurrentRow = 0;
        return this;
    }

    @Override
    public PageContext closeTableRow() {

        for (int i = this.putColumnsInCurrentRow; i < this.tableColumns; i++) {
            this.tableInProgress.addCell("");
        }
        return this;
    }

    @Override
    public PageContext closeTable() {

        PdfPTable table = this.tableInProgress;

        this.tableInProgress = null;

        this.cellInProgress = null;
       
        this.addElement(table);

        return this;
    }

    @Override
    public PageContext openCell() {

        this.cellInProgress = new PdfPCell();

        this.cellInProgress.setPadding(10);

        return this;
    }

    @Override
    public PageContext closeCell() {

        this.tableInProgress.addCell(this.cellInProgress);
        
        return this;
    }

}
