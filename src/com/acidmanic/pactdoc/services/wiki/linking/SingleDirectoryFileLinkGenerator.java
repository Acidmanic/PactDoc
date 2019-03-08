/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.services.wiki.linking;

import com.acidmanic.pactdoc.services.contractindexing.ContentKeyHelper;
import com.acidmanic.pactdoc.services.contractindexing.IndexHelper;
import com.acidmanic.pactdoc.services.wiki.wikiformat.WikiFormat;
import java.nio.file.Paths;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class SingleDirectoryFileLinkGenerator implements LinkGenerator{
    
    private final SingleDirectoryContentLinkGenerator bareLinkGenerator;
    private final IndexHelper indexHelper;
    private final String extension;
    
    public SingleDirectoryFileLinkGenerator(String delimiter,
            WikiFormat format,
            IndexHelper indexHelper) {
        bareLinkGenerator = new SingleDirectoryContentLinkGenerator(delimiter);
        
        this.indexHelper = indexHelper;
        
        this.extension="."+format.getFilesExtension();
    }

    @Override
    public String generateLink(String[] contentKey) {
        
        String[] key = contentKey;
        
        if(!indexHelper.isLeaf(contentKey)){
           key = ContentKeyHelper.append(contentKey, "Index");
        }
        
        String keyName = bareLinkGenerator.generateLink(key);
        
        return keyName + this.extension;
    }
    
    
    
    
}
