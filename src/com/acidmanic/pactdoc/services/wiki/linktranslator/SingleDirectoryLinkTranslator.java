/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.services.wiki.linktranslator;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class SingleDirectoryLinkTranslator implements LinkTranslator{
    
    
    private final String extension;
    private final boolean addExtension;
    private final String delimiter;

    public SingleDirectoryLinkTranslator(String extension, boolean addExtension, String delimiter) {
        this.extension = extension;
        this.addExtension = addExtension;
        this.delimiter = delimiter;
    }
    
    
    @Override
    public String translate(String[] contentKey) {
        StringBuilder sb = new StringBuilder();
        
        String sep="";
        
        for(String key:contentKey){
            sb.append(sep).append(key);
            sep=this.delimiter;
        }
        
        if(this.addExtension){
            sb.append(".").append(extension);
        }
        return sb.toString();
    }
}
