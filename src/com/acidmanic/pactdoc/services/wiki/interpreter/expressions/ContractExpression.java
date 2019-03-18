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
import com.acidmanic.pactdoc.models.Contract;
import com.acidmanic.pactdoc.models.Interaction;
import com.acidmanic.pactdoc.utility.TextReformater;
import com.acidmanic.pactdoc.services.wiki.interpreter.contexts.WikiContext;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class ContractExpression extends ExpressionBase{

    public ContractExpression(String[] currentKey, 
            WikiGeneratorParamters parameters) {
        super(currentKey, parameters);
    }

    public ContractExpression(ExpressionBase base) {
        super(base);
    }
    
    
    

    @Override
    public void interpret(WikiContext context) {
        
        Contract contract = getCurrentContract();
        
        context.title(contract.getProvider().getName());
        
        context.horizontalLine();
        
        if(getCurrentKey().length>0){
            context.append("Back to: ");

            new NavigationExpression(this).interpret(context);

            context.horizontalLine();
        }
                
        context.newLine();
        
        for(Interaction inter:contract.getInteractions()){
            
            context.append("If ").append(inter.getProviderState())
                    .append(", an http ")
                    .badge(inter.getRequest().getMethod().toUpperCase())
                    .append(" Request to ").openItalic()
                    .append(inter.getRequest().getPath()).closeItalic();
            
            
            if(!inter.getRequest().getHeaders().isEmpty()){
                
                context.append(", With headers: ").newLine()
                        .table("Key", "Value", inter.getRequest().getHeaders())
                        .newLine();
            }
            
            if(inter.getRequest().getBody()!=null){
                
                context.newLine().append("With body:").newLine()
                        .json(new TextReformater().pritifyJson(inter.getRequest().getBody()))
                        .newLine();
            }

            context.newLine().append("Will receive a response with status code: ")
                    .openBold().append(Integer.toString(inter.getResponse().getStatus()))
                    .closeBold().newLine();
                    
            if(!inter.getResponse().getHeaders().isEmpty()){
                context.append(", With headers: ").newLine()
                        .table("Key", "Value", inter.getResponse().getHeaders())
                        .newLine();
            }

            if(inter.getResponse().getBody()!=null){
                context.json(inter.getResponse().getBody());
            }
        }
    }

   
    
}
