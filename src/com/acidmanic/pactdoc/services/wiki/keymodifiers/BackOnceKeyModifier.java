/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.services.wiki.keymodifiers;

import com.acidmanic.pactdoc.utility.ContentKeyHelper;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class BackOnceKeyModifier implements KeyModifier{

    private final KeyModifier origin;

    public BackOnceKeyModifier(KeyModifier origin) {
        this.origin = origin;
    }
    
    @Override
    public String[] getKey() {
        return ContentKeyHelper.backOnce(this.origin.getKey());
    }

    @Override
    public String[] getOriginalKey() {
        return this.origin.getOriginalKey();
    }
    
}
