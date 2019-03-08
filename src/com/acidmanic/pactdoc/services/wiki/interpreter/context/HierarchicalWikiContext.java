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
    
    
    public HierarchicalWikiContext(boolean referrerRelativeLinking, boolean linkWithExtensions) {

        this.referrerRelativeLinking = referrerRelativeLinking;
        
        this.linkWithExtensions = linkWithExtensions;        
    }
    
    
    
    
    

    @Override
    public WikiContext startNewPage(String[] contentKey) {
        this.currentPageKey=contentKey;
        onNewPageStarted(contentKey);
        return this;
    }
    
    
    protected abstract WikiContext onNewPageStarted(String[] contentKey);
    
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
                , "html");
        
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
    
    
    
    
}
