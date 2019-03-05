/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.services.wiki.linking;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class RelativeLinkingStrategy implements LinkingStrategy {
    
    
    


    @Override
    public String getLink(String refferer, String target) {
        return target;
    }
    
}
