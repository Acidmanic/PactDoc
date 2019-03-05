/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.services.wiki.glossary;

import static com.acidmanic.pactdoc.services.contractindexing.ContentKeyHelper.*;
import com.acidmanic.pactdoc.services.contractindexing.ContractIndexer;
import com.acidmanic.pactdoc.services.contractindexing.IndexHelper;
import java.util.List;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class GlossaryGenerator {
    
    
    
    private final IndexHelper indexHelper;
    
    public GlossaryGenerator(ContractIndexer indexer) {
        this.indexHelper = new IndexHelper(indexer);
    }
    
    public Glossary generate(){
        Glossary glossary = new Glossary();
        
        addLink(glossary, new String[]{});
        
        return glossary;
    }
    
    
    private void addLink(Glossary glossary
            ,String[] contentKey){
        
        
        
         if(!glossary.contains(contentKey)){
                glossary.add(contentKey);
            }
        
        if(!indexHelper.isLeaf(contentKey)){
           List<String> childs = indexHelper.getChilds(contentKey);
        
            for(String child:childs){
                addLink(glossary
                        ,  append(contentKey, child));
            }
        }
        
        
        
    }
    
}
