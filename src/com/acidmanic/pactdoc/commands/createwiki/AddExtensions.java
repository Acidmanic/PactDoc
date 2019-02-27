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
public class AddExtensions extends CreateWikiArgBase{

    @Override
    protected void update(CreateWikiParameters params) {
        params.setExtensionForMarkDownFiles(true);
    }

    @Override
    protected String getUsageString() {
        return "By providing this argument, generated Markdown files will have "
                + ".md extrnsion.";
    }

    @Override
    public ArgumentValidationResult validateArguments() {
        return new ArgumentValidationResult(0);
    }

    

    
    
    
}
