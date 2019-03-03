/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.services.contractindexing;

import com.acidmanic.pactdoc.models.Contract;
import com.acidmanic.pactdoc.utility.StringArrayKeyMaker;
import java.io.File;
import java.io.IOException;
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
    private final StringArrayKeyMaker keyMaker;
    
    public ContractIndexer(Property... indexingProperties) {
        
        this.indexingProperties = indexingProperties;
        
        this.indexes = new HashMap<>();
        
        this.propretyIndexes = new HashMap<>();
        
        this.allContracts = new ArrayList<>();
        
        this.keyMaker = new StringArrayKeyMaker();
    
    }

    
    public void index(String filePath){
        File file = new File(filePath);
        if(file.exists()){
            index(file);
        }
    }
    
    private void index(File file) {
        try {
            Contract contract = Contract.load(file.getAbsolutePath());
            index(contract);
        } catch (IOException ex) {
        }
    }
    
    public void index(Contract contract){
        
        
        indexContract(contract);
        
        
        indexProperties(contract);
        
        indexSingularProperties(contract);
    }
    
    
    public List<Contract> getContract(String...fields){
        if(fields.length!=0){
            String key = this.keyMaker.key(fields);
            if(this.indexes.containsKey(key)){
               return this.indexes.get(key);
            }   
        }
        return new ArrayList<>();
    }
    
    public List<String> getAll(String...fields){
                
        String key = this.keyMaker.key(fields);
        
        if(this.propretyIndexes.containsKey(key)){
            return this.propretyIndexes.get(key);
        }
        
        return new ArrayList<>();
    }
            
 
    
    public List<String> getAll(Class<? extends Property> property){
        Property xProp = null;
        try {
            xProp = property.newInstance();
        } catch (Exception e) {}
        
        if(xProp!=null){
            return getAll(xProp);
        }
        return new ArrayList<>();
    }
    
    public List<String> getAll(Property property){
        String key = this.keyMaker.key(new String[]{property.name()});
        if(this.propretyIndexes.containsKey(key)){
            return this.propretyIndexes.get(key);
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
        
        String[] fullkey = getFields(properties,contract);
        
        List<String> ret = triangleArray(fullkey);
        
        return ret;
    }
    
    private List<String> triangleArray(String[] array){
        return triangleArray(array, array.length);
    }
    
    private List<String> triangleArray(String[] array,int upToLength){
        ArrayList<String> ret = new ArrayList<>();
        for(int i=0;i<upToLength;i++){
            String key = this.keyMaker.key(array,i+1);
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
        String[] fields = getFields(this.indexingProperties, contract);
        
        for(int i=0;i<fields.length-1;i++){
            String propertyKey = this.keyMaker.key(fields, i+1);
            String propertyValue = fields[i+1];
            indexProperty(propertyKey,propertyValue);
        }
    }

    private void indexSingularProperties(Contract contract) {
        
        for(Property p:this.indexingProperties){
            String key = this.keyMaker.key(new String[]{p.name()});
            String value = p.value(contract);
            indexProperty(key, value);
        }
    }

    public Property[] getIndexingProperties() {
        return indexingProperties;
    }
    
    public Property getRootProperty(){
        return this.indexingProperties[0];
    }
    
    public int getPropertiesCount(){
        return this.indexingProperties.length;
    }
    
}
