/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.services.wiki.interpreter.expressions;

import com.acidmanic.pactdoc.businessmodels.WikiGeneratorParamters;
import com.acidmanic.pactdoc.models.Contract;
import static com.acidmanic.pactdoc.utility.ContentKeyHelper.append;
import com.acidmanic.pactdoc.services.contractindexing.IndexHelper;
import java.util.ArrayList;
import java.util.List;
import com.acidmanic.pactdoc.services.wiki.interpreter.contexts.WikiContext;

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
    
    protected List<String[]> getChilds(){
        
        IndexHelper indexHelper = new IndexHelper(paramters.getIndexer());
        
        List<String> childs = indexHelper.getChilds(currentKey);
        
        ArrayList<String[]> ret = new ArrayList<>();
        
        for(String child:childs){

            String[] childKey = append(currentKey, child);

            ret.add(childKey);
        }

        return ret;
    }
    
    public abstract void interpret(WikiContext context);
}
