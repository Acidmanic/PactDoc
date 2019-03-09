/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.services.wiki.keymodifiers;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class BasicKeyModifier implements KeyModifier{
    
    private final String[] contentKey;

    public BasicKeyModifier(String[] contentKey) {
        this.contentKey = contentKey;
    }

    @Override
    public String[] getKey() {
        return this.contentKey;
    }

    @Override
    public String[] getOriginalKey() {
        return this.contentKey;
    }
    
}
