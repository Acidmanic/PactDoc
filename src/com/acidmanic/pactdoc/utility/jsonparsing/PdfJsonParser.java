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
package com.acidmanic.pactdoc.utility.jsonparsing;

import com.acidmanic.pactdoc.dcoumentstructure.renderers.pagecontexts.pdfcontext.Palettes;
import com.acidmanic.pactdoc.utility.jsonparsing.states.InObjectSymbolsState;
import com.acidmanic.pactdoc.utility.jsonparsing.states.InRawValueState;
import com.acidmanic.pactdoc.utility.jsonparsing.states.InStrings;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import java.util.HashMap;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class PdfJsonParser extends JsonPrettifierInterceptor {

    private final HashMap<String, Font> fonts;

    private final Paragraph jsonParagraph;
    private final PdfPTable jsonTable;

    public PdfJsonParser() {

        fonts = new HashMap<>();

        Font defaultFont = new Font(Palettes.FONT_JSON);

        defaultFont.setColor(BaseColor.WHITE);

        fonts.put(InObjectSymbolsState.class.getName(), defaultFont);

        Font rawValueFont = new Font(defaultFont);

        rawValueFont.setColor(BaseColor.ORANGE);

        fonts.put(InRawValueState.class.getName(), rawValueFont);

        Font stringFont = new Font(defaultFont);

        stringFont.setColor(new BaseColor(70, 144, 203));

        fonts.put(InStrings.class.getName(), stringFont);

        jsonParagraph = new Paragraph();

        PdfPCell cell = new PdfPCell(jsonParagraph);

        cell.setBackgroundColor(new BaseColor(29, 31, 33));

        cell.setBorderWidth(1);

        cell.setBorderColor(BaseColor.BLACK);

        jsonTable = new PdfPTable(1);

        jsonTable.addCell(cell);

    }

    @Override
    protected void onText(String text, JsonStringState state) {
        
        Font font = this.fonts.get(state.getClass().getName());

        Chunk chunk = new Chunk(text, font);
        
        this.jsonParagraph.add(chunk);
    }

    public Element getJsonElement(){
        return this.jsonTable;
    }
}
