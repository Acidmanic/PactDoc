/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.services.wiki.linkdecorator;

import com.acidmanic.pactdoc.services.contractindexing.ContractIndexer;
import com.acidmanic.pactdoc.services.contractindexing.IndexHelper;
import java.nio.file.Paths;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class NameDeterminerLink extends FileSystemLink{
    
    private final FileSystemLink origin;
    private final IndexHelper indexHelper;
    private final ContractIndexer indexer;
    
    public NameDeterminerLink(FileSystemLink origin,ContractIndexer indexer) {
        this.origin = origin;
        this.indexHelper = new IndexHelper(indexer);
        this.indexer = indexer;
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
        
        String ret = this.origin.represent();
        
        if(!indexHelper.isLeaf(this.origin.getContentKey())){
            ret = Paths.get(ret).resolve("Index").toString();
        }
        return ret;
    }

    @Override
    public String[] getContentKey() {
        return origin.getContentKey();
    }
    
    @Override
    public void append(String... appending) {
        origin.append(appending);
    }

    @Override
    public Link cloneLink() {
        return new NameDeterminerLink((FileSystemLink) 
                this.origin.cloneLink(), this.indexer);
    }
    
}
