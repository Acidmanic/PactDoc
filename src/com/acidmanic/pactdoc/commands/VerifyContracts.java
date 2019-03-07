/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.commands;

import com.acidmanic.pactdoc.commands.typeregisteries.VerificationTypeRegistery;
import acidmanic.commandline.application.ExecutionEnvironment;
import acidmanic.commandline.utility.ArgumentValidationResult;
import com.acidmanic.pactdoc.commands.parametervalidation.ValidationResult;
import com.acidmanic.pactdoc.commands.parametervalidation.VerifyContractParameterValidator;
import com.acidmanic.pactdoc.businessmodels.VerifyCommandParameters;
import com.acidmanic.pactdoc.logging.Log;
import com.acidmanic.pactdoc.models.Contract;
import com.acidmanic.pactdoc.utility.PactFiles;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class VerifyContracts extends PactDocCommandBase {

    private final VerifyCommandParameters parameters;
    
    private final ExecutionEnvironment paramsEnvironment;

    public VerifyContracts() {
        this.parameters = new VerifyCommandParameters();
        
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
            
            ValidationResult<VerifyCommandParameters> result = 
                    new VerifyContractParameterValidator().validate(parameters);
            
       
            log(result);
            
            if( result.isValid()){
                
                List<Contract> contracts = new ArrayList<>();
                
                PactFiles.scanForAllContracts(parameters.getPactsRoot(),contracts);
                
                Log verificationResults = 
                        parameters.getContractVerifier()
                        .verify(contracts);
                
                log(verificationResults);
                
                int exitCode = verificationResults.isValid()?0:128;
                
                getExecutionEnvironment().setExitCode(exitCode);
            }
        }
    }
    
    @Override
    public ArgumentValidationResult validateArguments() {
        this.paramsEnvironment.execute(args);
        
        return anyAvailable();
    }

    
    
    
}
