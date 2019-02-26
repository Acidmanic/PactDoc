/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.commands;


import acidmanic.commandline.application.ExecutionEnvironment;
import acidmanic.commandline.commands.CommandBase;
import acidmanic.commandline.utility.ArgumentValidationResult;
import com.acidmanic.pactdoc.commands.createmarkdownwiki.CMDTypeRegistery;
import com.acidmanic.pactdoc.commands.createmarkdownwiki.MarkdownWikiParameters;
import com.acidmanic.pactdoc.services.ContractIndexer;
import com.acidmanic.pactdoc.services.MarkDownWikiGenerator;
import com.acidmanic.pactdoc.utility.SimpleFileVisitor;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class CreateMarkDownWiki extends CommandBase{
    
    private MarkdownWikiParameters parameters = new MarkdownWikiParameters();

    private ExecutionEnvironment environment = new ExecutionEnvironment(new CMDTypeRegistery());
    
    @Override
    protected String getUsageString() {
        return "This command will scan recursively all files inside "
                + "pacts directory to find any pact json available. then"
                + "will create a static md-based wiki in the documents directory"
                + " <sub-wiki-directory> is an optional argument, if provided, all "
                + "documents generated will be placed and linked regarding this base"
                + " directory.";
    }
    
    

    @Override
    public void execute() {
        
        if (!environment.isHelpExecuted()){
        
            ContractIndexer indexer = scanForAllContracts(parameters.getPactsRoot());

            MarkDownWikiGenerator generator = new MarkDownWikiGenerator(indexer, parameters.getDocumentsSubDirectory());

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
                        System.out.println(path.toAbsolutePath().toString());
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
