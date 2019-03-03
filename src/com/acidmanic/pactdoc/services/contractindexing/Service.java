/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.services.contractindexing;

import com.acidmanic.pactdoc.models.Contract;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class Service implements Property{

    public static final String DEFAULT_SERVICE = "General";
    
    @Override
    public String name() {
        return "Service";
    }

    @Override
    public String value(Contract contract) {
        String provider  =contract.getProvider().getName();
        
        if(provider!=null){
            String parts[] = provider.split("-");
            
           return getPart(parts);
            
        }
        
        return DEFAULT_SERVICE;
    }
    
    protected String getPart(String parts[]){
        if(parts.length>1){
                return parts[0];
        }
        
        return DEFAULT_SERVICE;
    }
    
}
