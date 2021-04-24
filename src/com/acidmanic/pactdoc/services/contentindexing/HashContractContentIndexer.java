/* 
 * The MIT License
 *
 * Copyright 2019 Mani Moayedi (acidmanic.moayedi@gmail.com).
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.acidmanic.pactdoc.services.contentindexing;

import com.acidmanic.pactmodels.Contract;
import com.acidmanic.pactmodels.ConventionedContract;
import com.acidmanic.pactmodels.Interaction;
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
