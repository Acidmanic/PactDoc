/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.services.wiki.linking;

import com.acidmanic.pactdoc.services.wiki.wikiformat.WikiFormat;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class LinkingStrategyFactory {
    
    
    public LinkingStrategy create(boolean rootRelated,
            String apisBase,
            WikiFormat format){
        
        if(rootRelated){
            return new RootRelativeLinkingStrategy(apisBase);
        }
        
        return new ReffererRelativeLinkingStrategy();
        
    }
}
