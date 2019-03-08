/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.services.wiki.interpreter.context;

import com.acidmanic.pactdoc.services.contractindexing.ContractIndexer;
import com.acidmanic.pactdoc.services.wiki.linkdecorator.BaseTrimmerLink;
import com.acidmanic.pactdoc.services.wiki.linkdecorator.FileSystemLink;
import com.acidmanic.pactdoc.services.wiki.linkdecorator.SimplePathLink;
import com.acidmanic.pactdoc.services.wiki.linkdecorator.Link;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public abstract class WikiContextBase implements WikiContext{
    
    private final String output;
    private final ContractIndexer indexer;
    
    public WikiContextBase(String output,ContractIndexer indexer) {
        this.output = output;
        this.indexer = indexer;
    }

    protected ContractIndexer getIndexer(){
        return indexer;
    }
    
    public String getOutput() {
        return output;
    }
    
    protected Link makeLinkFor(String[] contentKey){
        return decorateLink(new SimplePathLink(contentKey)); 
    }
    
    protected Link makeReferrerBasedLinkFor(String[] referrerKey,
            String[] targetKey){
        
        Link ret = new SimplePathLink(targetKey);
        
        
        
        ret = decorateLink(ret);
        
        ret = new BaseTrimmerLink( (FileSystemLink) ret, referrerKey);
        
        return ret;
    }
    
    protected abstract Link decorateLink(Link link);
    
    
}
