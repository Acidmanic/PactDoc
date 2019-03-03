/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.services.contentproviders;

import com.acidmanic.pactdoc.services.contentproviders.Link;
import java.util.HashMap;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public interface PageContext {
        
    public static final PageContext NULL = new NULLContext();
    
    PageContext title(String text);
    
    PageContext paragraph(String text);
    
    PageContext table(HashMap<String,String> table);
    
    PageContext table(String leftHeader,String rightHeader
            , HashMap<String,String> table);
    
    PageContext openBold();
    
    PageContext closeBold();
    
    PageContext openItalic();

    PageContext closeItalic();
    
    PageContext append(String text);
    
    PageContext link(Link link);
    
    PageContext newLine();
    
    PageContext json(String json);
    
    String output();
}
