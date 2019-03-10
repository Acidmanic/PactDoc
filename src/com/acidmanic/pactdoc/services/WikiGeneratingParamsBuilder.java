/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.services;

import com.acidmanic.pactdoc.businessmodels.WikiGeneratorParamters;
import com.acidmanic.pactdoc.businessmodels.WikiCommandParameters;
import com.acidmanic.pactdoc.services.contractindexing.ContractIndexer;
import com.acidmanic.pactdoc.services.wiki.glossary.Glossary;
import com.acidmanic.pactdoc.services.wiki.glossary.GlossaryGenerator;
import com.acidmanic.pactdoc.services.wiki.wikiformat.WikiFormat;
import com.acidmanic.pactdoc.services.wiki.wikiformat.WikiformatFactory;
import static com.acidmanic.pactdoc.utility.PactFiles.scanForAllContracts;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class WikiGeneratingParamsBuilder {
    
    private final WikiGeneratorParamters paramters = new WikiGeneratorParamters();
    
    public WikiGeneratingParamsBuilder withFormat(WikiFormat format){
        this.paramters.setWikiFormat(format);
        return this;
    }
    
    public WikiGeneratingParamsBuilder withApiBase(String apibase){
        this.paramters.setApiBase(apibase);
        return this;
    }
    
    public WikiGeneratingParamsBuilder withFilesHavingExtension(boolean  addExtension){
        this.paramters.setAddFileExtensions(addExtension);
        return this;
    }
    
    public WikiGeneratingParamsBuilder withFormat(String wikiFormatName){
        this.paramters.setWikiFormat(new WikiformatFactory().create(wikiFormatName));
        return this;
    }
    
    public WikiGeneratingParamsBuilder withIndexer(ContractIndexer indexer){
        this.paramters.setIndexer(indexer);
        
        Glossary glossary = new GlossaryGenerator(indexer).generate();
        
        this.paramters.setGlossary(glossary);
        
        return this;
        
    }
    
    public WikiGeneratingParamsBuilder withOutput(String output){
        this.paramters.setOutput(output);
        return this;
    }
    
    public WikiGeneratingParamsBuilder withCommandParamters(WikiCommandParameters parameters){
        ContractIndexer indexer = new ContractIndexer(parameters
                    .getPropertyProvider().makeProperties());
            
            scanForAllContracts(parameters.getPactsRoot(),indexer);

            WikiFormat format = new WikiformatFactory().create(parameters.getWikiFormat());
            
            return this.withApiBase(parameters.getDocumentsSubDirectory())
                    .withFilesHavingExtension(parameters.isLinksWithExtensions())
                    .withFormat(format)
                    .withIndexer(indexer)
                    .withReferrerBaseLinks(!parameters.isRootRelativeLinks())
                    .withOutput(parameters.getOutputDirectory());
    }
    
    
    public WikiGeneratorParamters build(){
        return new WikiGeneratorParamters(this.paramters);
    }

    private WikiGeneratingParamsBuilder withReferrerBaseLinks(boolean rootRelativeLinks) {
        this.paramters.setReferredBaseLinking(rootRelativeLinks);
        return this;
    }
    
}
