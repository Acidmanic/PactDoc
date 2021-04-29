/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.dcoumentstructure.pagestores;

import com.acidmanic.document.structure.Key;
import com.acidmanic.pactdoc.dcoumentstructure.PageStore;
import com.acidmanic.pactdoc.dcoumentstructure.renderers.pagecontexts.PdfPage;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileOutputStream;

/**
 *
 * @author diego
 */
public class PdfPageStore implements PageStore<PdfPage> {

    private final File outputFile;
    private Document document;

    public PdfPageStore(File outputFile) {
        this.outputFile = outputFile;
    }

    @Override
    public void initialize() {
        this.document = new Document();

        File parentDirectory = this.outputFile.toPath().getRoot().toFile();

        parentDirectory.mkdirs();

        if (this.outputFile.exists()) {

            this.outputFile.delete();
        }

        try {
            PdfWriter.getInstance(document, new FileOutputStream(this.outputFile));

        } catch (Exception e) {
        }

        document.open();
    }

    @Override
    public void save(Key key, PdfPage pageContent) {

        try {

            document.newPage();
            
            Chunk pageAnchor = new Chunk("   ");

            pageAnchor.setLocalDestination(key.uniqueHash());

            document.add(pageAnchor);
            
            for(Element e : pageContent.getElements()){
                
                document.add(e);
            }

        } catch (Exception e) {
        }
    }

    @Override
    public String translate(Key referrer, Key target) {

        return target.uniqueHash();
    }
    
    public void finish(){
    
        this.document.close();
    }

}
