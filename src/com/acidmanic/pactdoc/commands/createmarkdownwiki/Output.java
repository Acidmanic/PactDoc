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
public class Output extends MarkdownWikiArgBase {


    @Override
    protected String getUsageString() {
        return "Selects a directory to put all generated docs in it.";
    }

    @Override
    protected void update(MarkdownWikiParameters params) {
        if(noArguments()){
            log("Output directory has been defaulted to " + params.getOutputDirectory());
        }else{
            params.setOutputDirectory(args[0]);
        }
    }

    @Override
    public ArgumentValidationResult validateArguments() {
        if(noArguments()){
            return new ArgumentValidationResult(0);
        }else{
            return new ArgumentValidationResult(1);
        }
    }
    
    
    
}
