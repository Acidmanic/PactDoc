/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.dcoumentstructure.renderers.pagecontexts.pdfcontext;

import com.itextpdf.text.Font;

/**
 *
 * @author diego
 */
public class Palette {

    private Font font;
    private int fontStyle;
    private int deltaItalic;
    private int deltaBold;

    public Palette(Font font) {
        this.font = font;
        this.fontStyle = font.getStyle();
        deltaItalic = 0;
    }

    public Palette() {
    }

    public Font getFont() {
        return font;
    }

    public void setFont(Font font) {
        this.font = font;
        this.fontStyle = font.getStyle();
    }

    public void setBold() {

        if (!font.isBold()) {

            int style = font.getStyle();

            style += Font.BOLD;

            font.setStyle(style);

            deltaBold = Font.BOLD;
        }

    }

    public void setItalic() {

        if (!font.isItalic()) {

            int style = font.getStyle();

            style += Font.ITALIC;

            font.setStyle(style);

            deltaItalic += Font.ITALIC;
        }
    }

    public void resetBold() {

        if (this.deltaBold > 0) {

            int style = font.getStyle();

            style -= this.deltaBold;

            font.setStyle(style);

            this.deltaBold = 0;
        }
    }

    public void resetItalic() {

        if (this.deltaItalic > 0) {

            int style = font.getStyle();

            style -= this.deltaItalic;

            font.setStyle(style);

            this.deltaItalic = 0;
        }
    }

    public void resetFont() {
        
        font.setSize(fontStyle);

        this.deltaBold = 0;

        this.deltaItalic = 0;
    }
    
    
    @Override
    @SuppressWarnings({"CloneDeclaresCloneNotSupported", "CloneDoesntCallSuperClone"})
    public Palette clone(){
        
        Palette clone = new Palette(font);
        
        clone.font.setStyle(fontStyle);
        
        return new Palette(font);
    }
}
