/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.commands.createwiki;

import com.acidmanic.pactdoc.businessmodels.WikiCommandParameters;
import acidmanic.commandline.utility.ArgumentValidationResult;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class RootRelativeLinks extends CreateWikiArgBase{

    @Override
    protected void update(WikiCommandParameters params) {
        params.setRootRelativeLinks(true);
    }

    @Override
    protected String getUsageString() {
        return "Using this argument, will cause the geenrate wiki to use links"
                + " based on wiki-root instead of forward relative links in the"
                + " Index files.";
    }

    @Override
    public ArgumentValidationResult validateArguments() {
        return new ArgumentValidationResult(0);
    }
    
    
    
    
    
    
}
