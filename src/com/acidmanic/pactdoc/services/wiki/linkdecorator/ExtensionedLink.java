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
public class ExtensionedLink extends FileSystemLink{
    
    private final FileSystemLink origin;
    private final String extension;
    
    public ExtensionedLink(FileSystemLink origin,String extension) {
        this.origin = origin;
        this.extension = extension;
    }

    @Override
    public void trimBase(Link base) {
        origin.trimBase(base);
    }

    @Override
    public String represent() {
        String ext ="";
        if(this.extension!=null&&this.extension.length()>0){
            ext="."+extension;
        }
        
        return origin.represent()+ext;
    }

    @Override
    public String[] getContentKey() {
        return this.origin.getContentKey();
    }

    @Override
    public void baseOn(Link base) {
        this.origin.baseOn(base);
    }

    @Override
    public void append(String... appending) {
        origin.append(appending);
    }

    @Override
    public Link cloneLink() {
        return new ExtensionedLink((FileSystemLink) this.origin.cloneLink(), this.extension);
    }
    
    
}
