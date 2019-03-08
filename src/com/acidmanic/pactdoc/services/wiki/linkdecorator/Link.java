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
public interface Link {
    
    public void trimBase(Link base);
    
    public void baseOn(Link base);
    
    
    String represent();
    
    String[] getContentKey();
    
}
