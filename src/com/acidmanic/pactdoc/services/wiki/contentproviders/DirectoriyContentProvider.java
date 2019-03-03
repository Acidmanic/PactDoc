/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.services.wiki.contentproviders;

import com.acidmanic.pactdoc.services.wiki.interpreter.ContractExpression;
import com.acidmanic.pactdoc.services.wiki.interpreter.PageContext;
import com.acidmanic.pactdoc.services.wiki.interpreter.IndexExpression;
import com.acidmanic.pactdoc.models.Contract;
import com.acidmanic.pactdoc.services.wiki.glossary.Glossary;
import static com.acidmanic.pactdoc.services.contractindexing.ContentKeyHelper.append;
import com.acidmanic.pactdoc.services.contractindexing.ContractIndexer;
import com.acidmanic.pactdoc.services.contractindexing.IndexHelper;
import com.acidmanic.pactdoc.services.wiki.wikiformat.WikiFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class DirectoriyContentProvider implements ContentProvider{

    private final ContractIndexer indexer;
    private final WikiFormat wikiFormat;
    private final IndexHelper indexHelper;
    
    public DirectoriyContentProvider(ContractIndexer indexer,  WikiFormat wikiFormat) {
        this.indexer = indexer;
        this.indexHelper = new IndexHelper(indexer);
        this.wikiFormat = wikiFormat;
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
        PageContext context = wikiFormat.makeContext();
        IndexExpression exp = new IndexExpression(context);
        exp.interpret(links);
        return context.output();
    }
    
    
    protected String createContractPage(Contract contract){
        PageContext context = wikiFormat.makeContext();
            ContractExpression exp = new ContractExpression(context);
            exp.interpret(contract);
            
            return context.output();
        
    }
    
    
    
}
