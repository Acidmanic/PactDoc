/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.commands;


import acidmanic.commandline.application.ExecutionEnvironment;
import acidmanic.commandline.commands.CommandBase;
import acidmanic.commandline.utility.ArgumentValidationResult;
import com.acidmanic.pactdoc.commands.createwiki.CreateMarkdownWikiTypeRegistery;
import com.acidmanic.pactdoc.commands.createwiki.CreateWikiParameters;
import com.acidmanic.pactdoc.services.ContractIndexer;
import com.acidmanic.pactdoc.services.wikigenerators.MarkdownWikiGenerator;
import com.acidmanic.pactdoc.utility.SimpleFileVisitor;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class CreateMarkdownWiki extends CommandBase{
    
    private final CreateWikiParameters parameters = new CreateWikiParameters();

    private final ExecutionEnvironment environment = new ExecutionEnvironment(new CreateMarkdownWikiTypeRegistery());
    
    @Override
    protected String getUsageString() {
        return "This command will scan recursively all files inside "
                + "pacts directory to find any pact json available. then"
                + "will create a static md-based wiki in the documents directory";
    }
    
    

    @Override
    public void execute() {
        
        if (!environment.isHelpExecuted()){
        
            ContractIndexer indexer = scanForAllContracts(parameters.getPactsRoot());

            MarkdownWikiGenerator generator = new MarkdownWikiGenerator(indexer, parameters.getDocumentsSubDirectory());

            generator.setGenerateFilesWithExtension(parameters.isExtensionForMarkDownFiles());

            generator.generate(parameters.getOutputDirectory());
        }
    }

    private ContractIndexer scanForAllContracts(String rootDirectory) {
        
        final ContractIndexer indexer = new ContractIndexer();
        
        try {
            File root = new File(rootDirectory);
            
            Files.walkFileTree(root.toPath(), new SimpleFileVisitor() {
                @Override
                public void onFile(Path path) {
                    if(path.toString().endsWith(".json")){
                        indexer.index(path.toAbsolutePath().toString());
                    }
                }
            });
        } catch (IOException ex) {}
        
        return indexer;
    }

    @Override
    public ArgumentValidationResult validateArguments() {
        
        
        environment.getDataRepository().set("params", parameters);
        
        environment.execute(args);
        
        return new ArgumentValidationResult(environment.getNumberOfExecutedCommands());
    }

    
    
}
