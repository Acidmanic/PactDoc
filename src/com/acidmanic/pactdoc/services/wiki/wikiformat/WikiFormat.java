/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.services.wiki.wikiformat;

import com.acidmanic.pactdoc.services.wiki.interpreter.context.WikiContext;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class WikiFormat{
    
    private Class<? extends WikiContext> contextClass;
    
    private String filesExtension;
    
    private String name;

    public WikiFormat(Class<? extends WikiContext> contextClass, String filesExtension, String name) {
        this.contextClass = contextClass;
        this.filesExtension = filesExtension;
        this.name = name;
    }

    

    public WikiFormat() {
    }

    public String getFilesExtension() {
        return filesExtension;
    }

    public void setFilesExtension(String filesExtension) {
        this.filesExtension = filesExtension;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Class<? extends WikiContext> getContextClass() {
        return contextClass;
    }
    
    
}
