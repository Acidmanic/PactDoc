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
public class ContentLink implements Link{
    
    private String[] contentKey;

    public ContentLink(String[] contentKey) {
        this.contentKey = contentKey;
    }

    @Override
    public void reBase(Link base) {
        
        String[] baseKey= base.getContentKey();
        
        int count = baseKey.length;
        
        if(contentKey.length<count){
            return;
        }
        
        for(int i=0;i<count;i++){
            if(baseKey[i].compareTo(contentKey[i])!=0){
                return;
            }
        }
        
        int remaining = contentKey.length - baseKey.length;
        
        String[] ret = new String[remaining];
        
        System.arraycopy(contentKey, baseKey.length, ret, 0, remaining);
        
        this.contentKey = ret;
    }

    @Override
    public String[] getContentKey() {
        return contentKey;
    }

    @Override
    public String represent() {
        return "";
    }
    
    
}
