/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.dcoumentstructure.renderers.pagecontexts.pdfcontext;

import com.acidmanic.pactdoc.utility.ResourceHelper;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import java.io.File;
import java.nio.file.Path;

/**
 *
 * @author diego
 */
public class Palettes {

    private static final Font FONT_TITLE;
    private static final Font FONT_SUBTITLE;
    private static final Font FONT_REGULAR;

    public static final Font FONT_TABLE_HEADER;
    public static final Font FONT_TABLE_CONTENT;
    public static final Font FONT_BADGE;
    public static final Font FONT_JSON;

    public static final BaseColor LINK_COLOR;

    public static Palette NORMAL;

    public static Palette TITLE;

    public static Palette SUB_TITLE;

    public static Palette PARAGRAPH;

    private static void registerFont(String filename, String aliasName) {

        Path fontsDirectory = new ResourceHelper().getExecutionDirectory().resolve("fonts");

        fontsDirectory.toFile().mkdirs();

        File fontFile = fontsDirectory.resolve(filename).toFile();

        new ResourceHelper().exportLargeResource("fonts/" + filename, fontFile);

        FontFactory.register(fontFile.getAbsolutePath(), aliasName);
    }

    static {

        registerFont("Aleo-Regular.otf", "Aleo");
        
        registerFont("futura medium bt.ttf", "Futura");
        
        registerFont("CONSOLA.TTF", "Consolas");

        FONT_TITLE = FontFactory.getFont("Aleo", 28f, Font.BOLD, BaseColor.BLACK);

        FONT_SUBTITLE = FontFactory.getFont("Aleo", 18f, Font.NORMAL, BaseColor.BLACK);

        FONT_REGULAR = FontFactory.getFont("Futura", 12f, Font.NORMAL, BaseColor.BLACK);

        FONT_TABLE_HEADER = FontFactory.getFont("Aleo", 10f, Font.BOLD, BaseColor.BLACK);

        FONT_TABLE_CONTENT = FontFactory.getFont("Futura", 10f, Font.NORMAL, BaseColor.BLACK);

        FONT_BADGE = FontFactory.getFont("Futura", 10f, Font.BOLD, BaseColor.BLACK);

        FONT_JSON = FontFactory.getFont("Consolas", 10f, Font.NORMAL, BaseColor.BLACK);

        NORMAL = new Palette(FONT_REGULAR);
        
        TITLE = new Palette(FONT_TITLE);
        
        SUB_TITLE = new Palette(FONT_SUBTITLE);
        
        PARAGRAPH = new Palette(FONT_REGULAR);

        LINK_COLOR = new BaseColor(0, 148, 255);
    }

}
