/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.services.wiki.interpreter;

import com.acidmanic.pactdoc.businessmodels.WikiGeneratorParamters;
import com.acidmanic.pactdoc.services.wiki.interpreter.context.WikiContext;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class WikiExpression extends ExpressionBase{

    public WikiExpression(String[] currentKey, WikiGeneratorParamters parameters) {
        super(currentKey, parameters);
    }

    @Override
    public void interpret(WikiContext context) {
        
        getParamters().getGlossary().forEach((String[]contentKey)->{
            
            context.startNewPage(contentKey);
            
            PageExpression page = new PageExpression(contentKey, getParamters());
            
            page.interpret(context);
        });
    }
    
}
