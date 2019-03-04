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
import com.acidmanic.pactdoc.utility.TextReformater;
import java.util.List;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class IndexExpression {
    
    
    private final PageContext provider;

    public IndexExpression(PageContext provider) {
        this.provider = provider;
    }
    
    
    
    public void interpret(List<Link> links,String[] current,ContractIndexer indexer){
        
        
        String title = getTitleFor(current);
        
        provider.title(title);
        if(current.length>1){
                provider.openItalic()
                .append("(")
                .append(delimit(current))
                .append(")")
                .newLine().newLine().newLine();
        }
        
        
        int propIndex = current.length;
        Property[] properties = indexer.getIndexingProperties();
        
        if(propIndex<properties.length){
            String itemsName = properties[propIndex].name();
            itemsName = new TextReformater().plural(itemsName);
            provider.openItalic()
                    .append(itemsName)
                    .append(":")
                    .newLine();
        }
        
        provider.newLine();
        
        
        
        for(Link link:links){
            
            provider.openLink(link.getSrc());
            
            provider.openBold().openItalic()
                    .append(link.getCaption())
                    .closeItalic().closeBold()
                    .closeLink()
                    .newLine();
        }
        
        provider.newLine().newLine().newLine();
        
        provider.horizontalLine();
        
        
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
