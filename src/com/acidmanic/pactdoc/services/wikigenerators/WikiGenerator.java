/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.services.wikigenerators;

import com.acidmanic.pactdoc.services.Glossary;
import com.acidmanic.pactdoc.services.GlossaryScanner;
import com.acidmanic.pactdoc.services.contentproviders.ContentProvider;
import com.acidmanic.pactdoc.services.contentproviders.DirectoriyContentProvider;
import com.acidmanic.pactdoc.services.extendableindexing.ContractIndexer;
import com.acidmanic.pactdoc.services.pages.PageContext;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class WikiGenerator {
    
    
    private boolean byVersion;
    private final boolean  linksEndWithFileExtionsion;
    private final ContractIndexer indexer;
    private final String linksBase;
    private final Class<? extends PageContext> contextClass;
    
    
    private final ContentProvider contentProvider;
   

    public WikiGenerator(boolean linksEndWithFileExtionsion, ContractIndexer indexer, String linksBase, Class<? extends PageContext> contextClass) {
        this.linksEndWithFileExtionsion = linksEndWithFileExtionsion;
        this.indexer = indexer;
        this.linksBase = linksBase;
        this.contextClass = contextClass;
        this.contentProvider = new DirectoriyContentProvider(indexer, contextClass);
    }

    

   
    
    public boolean getLinksEndWithFileExtension(){
        return this.linksEndWithFileExtionsion;
    }
    
    
    public void generate(String destinationDirectory) {
        
        final String fsExtension = linksEndWithFileExtionsion?"":".md";
        final String glossaryExtension = linksEndWithFileExtionsion?".md":"";
        
        Glossary glossary = new GlossaryGenerator(indexer)
                .generate(linksBase, glossaryExtension);
        
        final Path baseDirectory = Paths.get(destinationDirectory)
                .toAbsolutePath().normalize();
        
        
        glossary.scan(new GlossaryScanner() {
            @Override
            public void scan(String link, String[] contentKey) {
                String content = contentProvider.provideContentFor(contentKey, glossary);
                
                Path path = baseDirectory.resolve(link+fsExtension); 
                
                path.getParent().toFile().mkdirs();
                
                writeFile(path.toString(),content);
            }
        });
        
    }
    
    private void writeFile(String path, String content) {
        try {
            File f = new File(path);
            
            if(f.getParentFile()!=null){
                f.getParentFile().mkdirs();
            }
            
            if(f.exists()){
                f.delete();
            }
            
            
            Files.write(f.toPath(), content.getBytes(), StandardOpenOption.CREATE);
            
            
        } catch (Exception e) {
        }
    }
    
    
    public boolean isByVersion() {
        return byVersion;
    }

    public void setByVersion(boolean byVersion) {
        this.byVersion = byVersion;
    }

    public boolean isLinksEndWithFileExtionsion() {
        return linksEndWithFileExtionsion;
    }

    public ContractIndexer getIndexer() {
        return indexer;
    }

    public String getLinksBase() {
        return linksBase;
    }
    
    
    
    
    
}
