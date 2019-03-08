/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.services.wiki.linkdecorator;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class PathLink extends ContentLink{

    
    
    @Override
    public String represent() {
        String[] key = this.getContentKey();
        if(key==null||key.length==0){
            return "";
        }
        
        Path path = Paths.get(key[0]);
        
        for(int i=1;i<key.length;i++){
            path = path.resolve(key[i]);
        }
        
        return path.toString();
    }
    
}
