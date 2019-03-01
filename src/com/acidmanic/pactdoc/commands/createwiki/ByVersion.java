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
public class ByVersion extends CreateWikiArgBase{

    @Override
    protected void update(CreateWikiParameters params) {
        params.setByVersion(true);
    }

    @Override
    public ArgumentValidationResult validateArguments() {
        return new ArgumentValidationResult(0);
    }
    
    
    
}
