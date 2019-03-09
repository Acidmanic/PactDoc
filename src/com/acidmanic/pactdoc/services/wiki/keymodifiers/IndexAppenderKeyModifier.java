/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.services.wiki.keymodifiers;

import com.acidmanic.pactdoc.services.contractindexing.ContentKeyHelper;
import com.acidmanic.pactdoc.services.contractindexing.IndexHelper;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class IndexAppenderKeyModifier implements KeyModifier{
    
    
    private final IndexHelper indexHelper;
    private final KeyModifier origin;

    public IndexAppenderKeyModifier(KeyModifier origin,IndexHelper indexHelper) {
        this.indexHelper = indexHelper;
        this.origin = origin;
    }

    @Override
    public String[] getKey() {
        if(indexHelper.isLeaf(this.origin.getOriginalKey())){
            return this.origin.getKey();
        }else{
            return ContentKeyHelper.append(this.origin.getKey(), "Index");
        }
    }

    @Override
    public String[] getOriginalKey() {
        return origin.getOriginalKey();
    }
    
}
