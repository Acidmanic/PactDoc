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
public class FileNameLink implements FileSystemLink{
    
    private final Link origin;

    public FileNameLink(Link original) {
        this.origin = original;
    }
    
    

    //TODO: this is supposed to be char delimited
    @Override
    public String represent() {
        return "ApiHome";
    }

    
}
