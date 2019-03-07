/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.commands.parametervalidation;

import com.acidmanic.pactdoc.businessmodels.WikiCommandParameters;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class UpdateWikiAutoValidator extends CreateWikiParameterValidatorBase {

    @Override
    public ValidationResult<WikiCommandParameters> validate(WikiCommandParameters params) {
        
        ValidationResult<WikiCommandParameters> ret =  super.validate(params);
        
        if (empty(params.getRepository())) {
            ret.setValid(false);
            ret.error("You should provide the repository address for your wiki repo.");
        }
        
        if (!params.hasValidUserPass()) {
            ret.warning("A valid set of Username and password is not provided, " + "so your wiki repository must not need any authentication.");
        }
        
        if (empty(params.getRemote()) || params.getRemote().compareTo("origin") == 0) {
            params.setRemote("origin");
            ret.info("default remote, origin, will be used.");
        } else {
            ret.warning("You changed the default remote, origin to " + params.getRemote() + ".");
        }
        
        return ret;
    }
    
    
    
    
    
    
    
}
