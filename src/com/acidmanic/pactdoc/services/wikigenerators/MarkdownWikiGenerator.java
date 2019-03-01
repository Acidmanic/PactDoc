/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.services.wikigenerators;

import com.acidmanic.pactdoc.services.contentproviders.MarkdownContentProvider;
import com.acidmanic.pactdoc.services.contentproviders.ContentProvider;
import com.acidmanic.pactdoc.services.ContractIndexer;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class MarkdownWikiGenerator extends WikiGeneratorBase{

    public MarkdownWikiGenerator(ContractIndexer indexer, String linksBase,boolean linksEndWithFileExtionsion) {
        super(linksEndWithFileExtionsion, indexer, linksBase);
    }
    

    public MarkdownWikiGenerator(ContractIndexer indexer, String linksBase) {
        super(false, indexer, linksBase);
    }
    


    @Override
    protected ContentProvider getContentProvider() {
        return new MarkdownContentProvider(getIndexer());
    }


    
    
}
