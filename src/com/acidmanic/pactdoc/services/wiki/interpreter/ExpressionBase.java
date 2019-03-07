/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.services.wiki.interpreter;

import com.acidmanic.pactdoc.businessmodels.WikiGeneratorParamters;
import com.acidmanic.pactdoc.models.Contract;
import static com.acidmanic.pactdoc.services.contractindexing.ContentKeyHelper.append;
import com.acidmanic.pactdoc.services.contractindexing.IndexHelper;
import com.acidmanic.pactdoc.services.wiki.contentproviders.Link;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public abstract class ExpressionBase {
    
    private final String[] currentKey;
    private final WikiGeneratorParamters paramters;
    
    public ExpressionBase(String[] currentKey, 
            WikiGeneratorParamters parameters) {
        this.currentKey = currentKey;
        this.paramters = parameters;
    }

    public ExpressionBase(ExpressionBase base){
        this.currentKey = base.currentKey;
        this.paramters = base.paramters;
    }
    
    protected String[] getCurrentKey() {
        return currentKey;
    }

   protected final WikiGeneratorParamters getParamters(){
       return this.paramters;
   }
    
    protected Contract getCurrentContract(){
            List<Contract> contracts = paramters.getIndexer()
                    .getContract(currentKey);
        
            if(!contracts.isEmpty()) {
                
                return contracts.get(0);
            }
            
            return null;
    }
    
    
    protected String getCurrentLink(){
        return paramters.getLinkGenerator().generateLink(currentKey);
    }
    
    protected List<Link> getChildsBaseRelatedLinks(){
        
        IndexHelper indexHelper = new IndexHelper(paramters.getIndexer());
        
        List<String> childs = indexHelper.getChilds(currentKey);
        
        ArrayList<Link> ret = new ArrayList<>();
        
        for(String child:childs){

            String[] childKey = append(currentKey, child);

            String fullLink = paramters.getLinkGenerator()
                    .generateLink(childKey);
            
            ret.add(new Link(fullLink,child));
        }

        return ret;
    }
    
    public abstract void interpret(PageContext context);
}
