/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.services;

import com.acidmanic.pactdoc.models.Contract;
import com.acidmanic.pactdoc.models.ConventionedContract;
import com.acidmanic.pactdoc.models.Interaction;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class HashContractContentIndexer implements ContractContentIndexer{
    
    private final HashMap<String,List<ConventionedContract>> indexes;

    public HashContractContentIndexer() {
        this.indexes = new HashMap<>();
    }
    
    @Override
    public void index(String serviceName,ConventionedContract contract){
        String[] words = extractWords(contract);
        
        for(String word:words){
            if(indexes.containsKey(word)){
               List<ConventionedContract> contracts = this.indexes.get(word);
               
               contracts.add(contract);
               
            }else{
                List<ConventionedContract> contracts = new ArrayList<>();
                
                contracts.add(contract);
                
                this.indexes.put(word, contracts);
            }
            
        }
    }

    private String[] extractWords(Contract contract) {
        
        
        
        StringBuilder sb = new StringBuilder();
        
        sb.append(contract.getConsumer().getName()).append(" ");
        sb.append(contract.getProvider().getName()).append(" ");
        
        contract.getInteractions().forEach((Interaction in)-> append(sb,in));
        
        return sb.toString().toLowerCase().split("\\s+");
        
    }

    private StringBuilder append(StringBuilder sb, Interaction in) {
        
        sb.append(in.getDescription());
        
        sb.append(in.getProviderState());
        
        sb.append(in.getRequest().getPath());
        
        in.getRequest().getHeaders().forEach((String n,String v)->{
            sb.append(n).append(" ").append(v).append(" ");
        });
        
        in.getResponse().getHeaders().forEach((String n,String v)->{
            sb.append(n).append(" ").append(v).append(" ");
        });
        
        sb.append(in.getResponse().getBody()).append(" ");
        
        sb.append(in.getResponse().getStatus()).append(" ");
        
        return sb;
    }
    
    
    @Override
    public List<ConventionedContract> searchForWord(String word){
        String key = word.toLowerCase();
        if(this.indexes.containsKey(key)){
            return this.indexes.get(key);
        }
        return new ArrayList<>();
    }
}
