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

    public ContractIndexer() {
        this.contracts = new HashMap<>();
    }
    
    public void index(String directory){
        File dir = new File(directory);
        
        File[] files = dir.listFiles();
        
        for(File file : files){
            if(file.getName().endsWith(".json")){
                try {
                    Contract contract = Contract.load(file.getAbsolutePath());
                    
                    this.index(contract);
                    
                } catch (Exception e) {
                }
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
}
