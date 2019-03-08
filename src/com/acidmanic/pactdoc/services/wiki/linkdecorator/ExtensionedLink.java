/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.services.wiki.linkdecorator;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class ExtensionedLink implements FileSystemLink{
    
    private final FileSystemLink origin;
    private final String extension;
    
    public ExtensionedLink(FileSystemLink origin,String extension) {
        this.origin = origin;
        this.extension = extension;
    }
    
    @Override
    public String represent() {
        String ext ="";
        if(this.extension!=null&&this.extension.length()>0){
            ext="."+extension;
        }
        
        return origin.represent()+ext;
    }
}
