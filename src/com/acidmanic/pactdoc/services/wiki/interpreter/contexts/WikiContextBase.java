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
package com.acidmanic.pactdoc.services.wiki.interpreter.contexts;

import com.acidmanic.pactdoc.services.contractindexing.ContractIndexer;
import com.acidmanic.pactdoc.services.wiki.keymodifiers.BasicKeyModifier;
import com.acidmanic.pactdoc.services.wiki.keymodifiers.KeyModifier;
import com.acidmanic.pactdoc.services.wiki.linktranslator.LinkTranslator;


/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public abstract class WikiContextBase implements WikiContext{
    
    private final String output;
    private final ContractIndexer indexer;
    private final String apiBase ;
    
    private final boolean singleDirectory;
    private final String singleDirectoryDelimiter;
    
    public WikiContextBase(String output,
            ContractIndexer indexer,
            String apiBase,
            boolean singleDirectory,
            String singleDirectoryDelimiter) {
        this.output = output;
        this.indexer = indexer;
        this.apiBase = apiBase;
        this.singleDirectory = singleDirectory;
        this.singleDirectoryDelimiter = singleDirectoryDelimiter;
    }

    protected ContractIndexer getIndexer(){
        return indexer;
    }
    
    protected String getOutput() {
        return output;
    }
    
    protected String getApiBase(){
        return apiBase;
    }
    
    protected KeyModifier decorateModifier(KeyModifier keyModifier){
        return keyModifier;
    }

    protected boolean isSingleDirectory() {
        return singleDirectory;
    }

    protected String getSingleDirectoryDelimiter() {
        return singleDirectoryDelimiter;
    }
    
    protected abstract LinkTranslator getTranslator();

    protected String makeLinkFor(String[] contentkey) {
        
        KeyModifier modifier = new BasicKeyModifier(contentkey);
        
        decorateModifier(modifier);
        
        return getTranslator().translate(modifier.getKey());
    }
    
}
