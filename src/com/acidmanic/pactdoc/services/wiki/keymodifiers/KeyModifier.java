/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.services.wiki.keymodifiers;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public interface KeyModifier {
    
    public String[] getKey();
    
    public String[] getOriginalKey();
}