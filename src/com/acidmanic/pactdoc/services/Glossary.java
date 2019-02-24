/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.services;

import java.util.HashMap;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class Glossary {
    
    
    private final HashMap<String,Object> contentKeys;
    private final HashMap<Object,String> links;

    public Glossary() {
        this.contentKeys = new HashMap<>();
        this.links = new HashMap<>();
    
    }
    
    public void put(String link, Object contentKey ){
        if(!this.contentKeys.containsKey(link))
            if(!this.links.containsKey(contentKey)){
                this.contentKeys.put(link, contentKey);
                this.links.put(contentKey, link);
            }
    }
    
    public <T> String link(T contentKey){
        if(this.links.containsKey(contentKey)){
            return this.links.get(contentKey);
        }
        return null;
    }
    
    public <T> T contentKey(String link){
        if(this.contentKeys.containsKey(link)){
            return (T) this.contentKeys.get(link);
        }
        return null;
    }
    
    public void scan(GlossaryScanner scanner){
        for(String link:this.contentKeys.keySet()){
            scanner.scan(link, contentKeys.get(link));
        }
    }
    
}
