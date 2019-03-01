/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.services;

import com.acidmanic.pactdoc.utility.StringArrayKeyMaker;
import java.util.HashMap;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class Glossary {
    
    
    private final HashMap<String,Object> contentKeys;
    private final HashMap<String,String> links;
    private final StringArrayKeyMaker keyMaker;
    
    public Glossary() {
        this.contentKeys = new HashMap<>();
        this.links = new HashMap<>();
        this.keyMaker = new StringArrayKeyMaker();
    }
    
    public void put(String link, String[] contentKey ){
        if(!this.contentKeys.containsKey(link)){
            String hashed = this.keyMaker.key(contentKey);
            if(!this.links.containsKey(hashed)){
                this.contentKeys.put(link, contentKey);
                this.links.put(hashed, link);
            }
        }
    }
    
    public String link(String[] contentKey){
        String hashed = this.keyMaker.key(contentKey);
        if(this.links.containsKey(hashed)){
            return this.links.get(hashed);
        }
        return null;
    }
    
    public String[] contentKey(String link){
        if(this.contentKeys.containsKey(link)){
            return (String[]) this.contentKeys.get(link);
        }
        return null;
    }
    
    public void scan(GlossaryScanner scanner){
        for(String link:this.contentKeys.keySet()){
            scanner.scan(link, (String[]) contentKeys.get(link));
        }
    }
    
}
