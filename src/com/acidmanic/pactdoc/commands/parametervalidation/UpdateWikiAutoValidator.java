/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.commands.parametervalidation;

import com.acidmanic.pactdoc.commands.createwiki.CreateWikiParameters;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class UpdateWikiAutoValidator {
    
    
    public ValidationResult<CreateWikiParameters> validate(CreateWikiParameters params){
        
        ValidationResult<CreateWikiParameters> ret = new ValidationResult<>();
        
        // validation logic
        ret.setValidatedValue(params);
        
        ret.setValid(true);
        
        return ret;
    }
}
