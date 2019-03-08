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
import com.acidmanic.pactdoc.services.wiki.linking.LinkGenerator;
import com.acidmanic.pactdoc.services.wiki.linking.LinkGeneratorFactory;
import com.acidmanic.pactdoc.services.wiki.linking.LinkingStrategy;
import com.acidmanic.pactdoc.services.wiki.linking.LinkingStrategyFactory;
import com.acidmanic.pactdoc.services.wiki.wikiformat.WikiFormat;
import com.acidmanic.pactdoc.services.wiki.wikiformat.WikiformatFactory;
import static com.acidmanic.pactdoc.utility.PactFiles.scanForAllContracts;
import static com.acidmanic.pactdoc.utility.PactFiles.scanForAllContracts;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class WikiGeneratingParamsBuilder {
    
    private final WikiGeneratorParamters paramters = new WikiGeneratorParamters();
    
    
    public WikiGeneratingParamsBuilder withStrategy(LinkingStrategy linkingStrategy){
        this.paramters.setLinkingStrategy(linkingStrategy);
        return this;
    }
    
    public WikiGeneratingParamsBuilder withLinkGenerator(LinkGenerator linkGenerator){
        this.paramters.setLinkGenerator(linkGenerator);
        return this;
    }
    
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
    
    
    public WikiGeneratingParamsBuilder withWritingLinkGenerator(LinkGenerator generator){
        this.paramters.setWritingLinkGenerator(generator);
        
        return this;
        
    }
    
    public WikiGeneratingParamsBuilder withCommandParamters(WikiCommandParameters parameters){
        ContractIndexer indexer = new ContractIndexer(parameters
                    .getPropertyProvider().makeProperties());
            
            scanForAllContracts(parameters.getPactsRoot(),indexer);

            WikiFormat format = new WikiformatFactory().create(parameters.getWikiFormat());
            
            LinkGeneratorFactory factory = new LinkGeneratorFactory(indexer); 
                        
            LinkGenerator linkGenerator = factory.createForContent(parameters);
            
            LinkGenerator writingLinkGenerator = factory.createForFiles(parameters);
            
            LinkingStrategy linkingStrategy = new LinkingStrategyFactory()
                    .create(parameters.isRootRelativeLinks(),
                            parameters.getDocumentsSubDirectory(),
                            format);
            
            
            return this.withApiBase(parameters.getDocumentsSubDirectory())
                    .withFilesHavingExtension(parameters.isLinksWithExtensions())
                    .withFormat(format)
                    .withIndexer(indexer)
                    .withLinkGenerator(linkGenerator)
                    .withWritingLinkGenerator(writingLinkGenerator)
                    .withStrategy(linkingStrategy);
    }
    
    
    public WikiGeneratorParamters build(){
        return new WikiGeneratorParamters(this.paramters);
    }
    
}
