/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.services.wiki.interpreter;

import com.acidmanic.pactdoc.models.Contract;
import com.acidmanic.pactdoc.services.contractindexing.ContractIndexer;
import com.acidmanic.pactdoc.services.wiki.contentproviders.Link;
import com.acidmanic.pactdoc.services.wiki.glossary.Glossary;
import java.util.List;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class NavigationExpression extends ExpressionBase{

    public NavigationExpression(String[] currentKey, List<Link> links, ContractIndexer indexer, Glossary glossary, Contract currentContract) {
        super(currentKey, links, indexer, glossary, currentContract);
    }

    public NavigationExpression(ExpressionBase base) {
        super(base);
    }
    
    
   
    
    @Override
    public void interpret(PageContext context) {
        
        for(int i=0;i<getCurrentKey().length;i++){
            
            String[] key = getSubKey(getCurrentKey());
            
            String link = getGlossary().link(key);
            
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
