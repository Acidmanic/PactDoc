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
        
        this.singleDirectory = paramters.singleDirectory;
        
        this.singleDirectoryDelimiter=paramters.singleDirectoryDelimiter;
        
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
