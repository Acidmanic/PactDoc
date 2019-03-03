/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.services.wikiformat;

import com.acidmanic.pactdoc.services.contentproviders.PageContext;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class WikiFormat {
    
    private PageContext context;
    
    private String filesExtension;

    public WikiFormat(PageContext context, String filesExtension) {
        this.context = context;
        this.filesExtension = filesExtension;
    }
    
    
    
    

    public WikiFormat() {
    }

    public PageContext getContext() {
        return context;
    }

    public void setContext(PageContext context) {
        this.context = context;
    }

    public String getFilesExtension() {
        return filesExtension;
    }

    public void setFilesExtension(String filesExtension) {
        this.filesExtension = filesExtension;
    }
    
    
}
