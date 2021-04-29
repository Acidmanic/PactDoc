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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class PdfElementJsonParserMachine {

    HashMap<String, Font> fonts;

    public PdfElementJsonParserMachine() {

        fonts = new HashMap<>();

        Font defaultFont = new Font(Palettes.FONT_JSON);

        defaultFont.setColor(BaseColor.WHITE);

        fonts.put(InObjectSymbolsState.class.getName(), defaultFont);

        Font rawValueFont = new Font(defaultFont);

        rawValueFont.setColor(BaseColor.ORANGE);

        fonts.put(InRawValueState.class.getName(), rawValueFont);

        Font stringFont = new Font(defaultFont);

//        stringFont.setColor(new BaseColor(47, 175, 0));
        stringFont.setColor(new BaseColor(70, 144, 203));

        fonts.put(InStrings.class.getName(), stringFont);

    }

    private class Indent {

        private final String unit;
        private int indent = 0;

        public Indent(String unit) {
            this.unit = unit;
        }

        public void in() {
            this.indent += 1;
        }

        public void out() {
            if (this.indent > 0) {
                this.indent -= 1;
            }
        }

        public StringBuilder append(StringBuilder sb) {

            for (int i = 0; i < this.indent; i++) {

                sb.append(this.unit);
            }
            return sb;
        }

    }

    public Element parse(String json) {

        char[] chars = json.toCharArray();

        JsonStringState state = new InObjectSymbolsState();

        StringBuilder sb = new StringBuilder();

        List<Element> elements = new ArrayList<>();

        Indent indent = new Indent("   ");

        for (char c : chars) {

            JsonStringState newState = state.whatsNext(c);

            if (state.getClass().equals(InObjectSymbolsState.class)
                    || newState.getClass().equals(InObjectSymbolsState.class)) {
                if (c == '}') {

                    indent.out();

                    indent.append(sb).append("\n");
                }
            }

            if (newState.getClass() != state.getClass()) {

                if (stateKeepsChars(state.getClass())) {

                    sb.append(c);

                    deliver(sb.toString(), state, elements);

                    sb = new StringBuilder();

                } else {

                    deliver(sb.toString(), state, elements);

                    sb = new StringBuilder();

                    sb.append(c);
                }

                state = newState;
            } else {
                sb.append(c);
            }

            if (state.getClass().equals(InObjectSymbolsState.class)
                    || newState.getClass().equals(InObjectSymbolsState.class)) {

                if (c == '{') {
                    indent.in();

                    sb.append("\n");

                    indent.append(sb);
                }
                if (c == ',') {

                    sb.append("\n");

                    indent.append(sb);
                }
            }

        }
        sb.append("\n");
        
        deliver(sb.toString(), state, elements);

        PdfPTable table = new PdfPTable(1);

        

        Paragraph p = new Paragraph();
        
        elements.forEach(e -> p.add(e));
        
        PdfPCell cell = new PdfPCell(p);

        cell.setBackgroundColor(new BaseColor(29, 31, 33));

        cell.setBorderWidth(1);

        cell.setBorderColor(BaseColor.BLACK);
        
        
        table.addCell(cell);

        return table;
    }

    private void deliver(String text, JsonStringState state, List<Element> result) {

        Font font = this.fonts.get(state.getClass().getName());

        Chunk chunk = new Chunk(text, font);

        result.add(chunk);
    }

    private boolean stateKeepsChars(Class type) {
        return type.getName().equals(InStrings.class.getName());
    }
}
