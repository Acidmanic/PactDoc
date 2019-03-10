/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.services.wiki.interpreter.contexts;

import com.acidmanic.pactdoc.services.contractindexing.ContractIndexer;
import com.acidmanic.pactdoc.services.wiki.keymodifiers.BasicKeyModifier;
import com.acidmanic.pactdoc.services.wiki.keymodifiers.KeyModifier;
import com.acidmanic.pactdoc.services.wiki.linktranslator.LinkTranslator;


/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public abstract class WikiContextBase implements WikiContext{
    
    private final String output;
    private final ContractIndexer indexer;
    private final String apiBase ;
    public WikiContextBase(String output,
            ContractIndexer indexer,
            String apiBase) {
        this.output = output;
        this.indexer = indexer;
        this.apiBase = apiBase;
    }

    protected ContractIndexer getIndexer(){
        return indexer;
    }
    
    protected String getOutput() {
        return output;
    }
    
    protected String getApiBase(){
        return apiBase;
    }
    
    protected KeyModifier decorateModifier(KeyModifier keyModifier){
        return keyModifier;
    }
    
    protected abstract LinkTranslator getTranslator();

    protected String makeLinkFor(String[] contentkey) {
        
        KeyModifier modifier = new BasicKeyModifier(contentkey);
        
        decorateModifier(modifier);
        
        return getTranslator().translate(modifier.getKey());
    }
    
}
