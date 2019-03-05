/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.businessmodels;

import com.acidmanic.pactdoc.services.contractindexing.ContractIndexer;
import com.acidmanic.pactdoc.services.wiki.linking.LinkGenerator;
import com.acidmanic.pactdoc.services.wiki.linking.LinkingStrategy;
import com.acidmanic.pactdoc.services.wiki.wikiformat.WikiFormat;
import java.util.List;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class WikiGeneratingParamters {
    
        
    
    private LinkingStrategy linkingStrategy;
    
    private List<String[]> glossary;
    
    private LinkGenerator linkGenerator;
    
    private WikiFormat wikiFormat;
    
    private ContractIndexer indexer;
    
    private String apiBase;
    
    private boolean addFileExtensions;

    public WikiGeneratingParamters() {
    }

    
    
    public WikiGeneratingParamters(WikiGeneratingParamters paramters) {
        this.linkingStrategy=paramters.linkingStrategy;

        this.glossary=paramters.glossary;

        this.linkGenerator=paramters.linkGenerator;

        this.wikiFormat=paramters.wikiFormat;

        this.indexer=paramters.indexer;

        this.apiBase=paramters.apiBase;

        this.addFileExtensions=paramters.addFileExtensions;
        
    }

    public LinkingStrategy getLinkingStrategy() {
        return linkingStrategy;
    }

    public void setLinkingStrategy(LinkingStrategy linkingStrategy) {
        this.linkingStrategy = linkingStrategy;
    }

    public List<String[]> getGlossary() {
        return glossary;
    }

    public void setGlossary(List<String[]> glossary) {
        this.glossary = glossary;
    }

    public LinkGenerator getLinkGenerator() {
        return linkGenerator;
    }

    public void setLinkGenerator(LinkGenerator linkGenerator) {
        this.linkGenerator = linkGenerator;
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
}
