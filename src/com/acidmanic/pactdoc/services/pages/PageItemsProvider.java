/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.services.pages;

import com.acidmanic.pactdoc.services.contentproviders.Link;
import java.util.HashMap;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public interface PageItemsProvider {
    
    
    StringBuilder appendTitle(StringBuilder sb,String text);
    
    StringBuilder appendParagraph(StringBuilder sb,String text);
    
    StringBuilder appendTable(StringBuilder sb,HashMap<String,String> table);
    
    String bold(String text);
    
    String italic(String text);
    
    StringBuilder appendJson(StringBuilder sb,String json);
    
    String link(Link link);

    String newLine();
}
