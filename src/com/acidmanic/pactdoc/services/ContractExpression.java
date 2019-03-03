/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.services;

import com.acidmanic.pactdoc.models.Contract;
import com.acidmanic.pactdoc.models.Interaction;
import com.acidmanic.pactdoc.utility.TextReformater;
import com.acidmanic.pactdoc.services.pages.PageContext;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class ContractExpression {
    
    
    private final PageContext context;

    public ContractExpression(PageContext context) {
        this.context = context;
    }
    
    
    public void interpret(Contract contract){
        
        this.context.title(contract.getProvider().getName())
                .newLine();
        
        for(Interaction inter:contract.getInteractions()){
            
            this.context.append("If ").append(inter.getProviderState())
                    .append(", an http").openBold()
                    .append(inter.getRequest().getMethod().toUpperCase()).closeBold()
                    .append(" Request to ").openItalic()
                    .append(inter.getRequest().getPath()).closeItalic();
            
            
            if(!inter.getRequest().getHeaders().isEmpty()){
                
                this.context.append(", with headers: ").newLine()
                        .table("Key", "Value", inter.getRequest().getHeaders())
                        .newLine();
            }
            
            if(inter.getRequest().getBody()!=null){
                
                this.context.newLine().append("with body:").newLine()
                        .json(new TextReformater().pritifyJson(inter.getRequest().getBody()))
                        .newLine();
            }

            this.context.newLine().append("Will receive a response with status code: ")
                    .openBold().append(Integer.toString(inter.getResponse().getStatus()))
                    .closeBold().newLine();
                    
            if(!inter.getResponse().getHeaders().isEmpty()){
                this.context.append(", with headers: ").newLine()
                        .table("Key", "Value", inter.getResponse().getHeaders())
                        .newLine();
            }

            if(inter.getResponse().getBody()!=null){
                this.context.json(inter.getResponse().getBody());
            }
        }
        
    }

   
    
}
