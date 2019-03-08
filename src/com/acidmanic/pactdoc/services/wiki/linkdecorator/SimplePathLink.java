/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.services.wiki.linkdecorator;

import com.acidmanic.pactdoc.services.contractindexing.ContentKeyHelper;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class SimplePathLink implements FileSystemLink{
    
    private final String[] contentKey;

    public SimplePathLink(String[] contentKey) {
        this.contentKey = contentKey;
    }

    @Override
    public String represent() {
        
        return ContentKeyHelper.getPath(contentKey).normalize().toString();
    }


    
}
