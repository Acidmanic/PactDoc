/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.services.wikigenerators;

import com.acidmanic.pactdoc.models.Contract;
import com.acidmanic.pactdoc.services.Glossary;
import com.acidmanic.pactdoc.services.extendableindexing.ContractIndexer;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class GlossaryGenerator {
    
    
    
    private final ContractIndexer indexer;

    public GlossaryGenerator(ContractIndexer indexer) {
        this.indexer = indexer;
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
        
        
        
        if(isLeaf(contentKey)){
            glossary.put(baseLink.toString()+extension, contentKey);
        }else{
            glossary.put(replace(baseLink,"Index").toString()+extension, contentKey);
        }
        
        List<String> childs = getChilds(contentKey);
        
        for(String child:childs){
            addLink(glossary
                    , append(contentKey, child)
                    , baseLink.resolve(child)
                    , extension);
        }
    }
    
    private String[] append(String[] array,String value){
        String[] ret = new String[array.length+1];
        System.arraycopy(array, 0, ret, 0, array.length);
        ret[array.length]=value;
        return ret;
    }
    
    
    private List<String> getChilds(String[] key){
        if (key.length==0){
            return indexer.getAll(indexer.getRootProperty());
        }
        if(key.length<indexer.getPropertiesCount()){
            return indexer.getAll(key);
        }
        return getNamesFor(indexer.getContract(key));
    }

    private List<String> getNamesFor(List<Contract> contracts) {
        ArrayList<String> names = new ArrayList<>();
        contracts.forEach((Contract c)-> names.add(c.getProvider().getName()));
        return names;
    }

    private boolean isLeaf(String[] contentKey) {
        return contentKey.length >= indexer.getPropertiesCount();
    }

    private Path replace(Path path, String with) {
        Path parent = path.getParent();
        if(parent==null){
            return Paths.get(with);
        }
        return parent.resolve(with);
    }
    
}
