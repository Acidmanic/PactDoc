/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.commands.verifycontracts;

import acidmanic.commandline.utility.ArgumentValidationResult;
import com.acidmanic.pactdoc.plugin.PluginLoader;
import com.acidmanic.pactdoc.services.contractverification.ContractVerifier;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class PlugVerifier extends VerifyingArgumentBase{

    @Override
    protected void update(VerifyingParameters params) {
        ContractVerifier verifier = null;
        if(!noArguments(2)){
            try {
                log("Loading verifier: "+args[0] + " from library: "+args[0] );
                verifier = new PluginLoader().makeObject(args[0], args[1]);
            } catch (Exception e) {
                error("Unable to load verifier because of a " + 
                        e.getClass().getSimpleName() + " error.");
            }
        }
        params.setContractVerifier(verifier);
    }

    @Override
    protected String getUsageString() {
        return "With this command, you can plug your own pact contract verifier,"
                + " into the " + new  VerifyContracts().getName() + " command."
                + "this way, your verifier class will be used for verifications. "
                + "this argument should be followed with <path-of-jar-lib> and then "
                + " <full-class-name> of your verifier class. the verifier class "
                + "should have a constructor with no arguments.";
    }

    @Override
    public ArgumentValidationResult validateArguments() {
        return enoughOrNothing(2);
    }
    
    
    
}
