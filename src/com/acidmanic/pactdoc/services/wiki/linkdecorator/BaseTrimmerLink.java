/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.services.wiki.linkdecorator;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class BaseTrimmerLink implements Link{

    private final Link origin;
    private final Link newBase;
    
    public BaseTrimmerLink(Link origin,Link newBase) {
        this.origin = origin;
        this.newBase = newBase;
    }
    
    @Override
    public void trimBase(Link base) {
        origin.trimBase(base);
    }

    @Override
    public void baseOn(Link base) {
        origin.baseOn(base);
    }

    @Override
    public void append(String... appending) {
        origin.append(appending);
    }

    @Override
    public String represent() {
        Link clone = this.origin.cloneLink();
        clone.trimBase(newBase);
        return clone.represent();
    }

    @Override
    public String[] getContentKey() {
        return origin.getContentKey();
    }

    @Override
    public Link cloneLink() {
        return new BaseTrimmerLink(this.origin.cloneLink(), newBase);
    }
    
}
