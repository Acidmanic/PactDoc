/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.commands.createmarkdownwiki;

import acidmanic.commandline.utility.ArgumentValidationResult;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class DocumentsSubDirectory extends MarkdownWikiArgBase {

    
    @Override
    protected String getUsageString() {
        return "This will set a sub directory, to put documents in it. its usefull, "
                + "for when the API documents are just a sub section of the whole"
                + " wiki.";
    }

  

    @Override
    protected void update(MarkdownWikiParameters params) {
        params.setDocumentsSubDirectory(args[0]);
    }

    @Override
    public ArgumentValidationResult validateArguments() {
        return new ArgumentValidationResult(1);
    }
    
    
    
}
