/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.commands;

import acidmanic.commandline.commands.CommandBase;
import com.acidmanic.pactdoc.services.ContractIndexer;
import com.acidmanic.pactdoc.services.GitLabWikiGenerator;
import com.acidmanic.pactdoc.utility.SimpleFileVisitor;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class CreateGitlabWiki extends CommandBase{

    @Override
    protected String getUsageString() {
        return "This command will scan recursively all files inside "
                + "<pacts-root-directory> to find any pact json available. then"
                + "will create a static md-based wiki in the <docs-directory>"
                + " <sub-wiki-directory> is an optional argument, if provided, all "
                + "documents generated will be placed and linked regarding this base"
                + " directory.";
    }

    @Override
    protected String declareArguments() {
        return "<pacts-root-directory> <docs-directory> [<sub-wiki-directory>]";
    }
    
    

    @Override
    public void execute() {
        if(noArguments(2)){
            argumentError();
            return;
        }
        
        String linkBase = args.length>2?args[2]:"";
        
        ContractIndexer indexer = scanForAllContracts(args[0]);
        
        GitLabWikiGenerator generator = new GitLabWikiGenerator(indexer, linkBase);
        
        generator.generate(args[1]);
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
    
}
