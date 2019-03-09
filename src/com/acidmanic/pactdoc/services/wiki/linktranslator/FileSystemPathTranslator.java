/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.services.wiki.linktranslator;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class FileSystemPathTranslator implements LinkTranslator{

    private final String extension;
    private final boolean addExtension;

    public FileSystemPathTranslator(String extension, boolean addExtension) {
        this.extension = extension;
        this.addExtension = addExtension;
    }
    
    
    
    
    
    @Override
    public String translate(String[] contentKey) {
        
        Path path = Paths.get("");
        
        for(String key:contentKey){
            path=path.resolve(key);
        }
        
        String ret = path.toString();
        
        if(this.addExtension){
            ret += "."+this.extension;
        }
        
        return ret;
    }
    
}
