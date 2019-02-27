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
public class APIsSubDir extends CreateWikiArgBase {

    
    @Override
    protected String getUsageString() {
        return "This will set a sub directory, to put documents in it. its usefull, "
                + "for when the API documents are just a sub section of the whole"
                + " wiki.";
    }

  

    @Override
    protected void update(CreateWikiParameters params) {
        if(!noArguments()){
            params.setDocumentsSubDirectory(args[0]);
        }
    }

    @Override
    public ArgumentValidationResult validateArguments() {
        if(!noArguments()){
            return new ArgumentValidationResult(1);    
        }
        return new ArgumentValidationResult(0);
    }
    
    
    
}
