/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.commands;


import acidmanic.commandline.application.ExecutionEnvironment;
import acidmanic.commandline.commands.CommandBase;
import acidmanic.commandline.utility.ArgumentValidationResult;
import com.acidmanic.pactdoc.businessmodels.WikiGeneratingParamters;
import com.acidmanic.pactdoc.commands.createwiki.CreateWikiTypeRegistery;
import com.acidmanic.pactdoc.commands.createwiki.WikiCommandParameters;
import com.acidmanic.pactdoc.services.WikiGeneratingParamsBuilder;
import com.acidmanic.pactdoc.services.WikiGenerator;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class GenerateWiki extends CommandBase{
    
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
        
            
            
            WikiGeneratingParamters genParams = new WikiGeneratingParamsBuilder()
                    .withCommandParamters(parameters)
                    .build();
                    
            WikiGenerator generator = new WikiGenerator(genParams);

            generator.generate(parameters.getOutputDirectory());
        }
    }


    @Override
    public ArgumentValidationResult validateArguments() {
        
        
        environment.getDataRepository().set("params", parameters);
        
        environment.execute(args);
        
        return new ArgumentValidationResult(environment.getNumberOfExecutedCommands());
    }

    
    
}
