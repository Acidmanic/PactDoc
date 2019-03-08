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
public class ExtensionedLink implements Link{
    
    private final FileSystemLink origin;
    private final String extension;
    
    public ExtensionedLink(FileSystemLink origin,String extension) {
        this.origin = origin;
        if(extension!=null&&extension.length()>0){
            this.extension="."+extension;
        }else{
            this.extension="";
        }
    }

    @Override
    public void reBase(Link base) {
        origin.reBase(base);
    }

    @Override
    public String represent() {
        return origin.represent()+this.extension;
    }

    @Override
    public String[] getContentKey() {
        return this.origin.getContentKey();
    }
    
}
