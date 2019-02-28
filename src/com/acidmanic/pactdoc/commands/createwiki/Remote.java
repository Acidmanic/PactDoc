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
public class Remote extends CreateWikiArgBase{

    @Override
    protected void update(CreateWikiParameters params) {
        if(!noArguments()){
            params.setRemote(args[0]);
        }
    }

    @Override
    public ArgumentValidationResult validateArguments() {
        return enoughOrNothing(1);
    }

    @Override
    protected String getUsageString() {
        return "If for any reason, your remote name in wiki git repo would change"
                + " from origin to something else, with this argument, you can "
                + "set this other remote name.";
    }
    
    
    
}
