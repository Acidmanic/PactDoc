/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.services.contractindexing;

import com.acidmanic.pactdoc.models.Contract;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class IndexHelper {
    
    
    private final ContractIndexer indexer;

    public IndexHelper(ContractIndexer indexer) {
        this.indexer = indexer;
    }
    
    public List<String> getChilds(String[] key){
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
    
    public boolean isLeaf(String...fields){
        return fields.length>=this.indexer.getPropertiesCount();
    }
    
    public String childrensPropertyName(String...fields){
        int index = fields.length;
        Property[] properties = this.indexer.getIndexingProperties();
        if(index< properties.length){
            return properties[index].name();
        }
        return "Contract";
    }
    
    public String addressingPropertyName(String...fields){
        int index = fields.length;
        if(index ==0 ){
            return "Index";
        }
        index -=1;
        Property[] properties = this.indexer.getIndexingProperties();
        if(index<properties.length){
            return properties[index].name();
        }
        return "";
    }
}
