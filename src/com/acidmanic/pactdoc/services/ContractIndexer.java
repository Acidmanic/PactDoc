/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.services;

import com.acidmanic.pactdoc.models.Contract;
import com.acidmanic.pactdoc.models.ConventionedContract;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class ContractIndexer {
    
    
    private final HashMap<String,List<Contract>> contracts;
    private final HashMap<String,String> services;
    
    private ContractContentIndexer contentIndexer;
    
    public ContractIndexer() {
        this.contracts = new HashMap<>();
        
        this.contentIndexer = new NullContractContentIndexer();
        
        this.services = new HashMap<>();
    }
    
    public void indexDirectory(String directory){
        File dir = new File(directory);
        
        File[] files = dir.listFiles();
        
        for(File file : files){
            index(file);
        }
    }
    
    public void index(String filePath){
        File file = new File(filePath);
        
        if(file.exists()){
            index(file);
        }
    }
    
    public void index(File file){
        if(file.getName().endsWith(".json")){
                try {
                    Contract contract = Contract.load(file.getAbsolutePath());
                    
                    this.index(contract);
                    
                } catch (Exception e) {
                }
            }
    }
    
    public void index(Contract contract){
        
        ConventionedContract conventionedContract = new ConventionedContract(contract);
        
        String serviceName = conventionedContract.getServiceName();
        
        if(serviceName!=null){
            
            List<Contract> list;
            
            if(this.contracts.containsKey(serviceName)){
                list = this.contracts.get(serviceName);  
                list.add(conventionedContract);
            }else{
                list = new ArrayList<>();
                list.add(conventionedContract);
                this.contracts.put(serviceName, list);
            }
            
            this.contentIndexer.index(serviceName, conventionedContract);
            
            this.services.put(getContractKey(contract), serviceName);
        }
    }
    
    
    public List<String> getServices(){
        ArrayList<String> services = new ArrayList<>();
        
        services.addAll(this.contracts.keySet());
        
        return services;
    }
    
    
    public List<Contract> getContracts(String service){
        
        if (this.contracts.containsKey(service)){
            return this.contracts.get(service);
        }
        
        return new ArrayList<>();
    }

    public ContractContentIndexer getContentIndexer() {
        return contentIndexer;
    }

    public void setContentIndexer(ContractContentIndexer contentIndexer) {
        this.contentIndexer = contentIndexer;
    }
    
    public String getParentService(Contract contract){
        String key = getContractKey(contract);
        if(this.services.containsKey(key)){
            return this.services.get(key);
        }
        return null;
    }

    private String getContractKey(Contract contract) {
        return contract.getProvider().getName().toLowerCase() + ":" +
                contract.getMetadata().getPactSpecification()
                .getVersion().toLowerCase();
    }
    
    
}
