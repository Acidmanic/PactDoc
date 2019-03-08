/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.services.wiki.interpreter;

import com.acidmanic.pactdoc.businessmodels.WikiGeneratorParamters;
import static com.acidmanic.pactdoc.services.wiki.contentproviders.ContentProvider.CONTENT_NOT_FOUND;
import com.acidmanic.pactdoc.services.wiki.interpreter.context.WikiContext;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class NullExpression extends ExpressionBase{
    
    

    public NullExpression() {
        super(null, new WikiGeneratorParamters());
    }

    @Override
    public void interpret(WikiContext context) {
        context.openBold().openItalic()
                .append(CONTENT_NOT_FOUND)
                .closeBold().closeItalic();
    }
    
}
