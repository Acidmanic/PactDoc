/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.services.wiki.interpreter;

import com.acidmanic.pactdoc.models.Contract;
import com.acidmanic.pactdoc.services.contractindexing.ContractIndexer;
import com.acidmanic.pactdoc.services.contractindexing.properties.Property;
import com.acidmanic.pactdoc.services.wiki.contentproviders.Link;
import com.acidmanic.pactdoc.services.wiki.glossary.Glossary;
import com.acidmanic.pactdoc.utility.TextReformater;
import java.util.List;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class IndexExpression extends ExpressionBase{

    public IndexExpression(String[] currentKey, 
            List<Link> links, 
            ContractIndexer indexer, 
            Glossary glossary, 
            Contract currentContract) {
        super(currentKey, links, indexer, glossary, currentContract);
    }

    public IndexExpression(ExpressionBase base) {
        super(base);
    }
    
    
    
    
    
    
    
    @Override
    public void interpret(PageContext context) {
        
        
        String title = getTitleFor(getCurrentKey());
        
        
        
        context.title(title);
        if(getCurrentKey().length>1){
                context.openItalic()
                .append("(")
                .append(delimit(getCurrentKey()))
                .append(")")
                .closeItalic()
                .newLine().newLine().newLine();
        }
        
        
        int propIndex = getCurrentKey().length;
        Property[] properties = getIndexer().getIndexingProperties();
        
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
        
        
        
        for(Link link:getLinks()){
            
            context.openLink(link.getSrc());
            
            context.openBold().openItalic()
                    .append(link.getCaption())
                    .closeItalic().closeBold()
                    .closeLink()
                    .newLine();
        }
        
        context.newLine().newLine().newLine();
        
        new NavigationExpression(this).interpret(context);
        
        context.horizontalLine();
        
        
    }

    private String getTitleFor(String[] current) {
        int depth = current.length;
        
        if(depth==0){
            return "Api Home Page";
        }else{
            return current[depth-1];
        }
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
