/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.services.wiki.interpreter.expressions;

import com.acidmanic.pactdoc.businessmodels.WikiGeneratorParamters;
import com.acidmanic.pactdoc.utility.ContentKeyHelper;
import com.acidmanic.pactdoc.services.wiki.interpreter.contexts.WikiContext;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class NavigationExpression extends ExpressionBase{

    public NavigationExpression(String[] currentKey, WikiGeneratorParamters parameters) {
        super(currentKey, parameters);
    }

    public NavigationExpression(ExpressionBase base) {
        super(base);
    }
    
    
   
    
    @Override
    public void interpret(WikiContext context) {
        
        String[] key = getCurrentKey();
        
        for(int i=0;i<key.length;i++){
            
            String[] subKey = ContentKeyHelper.subKey(key, i);
            
            context.append(" [")
                    .openLink(subKey)
                    .openItalic()
                    .append(ContentKeyHelper.getTitleFor(subKey))
                    .closeItalic()
                    .closeLink()
                    .append("] ");
        }
        
        context.newLine();
    }

    

    
    
}
