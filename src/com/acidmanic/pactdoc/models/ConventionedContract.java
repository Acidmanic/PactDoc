/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.models;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class ConventionedContract extends Contract{
    
    
    private String serviceName;
    
    private String apiName;

    public ConventionedContract() {
    }

    @SuppressWarnings("OverridableMethodCallInConstructor")
    public ConventionedContract(Contract contract) {
        
        this.setConsumer(contract.getConsumer());
        
        this.setInteractions(contract.getInteractions());
        
        this.setMetadata(contract.getMetadata());
        
        this.setProvider(contract.getProvider());
    }
    
    
    @Override
    public void setProvider(Provider provider) {
        super.setProvider(provider);
        
        this.serviceName = null;
        this.apiName = null;
        
        String pname = provider.getName();
        
        if (pname==null){
            return ;
        }
        
        String[] parts = pname.split("-");
        
        if(parts.length!=2){
            return ;
        }
        
        this.serviceName = parts[0];
        
        this.apiName = parts[1];
        
    }

    public String getServiceName() {
        return serviceName;
    }

    public String getApiName() {
        return apiName;
    }
    
    
    public static ConventionedContract load(String path) throws IOException{
        ObjectMapper objectMapper = new ObjectMapper();
    
        ConventionedContract contract = objectMapper.readValue(new File(path), ConventionedContract.class);
    
        return contract;
    }
}
