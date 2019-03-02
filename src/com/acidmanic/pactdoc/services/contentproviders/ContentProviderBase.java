/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.services.contentproviders;

import com.acidmanic.pactdoc.models.Contract;
import com.acidmanic.pactdoc.services.Glossary;
import static com.acidmanic.pactdoc.services.extendableindexing.ContentKeyHelper.append;
import com.acidmanic.pactdoc.services.extendableindexing.ContractIndexer;
import com.acidmanic.pactdoc.services.extendableindexing.IndexHelper;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public abstract class ContentProviderBase implements ContentProvider{

    private final ContractIndexer indexer;
    private final IndexHelper indexHelper;

    private Glossary glossary;
    
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
    
    protected Glossary getGlossary(){
        return this.glossary;
    }
    
    private boolean isIndex(String[] contentKey) {
        if(!indexHelper.isLeaf(contentKey)){
            return true;
        }
        return indexer.getContract(contentKey).isEmpty();
    }
    
    
    
    @Override
    public String provideContentFor(String[] contentKey, Glossary glossary) {
        
        this.glossary = glossary;
        
        if(isIndex(contentKey)){
            
            ArrayList<Link> links = new ArrayList<>();
            
            List<String> childs = getIndexHelper().getChilds(contentKey);
            
            for(String child:childs){
                
                String[] childKey = append(contentKey, child);
                
                links.add(new Link(glossary.link(childKey),child));
            }
            
            return createIndexPage(links);
        }else{
            List<Contract> contracts = indexer.getContract(contentKey);
        
            if(!contracts.isEmpty()) {

                return createContractPage(contracts.get(0));
            }
        }
        
        return CONTENT_NOT_FOUND;
    }

    
    protected abstract String createIndexPage(List<Link> links);
    
    
    protected abstract String createContractPage(Contract contract);
    
    
    
}
