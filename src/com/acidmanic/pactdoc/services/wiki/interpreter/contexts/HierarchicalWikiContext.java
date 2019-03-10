/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.services.wiki.interpreter.contexts;

import com.acidmanic.pactdoc.services.contractindexing.ContractIndexer;
import com.acidmanic.pactdoc.services.contractindexing.IndexHelper;
import com.acidmanic.pactdoc.services.wiki.keymodifiers.BackOnceKeyModifier;
import com.acidmanic.pactdoc.services.wiki.keymodifiers.BaseOnKeyModifier;
import com.acidmanic.pactdoc.services.wiki.keymodifiers.RelativizerKeyModifier;
import com.acidmanic.pactdoc.services.wiki.keymodifiers.BasicKeyModifier;
import com.acidmanic.pactdoc.services.wiki.keymodifiers.IndexAppenderKeyModifier;
import com.acidmanic.pactdoc.services.wiki.keymodifiers.KeyModifier;
import com.acidmanic.pactdoc.services.wiki.linktranslator.FileSystemPathTranslator;
import com.acidmanic.pactdoc.services.wiki.linktranslator.LinkTranslator;
import com.acidmanic.pactdoc.services.wiki.linktranslator.SingleDirectoryLinkTranslator;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public abstract class HierarchicalWikiContext extends WikiContextBase {
    
    
    private final boolean referrerRelativeLinking;
    
    private final boolean linkWithExtensions;

    private String[] currentPageKey = null;
    
    private final String extension;
    
    public HierarchicalWikiContext(boolean referrerRelativeLinking, 
            boolean linkWithExtensions,
            String extension,
            String output,
            ContractIndexer indexer,
            String apiBase,
            boolean singleDirectory,
            String singleDirectoryDelimiter) {
        
        super(output,indexer,apiBase,singleDirectory,singleDirectoryDelimiter);
        
        this.referrerRelativeLinking = referrerRelativeLinking;
        
        this.linkWithExtensions = linkWithExtensions;        
        
        this.extension = extension;
    }
    
    

    @Override
    public WikiContext startNewPage(String[] contentKey) {
        
        if(anyPageToDeliver()){
            deliverThisPage(this.currentPageKey);
        }
        this.currentPageKey=contentKey;
        
        initializeContextForNewPage();
        
        return this;
    }
    
    
    protected boolean anyPageToDeliver(){
        return this.currentPageKey!=null;
    }
    
    protected abstract void deliverThisPage(String[] currentPageContentKey);
    
    protected abstract void initializeContextForNewPage();
    
    
    private KeyModifier getBaseRelativeModifierFor(String[] key){
        
        KeyModifier modifier = new BasicKeyModifier(key);
        
        String[] base = {getApiBase()};
        
        modifier = new BaseOnKeyModifier(modifier, base);
        
        decorateModifier(modifier);
        
        modifier = new IndexAppenderKeyModifier(modifier, new IndexHelper(getIndexer()));
        
        return modifier;
    }
    
    
    protected String getInternalLinkFor(String[] contentkey){
        
        KeyModifier modifier = getBaseRelativeModifierFor(contentkey);
        
        if(this.referrerRelativeLinking){
            
            KeyModifier base = getBaseRelativeModifierFor(getCurrentPageKey());
            
            base = new BackOnceKeyModifier(base);
            
            modifier = new RelativizerKeyModifier(modifier, base.getKey());
        }
        
        return getTranslator().translate(modifier.getKey());
    }
    
    protected String getPageWriteLinkFor(String[] contentkey){
        
        KeyModifier modifier = new BasicKeyModifier(contentkey);
        
        String apiBase = getApiBase();
        
        if(apiBase!=null&&apiBase.length()>0){
            modifier = new BaseOnKeyModifier(modifier, new String[]{apiBase});    
        }
        
        modifier = new IndexAppenderKeyModifier(modifier, new IndexHelper(getIndexer()));
        
        modifier = decorateModifier(modifier);
        
        String outputRelativePath = makeTranslator(true).translate(modifier.getKey());
        
        Path path = Paths.get(getOutput());
        
        return path.resolve(outputRelativePath).toAbsolutePath().normalize().toString();
    }

    protected boolean isReferrerRelativeLinking() {
        return referrerRelativeLinking;
    }

    protected boolean isLinkWithExtensions() {
        return linkWithExtensions;
    }

    protected String[] getCurrentPageKey() {
        return currentPageKey;
    }
    
    @Override
    public void output() {
        if(anyPageToDeliver()){
            deliverThisPage(this.currentPageKey);
        }
    }
    
    @Override
    protected LinkTranslator getTranslator() {
        return makeTranslator(this.isLinkWithExtensions());
    }
    
    private LinkTranslator makeTranslator(boolean withExtension){
        if(this.isSingleDirectory()){
            return new SingleDirectoryLinkTranslator(this.extension,
                    withExtension, this.getSingleDirectoryDelimiter());
        }else{
            return new FileSystemPathTranslator(extension, 
                withExtension);
        }
    }
    
}
