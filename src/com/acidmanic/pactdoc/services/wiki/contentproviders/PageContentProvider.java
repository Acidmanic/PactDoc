/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.services.wiki.contentproviders;

import com.acidmanic.pactdoc.businessmodels.WikiGeneratingParamters;
import com.acidmanic.pactdoc.services.wiki.interpreter.ExpressionBase;
import com.acidmanic.pactdoc.services.wiki.interpreter.PageContext;
import com.acidmanic.pactdoc.services.wiki.interpreter.PageExpression;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class PageContentProvider implements ContentProvider{

   
    private final WikiGeneratingParamters paramters;

    public PageContentProvider(WikiGeneratingParamters paramters) {
        this.paramters = paramters;
    }
    
    
   

    @Override
    public String provideContentFor(String[] contentKey) {
        
        
        ExpressionBase expression = new PageExpression(contentKey,paramters);
        
        return render(expression);
    }
    
    private String render(ExpressionBase expression) {
        PageContext context = paramters.getWikiFormat().makeContext();
        expression.interpret(context);
        return context.output();
    }
    
}
