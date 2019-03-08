/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.services.wiki.linking;

import com.acidmanic.pactdoc.services.contractindexing.IndexHelper;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class FileSystemLinkGenerator implements LinkGenerator{
    
    
    private final IndexHelper indexHelper;
    private final String extension;

    FileSystemLinkGenerator(IndexHelper indexHelper, 
            String extension,
            boolean linksWithExtensions) {
        this.indexHelper = indexHelper;
        if(!linksWithExtensions||extension==null||extension.length()==0){
            this.extension="";
        }else{
            this.extension="."+extension;
        }
        
    }

    
    
    
    public Path getLinkFor(String[] contentKey){
        
        String baseLink = createLink(contentKey);
        
        if(indexHelper.isLeaf(contentKey)){
           return Paths.get(baseLink+extension);
        }else{
            return Paths.get(baseLink).resolve("Index"+extension);
        }
    }

    private String createLink(String[] contentKey) {
        Path path = Paths.get("");
        for(int i=0;i<contentKey.length;i++){
            path=path.resolve(contentKey[i]);
        }
        return path.toString();
    }

    @Override
    public String generateLink(String[] contentKey) {
        return getLinkFor(contentKey).toString();
    }
    
}
