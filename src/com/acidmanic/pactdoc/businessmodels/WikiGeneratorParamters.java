/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.businessmodels;

import com.acidmanic.pactdoc.services.contractindexing.ContractIndexer;
import com.acidmanic.pactdoc.services.wiki.glossary.Glossary;
import com.acidmanic.pactdoc.services.wiki.wikiformat.WikiFormat;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class WikiGeneratorParamters {
    
    private Glossary glossary;
    
    private WikiFormat wikiFormat;
    
    private ContractIndexer indexer;
    
    private String apiBase;
    
    private boolean addFileExtensions;
    
    private boolean referredBaseLinking;
    
    private String output;
    
    private boolean singleDirectory;
    
    private String singleDirectoryDelimiter;
    
    public WikiGeneratorParamters() {
        this.referredBaseLinking = true;
    }

    
    
    public WikiGeneratorParamters(WikiGeneratorParamters paramters) {

        this.glossary=paramters.glossary;
        
        this.wikiFormat=paramters.wikiFormat;

        this.indexer=paramters.indexer;

        this.apiBase=paramters.apiBase;

        this.addFileExtensions=paramters.addFileExtensions;
        
        this.output = paramters.output;
        
        this.referredBaseLinking = paramters.referredBaseLinking;
        
        this.singleDirectory = false;
        
        this.singleDirectoryDelimiter="_";
        
    }

    public Glossary getGlossary() {
        return glossary;
    }

    public void setGlossary(Glossary glossary) {
        this.glossary = glossary;
    }
    
    public WikiFormat getWikiFormat() {
        return wikiFormat;
    }

    public void setWikiFormat(WikiFormat wikiFormat) {
        this.wikiFormat = wikiFormat;
    }

    public ContractIndexer getIndexer() {
        return indexer;
    }

    public void setIndexer(ContractIndexer indexer) {
        this.indexer = indexer;
    }

    public String getApiBase() {
        return apiBase;
    }

    public void setApiBase(String apiBase) {
        this.apiBase = apiBase;
    }

    public boolean isAddFileExtensions() {
        return addFileExtensions;
    }

    public void setAddFileExtensions(boolean addFileExtensions) {
        this.addFileExtensions = addFileExtensions;
    }

    public boolean isReferrerBaseLinking() {
        return this.referredBaseLinking;
    }

    public void setReferredBaseLinking(boolean referredBaseLinking) {
        this.referredBaseLinking = referredBaseLinking;
    }

    public String getOutput() {
        return this.output;
    }
    
    public void setOutput(String output){
        this.output = output;
    }

    public boolean isSingleDirectory() {
        return singleDirectory;
    }

    public void setSingleDirectory(boolean singleDirectory) {
        this.singleDirectory = singleDirectory;
    }

    public String getSingleDirectoryDelimiter() {
        return singleDirectoryDelimiter;
    }

    public void setSingleDirectoryDelimiter(String singleDirectoryDelimiter) {
        this.singleDirectoryDelimiter = singleDirectoryDelimiter;
    }
    
    
}
