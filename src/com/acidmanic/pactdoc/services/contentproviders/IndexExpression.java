/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.services.contentproviders;

import com.acidmanic.pactdoc.services.contentproviders.Link;
import java.util.List;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class IndexExpression {
    
    
    private final PageContext provider;

    public IndexExpression(PageContext provider) {
        this.provider = provider;
    }
    
    
    
    public void interpret(List<Link> links){
        
        for(Link link:links){
            
            provider.newLine().link(link);
        }
        
        provider.newLine();
        
    }
}
