/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.services.wikigenerators;

import com.acidmanic.pactdoc.services.wikigenerators.WikiGeneratorBase;
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
    
    private boolean generateFilesWithExtension;

    public MarkdownWikiGenerator(ContractIndexer indexer, String linksBase, boolean generateFilesWithExtension) {
        this.indexer = indexer;
        this.linksBase = linksBase;
        this.generateFilesWithExtension = generateFilesWithExtension;
    }

    public MarkdownWikiGenerator(ContractIndexer indexer, String linksBase) {
        this.indexer = indexer;
        this.linksBase = linksBase;
        this.generateFilesWithExtension=false;
    }
    


    private Glossary makeGlossary(ContractIndexer indexer,String linksBase) {
        
        Glossary glossary = new Glossary();
        
        Path base = Paths.get(linksBase);
        
        /*      Add home        */
        
        glossary.put(base.resolve("Index").toString(), "");
        
        /*  Add services links  */
        for(String service:indexer.getServices()){
            glossary.put( base.resolve(service).toString()
                    , service);
        }
        
        /*  Add contracts   */
        for(String service:indexer.getServices()){
            for(Contract contract:indexer.getContracts(service)){
                glossary.put(base.resolve(contract.getProvider()
                        .getName()).toString(), contract);
            }
        }
        
        return glossary;
    }

    @Override
    protected Glossary createGlossary() {
        return makeGlossary(indexer, linksBase);
    }

    @Override
    protected boolean linksContainFileExtensions() {
        return generateFilesWithExtension;
    }

    @Override
    protected ContentProvider getContentProvider() {
        return new MarkdownContentProvider(indexer);
    }

    public boolean isGenerateFilesWithExtension() {
        return generateFilesWithExtension;
    }

    public void setGenerateFilesWithExtension(boolean generateFilesWithExtension) {
        this.generateFilesWithExtension = generateFilesWithExtension;
    }
    
    
    
    
    
}
