/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.services.wiki.interpreter;

import com.acidmanic.pactdoc.businessmodels.WikiGeneratingParamters;

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
        
        for(int i=0;i<getCurrentKey().length;i++){
            
            String[] key = getSubKey(getCurrentKey());
            
            String link = getParamters().getLinkGenerator().generateLink(key);
            
            link = getParamters().getLinkingStrategy().getLink(link,getCurrentLink());
            
            if(link!=null){
                
                context.append(" [")
                        .openLink(link)
                        .openItalic()
                        .append(getCurrentKey()[i])
                        .closeItalic()
                        .closeLink()
                        .append("] ");
                                
            }
            
        }
        
        context.newLine();
    }

    private String[] getSubKey(String[] key) {
        if(key.length<1){
            return new String[]{};
        }
        String[] sub = new String[key.length-1];
        System.arraycopy(key, 0, sub, 0, sub.length);
        return sub;
    }

    
    
}
