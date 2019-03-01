/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.services.wikigenerators;

import com.acidmanic.io.file.FileSystemHelper;
import com.acidmanic.pactdoc.models.Contract;
import com.acidmanic.pactdoc.services.Glossary;
import com.acidmanic.pactdoc.services.GlossaryScanner;
import com.acidmanic.pactdoc.services.contentproviders.ContentProvider;
import com.acidmanic.pactdoc.services.extendableindexing.ContractIndexer;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public abstract class WikiGeneratorBase {
    
    
    private boolean byVersion;
    private final boolean  linksEndWithFileExtionsion;
    private final ContractIndexer indexer;
    private final String linksBase;
    
    
    protected abstract ContentProvider getContentProvider();

    public WikiGeneratorBase(boolean linksEndWithFileExtionsion, ContractIndexer indexer, String linksBase) {
        this.linksEndWithFileExtionsion = linksEndWithFileExtionsion;
        this.indexer = indexer;
        this.linksBase = linksBase;
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
        
        new FileSystemHelper().clearDirectory(baseDirectory.toString());
        
        glossary.scan(new GlossaryScanner() {
            @Override
            public void scan(String link, String[] contentKey) {
                String content = getContentProvider().provideContentFor(contentKey, glossary);
                
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
