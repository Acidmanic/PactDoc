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
import com.acidmanic.pactdoc.utility.ContentKeyHelper;
import com.acidmanic.pactdoc.services.contractindexing.properties.Property;
import com.acidmanic.pactdoc.utility.TextReformater;
import com.acidmanic.pactdoc.services.wiki.interpreter.contexts.WikiContext;
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
