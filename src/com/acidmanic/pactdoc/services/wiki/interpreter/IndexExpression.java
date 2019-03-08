/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.services.wiki.interpreter;

import com.acidmanic.pactdoc.businessmodels.WikiGeneratorParamters;
import com.acidmanic.pactdoc.services.contractindexing.ContentKeyHelper;
import com.acidmanic.pactdoc.services.contractindexing.properties.Property;
import com.acidmanic.pactdoc.services.wiki.contentproviders.Link;
import com.acidmanic.pactdoc.utility.TextReformater;
import com.acidmanic.pactdoc.services.wiki.interpreter.context.WikiContext;
import java.util.List;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class IndexExpression extends ExpressionBase{

    public IndexExpression(String[] currentKey, WikiGeneratorParamters parameters) {
        super(currentKey, parameters);
    }

    public IndexExpression(ExpressionBase base) {
        super(base);
    }
    
    
    @Override
    public void interpret(WikiContext context) {
        
        
        String title = ContentKeyHelper.getTitleFor(getCurrentKey());
        
        context.title(title);
        
        context.horizontalLine();
        
        if(getCurrentKey().length>0){
            context.append("Back to: ");

            new NavigationExpression(this).interpret(context);

            context.horizontalLine();
        }
        
        if(getCurrentKey().length>1){
                context.openItalic()
                .append("(")
                .append(delimit(getCurrentKey()))
                .append(")")
                .closeItalic()
                .newLine().newLine().newLine();
        }
        
        
        int propIndex = getCurrentKey().length;
        Property[] properties = getParamters().getIndexer().getIndexingProperties();
        
        if(propIndex<properties.length){
            String itemsName = properties[propIndex].name();
            itemsName = new TextReformater().plural(itemsName);
            context.openItalic()
                    .append(itemsName)
                    .append(":")
                    .closeItalic()
                    .newLine();
        }
        
        context.newLine();
                
        List<String[]> childs= getChilds();
        
        for(String[] child:childs){
            
            context.openLink(child);
            
            context.openBold().openItalic()
                    .append(ContentKeyHelper.getTitleFor(child))
                    .closeItalic().closeBold()
                    .closeLink()
                    .newLine();
            
        }
        
        context.newLine().newLine().newLine();
        context.horizontalLine();
        
        
    }

    private String delimit(String[] current) {
        String ret = "";
        String sep = "";
        for(int i=0;i<current.length;i++){
            ret += sep + current[i];
            sep=" - ";
        }
        return ret;
    }

    
}
