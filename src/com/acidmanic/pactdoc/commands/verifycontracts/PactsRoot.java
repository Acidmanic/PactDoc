/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.commands.verifycontracts;

import acidmanic.commandline.utility.ArgumentValidationResult;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class PactsRoot extends VerifyingArgumentBase{
    @Override
    protected String getUsageString() {
        return "Sets the root directory for pact file search. any pact file "
                + " within this directory or its sub directories will be added"
                + " to wiki.";
    }


    @Override
    public ArgumentValidationResult validateArguments() {
        return enoughOrNothing(1);
    }

    @Override
    protected void update(VerifyingParameters params) {
        if(noArguments()){
            warning("Pacts root for search has been defaulted to " +
                    params.getPactsRoot());
        }else{
            params.setPactsRoot(args[0]);
        }
    }
}
