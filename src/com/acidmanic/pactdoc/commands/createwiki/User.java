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
public class User extends CreateWikiArgBase {

    @Override
    protected void update(WikiCommandParameters params) {
        if(!noArguments()){
            params.setUsername(args[0]);
        }
    }

    @Override
    public ArgumentValidationResult validateArguments() {
        return enoughOrNothing(1);
    }

    @Override
    protected String getUsageString() {
        return "If an authentication with username and password is needed to "
                + "work with the wiki repository, with this argument you can "
                + " set the username. you should use " + new Pass().getName() 
                + " argument to set the password.";
    }
    
    
    
    
}
