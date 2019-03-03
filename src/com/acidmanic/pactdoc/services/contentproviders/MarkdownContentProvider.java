/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.services.contentproviders;

import com.acidmanic.pactdoc.services.extendableindexing.ContractIndexer;
import com.acidmanic.pactdoc.services.pages.MarkdownContext;
import java.util.List;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class MarkdownContentProvider extends ContentProviderBase{

    public MarkdownContentProvider(ContractIndexer indexer) {
        super(indexer);
    }


    @Override
    protected String createIndexPage(List<Link> links) {

        return new IndexPageGenerator(new MarkdownContext())
                .generate(links);
    }


    
}
