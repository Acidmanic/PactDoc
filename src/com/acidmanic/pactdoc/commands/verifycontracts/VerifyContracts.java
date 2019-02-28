/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.commands.verifycontracts;

import acidmanic.commandline.application.ExecutionEnvironment;
import acidmanic.commandline.commands.CommandBase;
import acidmanic.commandline.utility.ArgumentValidationResult;
import com.acidmanic.pactdoc.commands.parametervalidation.ValidationResult;
import com.acidmanic.pactdoc.commands.parametervalidation.VerifyContractParameterValidator;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class VerifyContracts extends CommandBase {

    private final VerifyingParameters parameters;
    
    private final ExecutionEnvironment paramsEnvironment;

    public VerifyContracts() {
        this.parameters = new VerifyingParameters();
        
        this.paramsEnvironment = new ExecutionEnvironment(new VerificationTypeRegistery());
        
        this.paramsEnvironment.getDataRepository().set("params", parameters);
    
    }
    
    
    
    @Override
    protected String getUsageString() {
        return "This command will search for all pact contracts available,"
                + " then will check all of them to confirm with aproprate "
                + "conventions. you can write your own contract verifier class"
                + " and plug the jar file into this command and run it on your "
                + "pipe line.";
    }

    @Override
    public void execute() {
        
        if(!this.paramsEnvironment.isHelpExecuted()){
            
            ValidationResult<VerifyingParameters> result = 
                    new VerifyContractParameterValidator().validate(parameters);
            
       
            result.getInfos().forEach((String v)-> log(v));
            result.getWarnings().forEach((String v)-> warning(v));
            result.getErrors().forEach((String v)-> error(v));
            
            if( result.isValid()){
                
            }
        }
    }

    @Override
    public ArgumentValidationResult validateArguments() {
        this.paramsEnvironment.execute(args);
        
        return anyAvailable();
    }

    
    
    
}
