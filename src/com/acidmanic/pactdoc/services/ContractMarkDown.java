/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.services;

import com.acidmanic.pactdoc.models.Contract;
import com.acidmanic.pactdoc.models.Interaction;
import java.util.HashMap;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class ContractMarkDown {
    
    
    public String getMarkDown(Contract contract){
        StringBuilder sb = new StringBuilder();
        
        
        
        sb.append(contract.getProvider().getName()).append("\n")
                .append("===").append("\n")
                .append("\n")
                .append("\n");
        
        
        for(Interaction inter:contract.getInteractions()){
            sb.append("If ").append(inter.getProviderState())
                .append(", an http __").append(inter.getRequest().getMethod().toUpperCase())
                .append("__ Request to _").append(inter.getRequest().getPath()).append("_");
            if(!inter.getRequest().getHeaders().isEmpty()){
                sb.append(", with headers: \n");
                appendTable(sb,inter.getRequest().getHeaders());
            }

            sb.append("\nWill receive a response with status code: __")
                    .append(inter.getResponse().getStatus())
                    .append("__");

            if(!inter.getResponse().getHeaders().isEmpty()){
                sb.append(", with headers: \n");
                appendTable(sb,inter.getResponse().getHeaders());
            }

            if(inter.getResponse().getBody()!=null){
               sb.append("\n").append("with body:\n```json\n")
                       .append(pritifyJson(inter.getResponse().getBody()))
                       .append("\n```");
            }
        }
                
        
        return sb.toString();
    }

    private StringBuilder appendTable(StringBuilder sb, HashMap<String, String> headers) {
        
        sb.append("\n|Key   |Value   |"
                + "\n|:----------|:----------------------------------|\n");
        for(String name:headers.keySet()){
            sb.append("|").append(name).append("|").append(headers.get(name)).append("|")
                    .append("\n");
        }
        return sb;
    }
    
    
    private String pritifyJson(String json){
        StringBuilder sb = new StringBuilder();
        
        int indent =0;
        char[] chars = json.toCharArray();
        
        for(char ch: chars){
            if(ch=='{' || ch == '[' ){
                sb.append(ch);
                indent+=1;
                postIndent(sb,indent);
            }else if(ch=='}' || ch ==']'){
                indent -=1;
                postIndent(sb,indent);
                sb.append(ch);
            }else if ( ch == ','){
                sb.append(ch);
                postIndent(sb,indent);
            }else{
                sb.append(ch);
            }
        }
        
        return sb.toString();
    }

   
    private StringBuilder postIndent(StringBuilder sb,int indent) {
        
        sb.append("\n");
        
        for(int i=0;i<indent;i++){
            sb.append("    ");
        }
        
        return sb;
    }
}
