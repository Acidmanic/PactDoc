/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.services.contentproviders;

import com.acidmanic.pactdoc.models.Contract;
import com.acidmanic.pactdoc.services.ContractMarkDown;
import com.acidmanic.pactdoc.services.Glossary;
import static com.acidmanic.pactdoc.services.extendableindexing.ContentKeyHelper.*;
import com.acidmanic.pactdoc.services.extendableindexing.ContractIndexer;
import com.acidmanic.pactdoc.services.extendableindexing.IndexHelper;
import java.util.List;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class MarkdownContentProvider implements ContentProvider{

    private final ContractIndexer indexer;
    private final IndexHelper indexHelper;
    
    public MarkdownContentProvider(ContractIndexer indexer) {
        this.indexer = indexer;
        this.indexHelper = new IndexHelper(indexer);
    }
    
    
    
    
    @Override
    public <T> String provideContentFor(T contentKey, Glossary glossary) {
        
        if( contentKey instanceof String[]){
            return provide((String[]) contentKey,glossary);
        }
        return "";
    }
    
    
    private String provide(String[] contentKey, Glossary glossary){
        
        if(isIndex(contentKey)){
            
            StringBuilder sb = new StringBuilder();
            
            List<String> childs = indexHelper.getChilds(contentKey);
            
            for(String child:childs){
                
                String[] childKey = append(contentKey, child);
                
                sb.append("\n[**");
                sb.append(child).append("**](")
                    .append(glossary.link(childKey)).append(")");
                
                sb.append("\n");
            }
            
            return sb.toString();
        }
        
        List<Contract> contracts = indexer.getContract(contentKey);
        
        if(!contracts.isEmpty()) {
        
            return provide(contracts.get(0));
        }
        
        return "Fuck you not found";
    }

    private boolean isIndex(String[] contentKey) {
        if(!indexHelper.isLeaf(contentKey)){
            return true;
        }
        return indexer.getContract(contentKey).isEmpty();
    }

    private String provide(Contract contract) {
        
        ContractMarkDown markDown = new ContractMarkDown();
        
        return markDown.getMarkDown(contract);
        
        
    }


    
}
