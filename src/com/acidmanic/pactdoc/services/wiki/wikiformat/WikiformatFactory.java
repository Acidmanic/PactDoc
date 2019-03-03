/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.services.wiki.wikiformat;

import java.util.HashMap;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class WikiformatFactory {
    
    
    private final HashMap<String,WikiFormat> formats;
    
    
    public WikiformatFactory() {
    
        this.formats = new HashMap<>();
    
        putFormat(WikiFormats.MARKDOWN);
    }
    
    
    public WikiFormat create(String name){
        String key = name.toLowerCase();
        if(formats.containsKey(key)){
            return formats.get(key);
        }
        
        return WikiFormats.MARKDOWN;
    }

    private void putFormat(WikiFormat format) {
        this.formats.put(format.getName().toLowerCase(), format);
    }
    
    
    
}
