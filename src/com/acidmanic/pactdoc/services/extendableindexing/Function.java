/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.services.extendableindexing;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class Function extends Service {

    @Override
    public String name() {
        return "Function";
    }

    @Override
    protected String getPart(String[] parts) {
        StringBuilder sb = new StringBuilder();
        String sep ="";
        for(int i=1;i<parts.length;i++){
            sb.append(sep).append(parts[i]);
        }
        return sb.toString();
    }
    
    
    
    
    
}
