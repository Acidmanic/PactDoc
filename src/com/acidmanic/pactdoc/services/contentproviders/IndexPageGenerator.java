/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.services.contentproviders;

import java.util.List;
import com.acidmanic.pactdoc.services.pages.PageItemsProvider;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class IndexPageGenerator {
    
    
    private final PageItemsProvider provider;

    public IndexPageGenerator(PageItemsProvider provider) {
        this.provider = provider;
    }
    
    
    
    public String generate(List<Link> links){
        StringBuilder sb = new StringBuilder();
        
        for(Link link:links){
            sb.append(provider.link(link))
                    .append(provider.newLine());
        }
        
        return sb.toString();
        
    }
}
