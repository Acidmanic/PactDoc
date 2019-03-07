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
public class Repository extends CreateWikiArgBase{

    @Override
    protected void update(WikiCommandParameters params) {
        if(!noArguments()){
            params.setRepository(args[0]);
        }
    }

    @Override
    public ArgumentValidationResult validateArguments() {
        if (noArguments()){
            return new ArgumentValidationResult(0);
        }
        return new ArgumentValidationResult(1);
    }

    @Override
    protected String getUsageString() {
        return "This will set repository address of your wiki repo.";
    }

    
    
}
