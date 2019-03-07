/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.services.wiki.linking;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class RootRelativeLinkingStrategy implements LinkingStrategy {
    
    
    private final Path parent;

    public RootRelativeLinkingStrategy(String apiBase) {
        if(apiBase==null||apiBase.length()==0){
            parent=null;
        }else{
            parent=Paths.get(apiBase);
        }
    }


    @Override
    public String getLink(String refferer, String target) {
        if(this.parent==null){
            return target;
        }
        return this.parent.resolve(target).toString();
    }
    
}
