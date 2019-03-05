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
public class ReffererRelativeLinkingStrategy implements LinkingStrategy{
    
    @Override
    public String getLink(String target,String refferer) {
        Path path = Paths.get(target);
        
        Path refPath = Paths.get(refferer).getParent();
        
        if(refPath==null){
            return target;
        }
        
        path=refPath.relativize(path);
        
        
        return path.toString();
    }
    
}
