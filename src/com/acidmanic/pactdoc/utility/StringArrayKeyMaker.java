/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.utility;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class StringArrayKeyMaker {
    
    
    public String key(String[] fields){
        return key(fields,fields.length);
    }
    
    public String key(String[] fields,int upToCount){
        StringBuilder sb = new StringBuilder();
        
        String sep="";
        
        sb.append("[");
        
        for(int i=0;i<upToCount;i++){
            String field = fields[i];
            sb.append(sep).append(field);
            sep="]::[";
        }
     
        sb.append("]");
        
        return sb.toString();
    }
}
