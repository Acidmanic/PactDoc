/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.services.wiki.linking;

import com.acidmanic.pactdoc.businessmodels.WikiCommandParameters;
import com.acidmanic.pactdoc.services.contractindexing.ContractIndexer;
import com.acidmanic.pactdoc.services.contractindexing.IndexHelper;
import com.acidmanic.pactdoc.services.wiki.wikiformat.WikiFormat;
import com.acidmanic.pactdoc.services.wiki.wikiformat.WikiformatFactory;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class LinkGeneratorFactory {
    
    private final IndexHelper indexHelper;
    

    public LinkGeneratorFactory(ContractIndexer indexer) {
        
        this.indexHelper = new IndexHelper(indexer);
    }
    
    
    
    
    
    public LinkGenerator create(WikiCommandParameters parameters){
        
        if(parameters.isSingleDirectory()){
            
            return new SingleDirectoryLinkGenerator(parameters.getSingleDirectoryDelimiter());
        }
        
        WikiFormat format = new WikiformatFactory().create(parameters.getWikiFormat());
        
        return new FileSystemLinkGenerator(indexHelper,
                format.getFilesExtension(),
                parameters.isLinksWithExtensions());
                
    }
}
