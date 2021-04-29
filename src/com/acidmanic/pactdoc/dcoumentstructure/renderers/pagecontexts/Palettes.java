/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.dcoumentstructure.renderers.pagecontexts;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Font;

/**
 *
 * @author diego
 */
public class Palettes {
    
    private static final Font FONT_TITLE = new Font(Font.FontFamily.valueOf("Aleo"), 28, Font.BOLD, BaseColor.BLACK);
    private static final Font FONT_SUBTITLE = new Font(Font.FontFamily.valueOf("Aleo"), 18, Font.NORMAL, BaseColor.BLACK);
    private static final Font FONT_REGULAR = new Font(Font.FontFamily.valueOf("Futura Lt BT"), 12, Font.NORMAL, BaseColor.BLACK);
    
    
    public static final Palette NORMAL = new Palette(FONT_REGULAR);
    
    public static final Palette TITLE = new Palette(FONT_TITLE);
    
    public static final Palette SUB_TITLE = new Palette(FONT_SUBTITLE);
    
    public static final Palette PARAGRAPH = new Palette(FONT_REGULAR);
    
    
    
}
