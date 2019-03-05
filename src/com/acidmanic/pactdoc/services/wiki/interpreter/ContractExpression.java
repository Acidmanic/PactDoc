/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.services.wiki.interpreter;

import com.acidmanic.pactdoc.businessmodels.WikiGeneratingParamters;
import com.acidmanic.pactdoc.models.Contract;
import com.acidmanic.pactdoc.models.Interaction;
import com.acidmanic.pactdoc.utility.TextReformater;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class ContractExpression extends ExpressionBase{

    public ContractExpression(String[] currentKey, 
            WikiGeneratingParamters parameters) {
        super(currentKey, parameters);
    }

    public ContractExpression(ExpressionBase base) {
        super(base);
    }
    
    
    

    @Override
    public void interpret(PageContext context) {
        
        Contract contract = getCurrentContract();
        
        context.title(contract.getProvider().getName());
        
        new NavigationExpression(this).interpret(context);
        
        context.newLine();
        
        for(Interaction inter:contract.getInteractions()){
            
            context.append("If ").append(inter.getProviderState())
                    .append(", an http ").openBold()
                    .append(inter.getRequest().getMethod().toUpperCase()).closeBold()
                    .append(" Request to ").openItalic()
                    .append(inter.getRequest().getPath()).closeItalic();
            
            
            if(!inter.getRequest().getHeaders().isEmpty()){
                
                context.append(", with headers: ").newLine()
                        .table("Key", "Value", inter.getRequest().getHeaders())
                        .newLine();
            }
            
            if(inter.getRequest().getBody()!=null){
                
                context.newLine().append("with body:").newLine()
                        .json(new TextReformater().pritifyJson(inter.getRequest().getBody()))
                        .newLine();
            }

            context.newLine().append("Will receive a response with status code: ")
                    .openBold().append(Integer.toString(inter.getResponse().getStatus()))
                    .closeBold().newLine();
                    
            if(!inter.getResponse().getHeaders().isEmpty()){
                context.append(", with headers: ").newLine()
                        .table("Key", "Value", inter.getResponse().getHeaders())
                        .newLine();
            }

            if(inter.getResponse().getBody()!=null){
                context.json(inter.getResponse().getBody());
            }
        }
    }

   
    
}
