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
public class SingleDirectoryContentLinkGenerator  implements LinkGenerator{

    
    private final String delimiter;

    SingleDirectoryContentLinkGenerator(String delimiter) {
        this.delimiter = delimiter;
    }

    
    
    @Override
    public String generateLink(String[] contentKey) {
        StringBuilder sb = new StringBuilder();
        String sep="";
        for(String key:contentKey){
            sb.append(sep).append(key);
            sep=delimiter;
        }
        return sb.toString();
    }
    
}
