/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.services.wiki.interpreter;

import com.acidmanic.pactdoc.businessmodels.WikiGeneratingParamters;
import com.acidmanic.pactdoc.services.contractindexing.ContentKeyHelper;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class NavigationExpression extends ExpressionBase{

    public NavigationExpression(String[] currentKey, WikiGeneratingParamters parameters) {
        super(currentKey, parameters);
    }

    public NavigationExpression(ExpressionBase base) {
        super(base);
    }
    
    
   
    
    @Override
    public void interpret(PageContext context) {
        
        String[] key = getCurrentKey();
        
        for(int i=0;i<key.length;i++){
            
            String[] subKey = ContentKeyHelper.subKey(key, i);
            
            String link = getParamters().getLinkGenerator().generateLink(subKey);
            
            link = getParamters().getLinkingStrategy().getLink(link,getCurrentLink());
            
            if(link!=null){
                
                context.append(" [")
                        .openLink(link)
                        .openItalic()
                        .append(ContentKeyHelper.getTitleFor(subKey))
                        .closeItalic()
                        .closeLink()
                        .append("] ");
                                
            }
            
        }
        
        context.newLine();
    }

    

    
    
}
