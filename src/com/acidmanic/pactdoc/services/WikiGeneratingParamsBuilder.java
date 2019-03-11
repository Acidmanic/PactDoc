/* 
 * The MIT License
 *
 * Copyright 2019 Mani Moayedi (acidmanic.moayedi@gmail.com).
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
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
    
    
    public WikiGeneratingParamsBuilder singleDirectory(boolean singleDirectory){
        this.paramters.setSingleDirectory(singleDirectory);
        return this;
    }
    
    public WikiGeneratingParamsBuilder withDelimiter(String singleDirectoryDelimiter){
        this.paramters.setSingleDirectoryDelimiter(singleDirectoryDelimiter);
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
                    .withOutput(parameters.getOutputDirectory())
                    .singleDirectory(parameters.isSingleDirectory())
                    .withDelimiter(parameters.getSingleDirectoryDelimiter());
    }
    
    
    public WikiGeneratorParamters build(){
        return new WikiGeneratorParamters(this.paramters);
    }

    private WikiGeneratingParamsBuilder withReferrerBaseLinks(boolean rootRelativeLinks) {
        this.paramters.setReferredBaseLinking(rootRelativeLinks);
        return this;
    }
    
}
