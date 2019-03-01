/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.services.extendableindexing;

import com.acidmanic.pactdoc.models.Contract;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class ContractIndexer {
    
    private final Property[] indexingProperties;
    private final HashMap<String,List<Contract>> indexes;
    private final HashMap<String,List<String>> propretyIndexes;
    private final List<Contract> allContracts;
    
    public ContractIndexer(Property... indexingProperties) {
        this.indexingProperties = indexingProperties;
        
        this.indexes = new HashMap<>();
        
        this.propretyIndexes = new HashMap<>();
        
        this.allContracts = new ArrayList<>();
    }
    
    
    
    public void index(Contract contract){
        
        
        indexContract(contract);
        
        
        indexProperties(contract);
    }
    
    
    public List<Contract> getContract(String...fields){
        String key = key(fields);
        if(this.indexes.containsKey(key)){
            return this.indexes.get(key);
        }
        return new ArrayList<>();
    }
    
    public List<Contract> getAllContracts(){
        List<Contract> ret = new ArrayList<>();
        ret.addAll(this.allContracts);
        return ret;
    }
    
    private void addContract(String key,Contract contract){
        
        List<Contract> contracts;
        
        if(this.indexes.containsKey(key)){
            contracts = this.indexes.get(key);
        }else{
            contracts = new ArrayList<>();
            this.indexes.put(key, contracts);
        }
        
        contracts.add(contract);
    }
    
    
    private List<String> allKeys(Property[] properties,Contract contract){
        
        ArrayList<String> ret = new ArrayList<>();
        
        String[] fullkey = getFields(properties,contract);
        
        int count = properties.length;
        
        for(int i=0;i<count;i++){
            String key = key(fullkey,i+1);
            ret.add(key);
        }
        return ret;
    }
    
    private String[] getFields(Property[] properties,Contract contract){
        String[] ret = new String[properties.length];
        
        for(int i=0;i<ret.length;i++){
            ret[i]=properties[i].value(contract);
        }
        return ret;
    }
 
    
    private String getPropertyKey(Property[] properties,Contract contract
            , int from, int to){
        
        String[] fields ={properties[from].value(contract)
                ,properties[to].name()};
        
        return key(fields);
    }
    
    private String key(String[] fields){
        return key(fields,fields.length);
    }
    
    private String key(String[] fields,int upTo){
        StringBuilder sb = new StringBuilder();
        
        String sep="";
        
        sb.append("[");
        
        for(int i=0;i<upTo;i++){
            String field = fields[i];
            sb.append(sep).append(field);
            sep="]::::[";
        }
     
        sb.append("]");
        
        return sb.toString();
    }

    private void indexProperty(String propertyKey, String propertyValue) {
        List<String> values;
        
        if(this.propretyIndexes.containsKey(propertyKey)){
            values = this.propretyIndexes.get(propertyKey);
        }else{
            values = new ArrayList<>();
            this.propretyIndexes.put(propertyKey, values);
        }
        if(!values.contains(propertyValue)){
            values.add(propertyValue);
        }
    }

    private void indexContract(Contract contract) {
        List<String> properties = allKeys(indexingProperties, contract);
        
        properties.forEach((String key)->addContract(key, contract));
        
        this.allContracts.add(contract);
    }

    private void indexProperties(Contract contract) {
        for(int from=0;from<this.indexingProperties.length-1;from++)
            for(int to=from+1;to<this.indexingProperties.length;to++){
                String propertyKey = getPropertyKey(indexingProperties
                        ,contract, from, to);
                String propertyValue = this.indexingProperties[to].value(contract);
                indexProperty(propertyKey,propertyValue);
            }
    }
}
