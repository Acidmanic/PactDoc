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
public class PactsRoot extends CreateWikiArgBase{

    
    @Override
    protected String getUsageString() {
        return "Sets the root directory for pact file search. any pact file "
                + " within this directory or its sub directories will be added"
                + " to wiki.";
    }

    @Override
    protected void update(WikiCommandParameters params) {
        if(noArguments()){
            warning("Pacts root for search has been defaulted to " +
                    params.getPactsRoot());
        }else{
            params.setPactsRoot(args[0]);
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
