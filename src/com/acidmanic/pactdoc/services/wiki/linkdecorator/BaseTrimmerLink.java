/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.services.wiki.linkdecorator;

import com.acidmanic.pactdoc.services.contractindexing.ContentKeyHelper;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class BaseTrimmerLink implements FileSystemLink{

    private final Link origin;
    private final String[] base;
    
    public BaseTrimmerLink(FileSystemLink origin,String[] base) {
        this.origin = origin;
        this.base = base;
    }
    
    

    @Override
    public String represent() {
        Path mine =Paths.get(this.origin.represent());
        Path bPath = ContentKeyHelper.getPath(base);
        bPath=bPath.getParent();
        if(bPath==null){
            return mine.toString();
        }
        return bPath.relativize(mine).toString();
    }

    
    
}
