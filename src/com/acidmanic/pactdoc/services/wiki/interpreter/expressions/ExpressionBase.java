/* 
 * The MIT License
 *
 * Copyright 2019 Mani Moayedi (acidmanic.moayedi@gmail.com).
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.acidmanic.pactdoc.services.wiki.interpreter.expressions;

import com.acidmanic.pactdoc.businessmodels.WikiGeneratorParamters;
import static com.acidmanic.pactdoc.utility.ContentKeyHelper.append;
import com.acidmanic.pactdoc.services.contractindexing.IndexHelper;
import java.util.ArrayList;
import java.util.List;
import com.acidmanic.pactdoc.services.wiki.interpreter.contexts.WikiContext;
import com.acidmanic.pactmodels.Contract;

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
