/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.services.wikigenerators;

import com.acidmanic.pactdoc.services.contentproviders.MarkdownContentProvider;
import com.acidmanic.pactdoc.services.contentproviders.ContentProvider;
import com.acidmanic.pactdoc.models.Contract;
import com.acidmanic.pactdoc.services.ContractIndexer;
import com.acidmanic.pactdoc.services.Glossary;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class MarkdownWikiGenerator extends WikiGeneratorBase{
    
    
    private final ContractIndexer indexer;
    private final String linksBase;
    private boolean  linksEndWithFileExtionsion;

    public MarkdownWikiGenerator(ContractIndexer indexer, String linksBase, boolean generateFilesWithExtension) {
        this.indexer = indexer;
        this.linksBase = linksBase;
        this.linksEndWithFileExtionsion = generateFilesWithExtension;
    }

    public MarkdownWikiGenerator(ContractIndexer indexer, String linksBase) {
        this.indexer = indexer;
        this.linksBase = linksBase;
        this.linksEndWithFileExtionsion=false;
    }
    


    private Glossary makeGlossary(ContractIndexer indexer,String linksBase) {
        
        Glossary glossary = new Glossary();
        
        Path base = Paths.get(linksBase);
        
        String extension=linksEndWithFileExtionsion?".md":"";
        
        /*      Add home        */
        
        glossary.put(base.resolve("Index"+extension).toString(), "");
        
        /*  Add services links  */
        for(String service:indexer.getServices()){
            glossary.put( base.resolve(service+extension).toString()
                    , service);
        }
        
        /*  Add contracts   */
        for(String service:indexer.getServices()){
            for(Contract contract:indexer.getContracts(service)){
                glossary.put(base.resolve(contract.getProvider()
                        .getName()+extension).toString(), contract);
            }
        }
        
        return glossary;
    }

    @Override
    protected Glossary createGlossary() {
        return makeGlossary(indexer, linksBase);
    }

    @Override
    protected boolean isLinksEndWithFileExtionsion() {
        return linksEndWithFileExtionsion;
    }

    @Override
    protected ContentProvider getContentProvider() {
        return new MarkdownContentProvider(indexer);
    }


    public void setLinksEndWithFileExtionsion(boolean linksEndWithFileExtionsion) {
        this.linksEndWithFileExtionsion = linksEndWithFileExtionsion;
    }
    
    
    
    
    
}
