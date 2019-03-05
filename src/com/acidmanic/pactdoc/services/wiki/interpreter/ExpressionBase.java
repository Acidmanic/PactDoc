/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.services.wiki.interpreter;

import com.acidmanic.pactdoc.models.Contract;
import com.acidmanic.pactdoc.services.contractindexing.ContractIndexer;
import com.acidmanic.pactdoc.services.wiki.contentproviders.Link;
import com.acidmanic.pactdoc.services.wiki.glossary.Glossary;
import java.util.List;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public abstract class ExpressionBase {
    
    private final String[] currentKey;
    private final List<Link> links;
    private final ContractIndexer indexer;
    private final Glossary glossary;
    private final Contract currentContract;

    public ExpressionBase(String[] currentKey, 
            List<Link> links, 
            ContractIndexer indexer, 
            Glossary glossary,
            Contract currentContract) {
        this.currentKey = currentKey;
        this.links = links;
        this.indexer = indexer;
        this.glossary = glossary;
        this.currentContract = currentContract;
    }

    public ExpressionBase(ExpressionBase base){
        this.currentContract =base.currentContract;
        this.currentKey = base.currentKey;
        this.links = base.links;
        this.glossary=base.glossary;
        this.indexer = base.indexer;
    }
    
    protected String[] getCurrentKey() {
        return currentKey;
    }

    protected List<Link> getLinks() {
        return links;
    }

    protected ContractIndexer getIndexer() {
        return indexer;
    }

    protected Glossary getGlossary() {
        return glossary;
    }

    protected Contract getCurrentContract() {
        return currentContract;
    }
    
    
    
    
    public abstract void interpret(PageContext context);
    
    
    
    
}
