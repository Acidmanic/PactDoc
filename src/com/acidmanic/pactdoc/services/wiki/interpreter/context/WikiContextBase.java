/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.services.wiki.interpreter.context;

import com.acidmanic.pactdoc.services.wiki.linkdecorator.ContentLink;
import com.acidmanic.pactdoc.services.wiki.linkdecorator.Link;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public abstract class WikiContextBase implements WikiContext{
    
    private final String output;

    public WikiContextBase(String output) {
        this.output = output;
    }

    public String getOutput() {
        return output;
    }
    
    protected Link makeLinkFor(String[] contentKey){
        return decorateLink(new ContentLink(contentKey)); 
    }
    
    protected Link makeReferrerBasedLinkFor(String[] referrerKey,
            String[] targetKey){
        Link ret = decorateLink(new ContentLink(targetKey));
        ret.trimBase(new ContentLink(referrerKey));
        return ret;
    }
    
    protected abstract Link decorateLink(ContentLink link);
    
    
}
