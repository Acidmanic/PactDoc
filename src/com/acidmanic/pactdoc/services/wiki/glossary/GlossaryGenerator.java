/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.services.wiki.glossary;

import static com.acidmanic.pactdoc.services.contractindexing.ContentKeyHelper.*;
import com.acidmanic.pactdoc.services.contractindexing.ContractIndexer;
import com.acidmanic.pactdoc.services.contractindexing.IndexHelper;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
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
    
    public List<String[]> generate(){
        List<String[]> glossary = new ArrayList<>();
        
        addLink(glossary, new String[]{});
        
        return glossary;
    }
    
    
    private void addLink(List<String[]> glossary
            ,String[] contentKey){
        
        glossary.add(contentKey);
        
        List<String> childs = indexHelper.getChilds(contentKey);
        
        for(String child:childs){
            addLink(glossary
                    ,  append(contentKey, child));
        }
    }
    
    
    
    public Glossary generate(String linksBase,String extension){
        Glossary glossary = new Glossary();
        
        Path base = Paths.get(linksBase);
        
        addLink(glossary, new String[]{}, base, extension);
        
        return glossary;
    }
    
    
    private void addLink(Glossary glossary
            ,String[] contentKey
            ,Path baseLink
            ,String extension){
        
        
        
        if(indexHelper.isLeaf(contentKey)){
            glossary.put(baseLink.toString()+extension, contentKey);
            return;
        }else{
            glossary.put(baseLink.resolve("Index").toString()+extension, contentKey);
        }
        
        List<String> childs = indexHelper.getChilds(contentKey);
        
        for(String child:childs){
            addLink(glossary
                    ,  append(contentKey, child)
                    , baseLink.resolve(child)
                    , extension);
        }
    }
 

    private Path replace(Path path, String with) {
        Path parent = path.getParent();
        if(parent==null){
            return Paths.get(with);
        }
        return parent.resolve(with);
    }
    
}
