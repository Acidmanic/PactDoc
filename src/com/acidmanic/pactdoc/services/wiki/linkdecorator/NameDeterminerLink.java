/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.services.wiki.linkdecorator;

import com.acidmanic.pactdoc.services.contractindexing.ContractIndexer;
import com.acidmanic.pactdoc.services.contractindexing.IndexHelper;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class NameDeterminerLink implements Link{
    
    private final Link origin;
    private final IndexHelper indexHelper;
    
    public NameDeterminerLink(Link origin,ContractIndexer indexer) {
        this.origin = origin;
        this.indexHelper = new IndexHelper(indexer);
    }
    
    @Override
    public void trimBase(Link base) {
        origin.trimBase(base);
    }

    @Override
    public void baseOn(Link base) {
        origin.baseOn(base);
    }

    @Override
    public String represent() {
        if(!indexHelper.isLeaf(origin.getContentKey())){
            origin.append("Index");
        }
        return origin.represent();
    }

    @Override
    public String[] getContentKey() {
        return origin.getContentKey();
    }
    
    @Override
    public void append(String... appending) {
        origin.append(appending);
    }
    
}
