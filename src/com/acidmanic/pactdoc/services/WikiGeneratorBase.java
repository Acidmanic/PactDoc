/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.services;

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
    
    
    
    
    protected abstract Glossary createGlossary();
    
    protected abstract boolean linksContainFileExtensions();
    
    protected abstract ContentProvider getContentProvider();
    
    public void generate(String destinationDirectory) {
        Glossary glossary = createGlossary();
        
        final String extension = this.linksContainFileExtensions()?".md":"";
        
        final Path baseDirectory = Paths.get(destinationDirectory);
        
        glossary.scan(new GlossaryScanner() {
            @Override
            public void scan(String link, Object contentKey) {
                String content = getContentProvider().provideContentFor(contentKey, glossary);
                
                writeFile(baseDirectory.resolve(link+extension)
                        .toString(),content);
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
}
