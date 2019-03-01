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
import com.acidmanic.pactdoc.logging.Log;
import com.acidmanic.pactdoc.logging.LogRecord;
import com.acidmanic.pactdoc.models.Contract;
import com.acidmanic.pactdoc.utility.PactFiles;
import java.util.ArrayList;
import java.util.List;

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

    private void log(Log log){
        for(LogRecord record:log.getRecords()){
            if (null!=record.getLogType())switch (record.getLogType()) {
                case Error:
                    error(record.getMessage());
                    break;
                case Warning:
                    warning(record.getMessage());
                    break;
                case Info:
                    info(record.getMessage());
                    break;
                default:
                    log(record.getMessage());
                    break;
            }
        }
    }
    
    @Override
    public ArgumentValidationResult validateArguments() {
        this.paramsEnvironment.execute(args);
        
        return anyAvailable();
    }

    
    
    
}
