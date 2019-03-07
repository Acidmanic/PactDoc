/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.commands;


import acidmanic.commandline.application.ExecutionEnvironment;
import acidmanic.commandline.utility.ArgumentValidationResult;
import com.acidmanic.pactdoc.businessmodels.WikiGeneratorParamters;
import com.acidmanic.pactdoc.commands.typeregisteries.CreateWikiTypeRegistery;
import com.acidmanic.pactdoc.businessmodels.WikiCommandParameters;
import com.acidmanic.pactdoc.commands.parametervalidation.GenerateWikiParameterValidator;
import com.acidmanic.pactdoc.commands.parametervalidation.ValidationResult;
import com.acidmanic.pactdoc.services.WikiGeneratingParamsBuilder;
import com.acidmanic.pactdoc.services.WikiGenerator;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class GenerateWiki extends PactDocCommandBase{
    
    private final WikiCommandParameters parameters = new WikiCommandParameters();

    private final ExecutionEnvironment environment;

    public GenerateWiki() {
        this.environment 
            = new ExecutionEnvironment(new CreateWikiTypeRegistery(),this);
    }
    
    
    @Override
    protected String getUsageString() {
        return "This command will scan recursively all files inside "
                + "pacts directory to find any pact json available. then"
                + "will create a static md-based wiki in the documents directory";
    }
    
    

    @Override
    public void execute() {
        
        if (!environment.isHelpExecuted()){
            
            ValidationResult<WikiCommandParameters> result
                    = new GenerateWikiParameterValidator().validate(parameters);
            
            log(result);
            
            if(result.isValid()){
            
                WikiGeneratorParamters genParams = new WikiGeneratingParamsBuilder()
                        .withCommandParamters(result.getValidatedValue())
                        .build();

                WikiGenerator generator = new WikiGenerator(genParams);

                generator.generate(parameters.getResolvedWikiPath());
            }
        }
    }


    @Override
    public ArgumentValidationResult validateArguments() {
        
        environment.getDataRepository().set("params", parameters);
        
        environment.execute(args);
        
        return new ArgumentValidationResult(environment.getNumberOfExecutedCommands());
    }

    
    
}
