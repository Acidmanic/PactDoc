/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.services.wiki.interpreter;

import com.acidmanic.pactdoc.businessmodels.WikiGeneratorParamters;
import com.acidmanic.pactdoc.services.contractindexing.ContractIndexer;
import com.acidmanic.pactdoc.services.contractindexing.IndexHelper;
import com.acidmanic.pactdoc.services.wiki.linking.LinkGenerator;
import com.acidmanic.pactdoc.services.wiki.linking.LinkingStrategy;
import com.acidmanic.pactdoc.services.wiki.wikiformat.WikiFormat;
import java.util.List;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class PageExpression extends ExpressionBase{

    private final IndexHelper indexHelper;

    public PageExpression(String[] currentKey, WikiGeneratorParamters parameters) {
        super(currentKey, parameters);
        this.indexHelper = new IndexHelper(getParamters().getIndexer());
    }

    public PageExpression(ExpressionBase base) {
        super(base);
        this.indexHelper = new IndexHelper(getParamters().getIndexer());
    }
    
    

    protected IndexHelper getIndexHelper() {
        return indexHelper;
    }
    

    
    private boolean isIndex(String[] contentKey) {
        if(!indexHelper.isLeaf(contentKey)){
            return true;
        }
        return getParamters().getIndexer().getContract(contentKey).isEmpty();
    }

    @Override
    public void interpret(PageContext context) {
        ExpressionBase expression;
        
        if(isIndex(getCurrentKey())){
            expression = new IndexExpression(this);
        }else{
            expression = new ContractExpression(this);
        }
        
        expression.interpret(context);
    }
    
    
    
}
