/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.services.wiki;

import com.acidmanic.pactdoc.businessmodels.WikiGeneratorParamters;
import com.acidmanic.pactdoc.services.wiki.interpreter.contexts.MarkdownContext;
import com.acidmanic.pactdoc.services.wiki.interpreter.contexts.MultiPageHtmlContext;
import com.acidmanic.pactdoc.services.wiki.interpreter.contexts.WikiContext;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class ContextFactory {
    
    
    public WikiContext make(WikiGeneratorParamters paramters){
        
        Class contextClass = paramters.getWikiFormat().getContextClass();
        
        if(contextClass==MarkdownContext.class){
            return new MarkdownContext(paramters.isReferrerBaseLinking(),
                    paramters.isAddFileExtensions(),
                    paramters.getOutput(),
                    paramters.getIndexer(),
                    paramters.getApiBase(),
                    paramters.isSingleDirectory(),
                    paramters.getSingleDirectoryDelimiter());
        }
        
        if(contextClass==MultiPageHtmlContext.class){
            return new MultiPageHtmlContext(paramters.isReferrerBaseLinking(),
                    paramters.isAddFileExtensions(),
                    paramters.getOutput(),
                    paramters.getIndexer(),
                    paramters.getApiBase(),
                    paramters.isSingleDirectory(),
                    paramters.getSingleDirectoryDelimiter());
        }
        return WikiContext.NULL;
    }
}
