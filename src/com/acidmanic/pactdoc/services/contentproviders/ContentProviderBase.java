/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.services.contentproviders;

import com.acidmanic.pactdoc.models.Contract;
import com.acidmanic.pactdoc.services.Glossary;
import com.acidmanic.pactdoc.services.extendableindexing.ContractIndexer;
import com.acidmanic.pactdoc.services.extendableindexing.IndexHelper;
import java.util.List;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public abstract class ContentProviderBase implements ContentProvider{

    private final ContractIndexer indexer;
    private final IndexHelper indexHelper;

    public ContentProviderBase(ContractIndexer indexer) {
        this.indexer = indexer;
        this.indexHelper = new IndexHelper(indexer);
    }

    protected ContractIndexer getIndexer() {
        return indexer;
    }

    protected IndexHelper getIndexHelper() {
        return indexHelper;
    }
    
    
    
    private boolean isIndex(String[] contentKey) {
        if(!indexHelper.isLeaf(contentKey)){
            return true;
        }
        return indexer.getContract(contentKey).isEmpty();
    }
    
    
    
    @Override
    public String provideContentFor(String[] contentKey, Glossary glossary) {
        
        if(isIndex(contentKey)){
            return createIndexPage(contentKey, glossary);
        }else{
            List<Contract> contracts = indexer.getContract(contentKey);
        
            if(!contracts.isEmpty()) {

                return createContractPage(contracts.get(0));
            }
        }
        
        return CONTENT_NOT_FOUND;
    }

    
    protected abstract String createIndexPage(String[] contentKey,Glossary glossary);
    
    
    protected abstract String createContractPage(Contract contract);
    
    
    
}
