/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.services;

import com.acidmanic.pactdoc.models.Contract;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class GitLabWikiGenerator extends WikiGeneratorBase{
    
    
    private final ContractIndexer indexer;
    private final String linksBase;

    public GitLabWikiGenerator(ContractIndexer indexer, String linkBase) {
        this.indexer = indexer;
        this.linksBase = linkBase;
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
        return false;
    }

    @Override
    protected ContentProvider getContentProvider() {
        return new MarkDownContentProvider(indexer);
    }
    
    
    
    
    
}
