/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.services.wiki.interpreter.context;

import com.acidmanic.pactdoc.services.wiki.contentproviders.Link;
import java.util.HashMap;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public interface WikiContext {
        
    public static final WikiContext NULL = new NULLContext();
    
    WikiContext title(String text);
    
    WikiContext paragraph(String text);
    
    WikiContext table(HashMap<String,String> table);
    
    WikiContext table(String leftHeader,String rightHeader
            , HashMap<String,String> table);
    
    WikiContext openBold();
    
    WikiContext closeBold();
    
    WikiContext openItalic();

    WikiContext closeItalic();
    
    WikiContext append(String text);
        
    WikiContext newLine();
    
    WikiContext openLink(String[] contentKey);
    
    WikiContext closeLink();
    
    WikiContext json(String json);
    
    void output();

    WikiContext horizontalLine();
    
    WikiContext startNewPage(String[] contentKey);
}
