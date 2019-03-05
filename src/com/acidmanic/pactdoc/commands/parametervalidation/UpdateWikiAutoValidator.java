/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.commands.parametervalidation;

import com.acidmanic.pactdoc.commands.createwiki.WikiCommandParameters;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class UpdateWikiAutoValidator extends ParameterValidator<WikiCommandParameters> {
    
    
    @Override
    public ValidationResult<WikiCommandParameters> validate(WikiCommandParameters params){
        
        ValidationResult<WikiCommandParameters> ret = new ValidationResult<>();
        
        
        ret.setValidatedValue(params);
        ret.setValid(true);
        
        
        if(empty(params.getPactsRoot())){
            params.setPactsRoot(getCurrentDirectory());
            ret.warning("Pact contract files search directory has been defaulted to: "
                    + params.getPactsRoot());
        }
        
        if(empty(params.getOutputDirectory())){
            params.setOutputDirectory(getCurrentDirectory());
            ret.warning("The work directory has been defaulted to: "
                    + params.getOutputDirectory());
        }
        
        if(empty(params.getRepository())){
            ret.setValid(false);
            ret.error("You should provide the repository address for your wiki repo.");
        }
        
        if(!params.hasValidUserPass()){
            ret.warning("A valid set of Username and password is not provided, "
                    + "so your wiki repository must not need any authentication.");
        }
        
        if(empty(params.getRemote())||params.getRemote().compareTo("origin")==0){
            params.setRemote("origin");
            ret.info("default remote, origin, will be used.");
        }else{
            ret.warning("You changed the default remote, origin to "+
                    params.getRemote()+ ".");
        }
        
        ret.setValidatedValue(params);
        
        return ret;
    }

}
