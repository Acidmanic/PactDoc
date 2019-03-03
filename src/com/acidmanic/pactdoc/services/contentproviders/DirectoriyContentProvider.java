/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.services.contentproviders;

import com.acidmanic.pactdoc.models.Contract;
import com.acidmanic.pactdoc.services.pages.ContractExpression;
import com.acidmanic.pactdoc.services.Glossary;
import static com.acidmanic.pactdoc.services.extendableindexing.ContentKeyHelper.append;
import com.acidmanic.pactdoc.services.extendableindexing.ContractIndexer;
import com.acidmanic.pactdoc.services.extendableindexing.IndexHelper;
import com.acidmanic.pactdoc.services.pages.IndexExpression;
import com.acidmanic.pactdoc.services.pages.PageContext;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class DirectoriyContentProvider implements ContentProvider{

    private final ContractIndexer indexer;
    private final IndexHelper indexHelper;
    private final Class<? extends PageContext> contextClass;
    
    private Glossary glossary;
    
    public DirectoriyContentProvider(ContractIndexer indexer, Class<? extends PageContext> contextClass) {
        this.indexer = indexer;
        this.indexHelper = new IndexHelper(indexer);
        this.contextClass = contextClass;
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

    
    protected String createIndexPage(List<Link> links){
        try {
            
            PageContext context = contextClass.newInstance();
            IndexExpression exp = new IndexExpression(context);
            exp.interpret(links);
            
            return context.output();
        } catch (Exception e) {}
        return CONTENT_NOT_FOUND;
    }
    
    
    protected String createContractPage(Contract contract){
        try {
            
            PageContext context = contextClass.newInstance();
            ContractExpression exp = new ContractExpression(context);
            exp.interpret(contract);
            
            return context.output();
        } catch (Exception e) {}
        return CONTENT_NOT_FOUND;
        
    }
    
    
    
}
