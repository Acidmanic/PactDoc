/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.services.wiki.linkdecorator;

import com.acidmanic.pactdoc.services.contractindexing.ContentKeyHelper;
import com.acidmanic.pactdoc.services.contractindexing.ContractIndexer;
import com.acidmanic.pactdoc.services.contractindexing.IndexHelper;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class NameDeterminerLink implements FileSystemLink{
    
    private final FileSystemLink origin;
    private final IndexHelper indexHelper;
    
    public NameDeterminerLink(FileSystemLink origin,ContractIndexer indexer) {
        this.origin = origin;
        this.indexHelper = new IndexHelper(indexer);
    }
    
    @Override
    public String represent() {
        
        Path path = Paths.get(this.origin.represent());
        
        String[] key = ContentKeyHelper.getKey(path);
                
        if(!indexHelper.isLeaf(key)){
            path=path.resolve("Index");
        }
        
        return path.toString();
    }

    
}
