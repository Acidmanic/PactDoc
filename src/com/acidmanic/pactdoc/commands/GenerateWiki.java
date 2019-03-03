/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.commands;


import acidmanic.commandline.application.ExecutionEnvironment;
import acidmanic.commandline.commands.CommandBase;
import acidmanic.commandline.utility.ArgumentValidationResult;
import com.acidmanic.pactdoc.commands.createwiki.CreateWikiTypeRegistery;
import com.acidmanic.pactdoc.commands.createwiki.CreateWikiParameters;
import com.acidmanic.pactdoc.services.contractindexing.ContractIndexer;
import com.acidmanic.pactdoc.services.contentproviders.WikiGenerator;
import static com.acidmanic.pactdoc.utility.PactFiles.*;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class GenerateWiki extends CommandBase{
    
    private final CreateWikiParameters parameters = new CreateWikiParameters();

    private final ExecutionEnvironment environment = new ExecutionEnvironment(new CreateWikiTypeRegistery());
    
    @Override
    protected String getUsageString() {
        return "This command will scan recursively all files inside "
                + "pacts directory to find any pact json available. then"
                + "will create a static md-based wiki in the documents directory";
    }
    
    

    @Override
    public void execute() {
        
        if (!environment.isHelpExecuted()){
        
            ContractIndexer indexer = new ContractIndexer(parameters
                    .getPropertyProvider().makeProperties());
            
            scanForAllContracts(parameters.getPactsRoot(),indexer);

            WikiGenerator generator = new WikiGenerator(parameters.isExtensionForMarkDownFiles(),
                    indexer, parameters.getDocumentsSubDirectory(), 
                    parameters.getContextClass());

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
