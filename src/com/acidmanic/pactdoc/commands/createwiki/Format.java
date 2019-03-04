/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.commands.createwiki;

import acidmanic.commandline.utility.ArgumentValidationResult;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class Format extends CreateWikiArgBase{

    @Override
    protected void update(CreateWikiParameters params) {
        if(!noArguments()){
            params.setWikiFormat(args[0]);
        }
    }

    @Override
    protected String getUsageString() {
        return "This can get values 'Markdown' or 'Html'. Therefore, the "
                + "resulting wiki documentation, will be of Markdown, or html";
    }

    @Override
    public ArgumentValidationResult validateArguments() {
        if(!noArguments(1)){
            return enoughOrNothing(1);
        }
        return anyAvailable();
    }
    
    
    
    
    
}
