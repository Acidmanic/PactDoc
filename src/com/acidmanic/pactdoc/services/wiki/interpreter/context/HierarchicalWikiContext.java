/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.services.wiki.interpreter.context;

import com.acidmanic.pactdoc.services.wiki.linkdecorator.ContentLink;
import com.acidmanic.pactdoc.services.wiki.linkdecorator.ExtensionedLink;
import com.acidmanic.pactdoc.services.wiki.linkdecorator.Link;
import com.acidmanic.pactdoc.services.wiki.linkdecorator.PathLink;

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
            String extension) {

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
        
        Link link = new ContentLink(contentkey);
        
        link = new ExtensionedLink(
                new PathLink(link)
                , extension);
        
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
