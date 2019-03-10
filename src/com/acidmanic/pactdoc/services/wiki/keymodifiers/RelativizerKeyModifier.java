/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.services.wiki.keymodifiers;

import com.acidmanic.pactdoc.services.contractindexing.ContentKeyHelper;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class RelativizerKeyModifier implements KeyModifier{
    
    private final KeyModifier origin;
    private final String[] base;

    public RelativizerKeyModifier(KeyModifier origin,String[] base) {
        this.origin = origin;
        this.base = base;
    }
    
    @Override
    public String[] getKey() {
        return ContentKeyHelper.relateTo(this.origin.getKey(),base);
    }

    @Override
    public String[] getOriginalKey() {
        return origin.getOriginalKey();
    }
    
}
