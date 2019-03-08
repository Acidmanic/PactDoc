/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.services;

import com.acidmanic.pactdoc.businessmodels.WikiGeneratorParamters;
import com.acidmanic.pactdoc.services.wiki.ContextFactory;
import com.acidmanic.pactdoc.services.wiki.interpreter.WikiExpression;
import com.acidmanic.pactdoc.services.wiki.interpreter.context.WikiContext;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class WikiGenerator {
    
    private final WikiGeneratorParamters paramters;


    public WikiGenerator(WikiGeneratorParamters parameters) {
        this.paramters = parameters;
        
    }

   
 
    public void generate(String destinationDirectory) {
        
        
        WikiExpression wiki = new WikiExpression(new String[]{}, paramters);
                
        WikiContext context= new ContextFactory().make(paramters);
        
        wiki.interpret(context);
        
    }
    
    
    
    
}
