/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.commands.parametervalidation;

import com.acidmanic.pactdoc.commands.createwiki.CreateWikiParameters;
import java.io.File;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class UpdateWikiAutoValidator {
    
    
    public ValidationResult<CreateWikiParameters> validate(CreateWikiParameters params){
        
        ValidationResult<CreateWikiParameters> ret = new ValidationResult<>();
        
        
        ret.setValidatedValue(params);
        ret.setValid(true);
        
        
        if(empty(params.getPactsRoot())){
            params.setPactsRoot(new File(".").toPath().normalize().toString());
            ret.warning("Pact contract files search directory has been defaulted to: "
                    + params.getPactsRoot());
        }
        
        if(empty(params.getOutputDirectory())){
            params.setOutputDirectory(new File(".").toPath().normalize().toString());
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

    private boolean empty(String value) {
        return value==null || value.length()==0;
    }
}
