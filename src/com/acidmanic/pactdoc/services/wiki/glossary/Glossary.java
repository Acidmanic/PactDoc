/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.services.wiki.glossary;

import com.acidmanic.pactdoc.utility.StringArrayKeyMaker;
import java.awt.Desktop;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.function.Consumer;
import javax.swing.Action;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class Glossary {
    
    private final StringArrayKeyMaker keyMaker;
    
    private final ArrayList<String[]> keys;

    private final HashSet<String> hashes;
    
    public Glossary() {
        
        this.keyMaker = new StringArrayKeyMaker();
        
        this.keys = new ArrayList<>();
        
        this.hashes = new HashSet<>();
    }
    
    public void add(String[] contentKey){
        this.keys.add(contentKey);
        String hash = keyMaker.key(contentKey);
        this.hashes.add(hash);
    }
    
    public int size(){
        return this.keys.size();
    }
    
    public boolean contains(String[] contentKey){
        String hash = keyMaker.key(contentKey);
        return this.hashes.contains(hash);
    }
    
    public void clear(){
        this.hashes.clear();
        this.keys.clear();
    }
    
    public void forEach(Consumer<String[]> action){
        this.keys.forEach(action);
    }
    
}
