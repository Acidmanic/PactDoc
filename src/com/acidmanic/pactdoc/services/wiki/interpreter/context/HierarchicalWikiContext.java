/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.services.wiki.interpreter.context;

import com.acidmanic.pactdoc.services.contractindexing.ContractIndexer;
import com.acidmanic.pactdoc.services.wiki.linkdecorator.SimplePathLink;
import com.acidmanic.pactdoc.services.wiki.linkdecorator.ExtensionedLink;
import com.acidmanic.pactdoc.services.wiki.linkdecorator.FileSystemLink;
import com.acidmanic.pactdoc.services.wiki.linkdecorator.Link;
import com.acidmanic.pactdoc.services.wiki.linkdecorator.NameDeterminerLink;

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
            ContractIndexer indexer) {
        
        super(output,indexer);
        
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
    
    
    protected Link getInternalLinkFor(String[] contentkey){
        if(this.referrerRelativeLinking){
            return makeReferrerBasedLinkFor(this.currentPageKey, contentkey);
        }else{
            return makeLinkFor(contentkey);
        }
    }
    
    protected Link getPageWriteLinkFor(String[] contentkey){
        
        Link link = new SimplePathLink(contentkey);
                
        link = new NameDeterminerLink((FileSystemLink) link, getIndexer());
        
        link = new ExtensionedLink((FileSystemLink) link, extension);
        
        return link;
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
    
    
}
