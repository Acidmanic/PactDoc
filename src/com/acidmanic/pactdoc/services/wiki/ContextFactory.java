/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.services.wiki;

import com.acidmanic.pactdoc.businessmodels.WikiGeneratorParamters;
import com.acidmanic.pactdoc.services.wiki.interpreter.context.MarkdownContext;
import com.acidmanic.pactdoc.services.wiki.interpreter.context.WikiContext;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class ContextFactory {
    
    
    public WikiContext make(WikiGeneratorParamters paramters){
        
        Class contextClass = paramters.getWikiFormat().getContextClass();
        
        //TODO: Implement here
        if(contextClass==MarkdownContext.class){
            
        }
        
        return WikiContext.NULL;
    }
}
