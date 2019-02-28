/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.commands;

import acidmanic.commandline.application.ExecutionEnvironment;
import acidmanic.commandline.commands.CommandBase;
import acidmanic.commandline.utility.ArgumentValidationResult;
import com.acidmanic.pactdoc.commands.createwiki.CreateWikiParameters;
import com.acidmanic.pactdoc.commands.createwiki.UpdateWikiTypesRegistery;
import com.acidmanic.pactdoc.commands.parametervalidation.UpdateWikiAutoValidator;
import com.acidmanic.pactdoc.commands.parametervalidation.ValidationResult;
import com.acidmanic.pactdoc.services.ContractIndexer;
import com.acidmanic.pactdoc.services.JGit;
import com.acidmanic.pactdoc.services.wikigenerators.MarkdownWikiGenerator;
import static com.acidmanic.pactdoc.utility.PactFiles.*;
import java.util.Date;
/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class UpdateWiki extends CommandBase{

    private final CreateWikiParameters parameters;
    
    private final ExecutionEnvironment parametersEnvironment;

    public UpdateWiki() {
        this.parameters = new CreateWikiParameters();
        
        this.parametersEnvironment = new ExecutionEnvironment(new UpdateWikiTypesRegistery());
            
    }
    
    @Override
    protected String getUsageString() {
        return "This command will fetch a wiki reposiroty, update it's content "
                + "by generating wiki contents from pact files and will update "
                + "the remote wiki with new content.";
    }

    @Override
    public void execute() {
        
        if(!this.parametersEnvironment.isHelpExecuted()){
            
            ValidationResult<CreateWikiParameters> result 
                    = new UpdateWikiAutoValidator().validate(parameters);
            
            JGit git = new JGit();
            
            if (result.isValid()){
                
                cloneGitRepo(parameters);
                generateWiki(parameters);
                git.acceptLocalChanges(parameters.getOutputDirectory(), makeCommit());
                push(parameters);
                
            }
            
            result.getInfos().forEach((String v)-> log(v));
            result.getWarnings().forEach((String v)-> warning(v));
            result.getErrors().forEach((String v)-> error(v));
        }
    }

    @Override
    public ArgumentValidationResult validateArguments() {
      
        this.parametersEnvironment.execute(args);
        
        return anyAvailable();
        
    }

    private void cloneGitRepo(CreateWikiParameters parameters) {
        JGit git = new JGit();
        
        if(parameters.hasValidUserPass()){
            git.clone(parameters.getRepository(), parameters.getOutputDirectory()
            ,parameters.getUsername(),parameters.getPassword());
        }else{
            git.clone(parameters.getRepository(), parameters.getOutputDirectory());
        }
        
    }

    private void generateWiki(CreateWikiParameters parameters) {
        ContractIndexer indexer = scanForAllContracts(parameters.getPactsRoot());

        MarkdownWikiGenerator generator = new MarkdownWikiGenerator(indexer, parameters.getDocumentsSubDirectory());

        generator.setGenerateFilesWithExtension(parameters.isExtensionForMarkDownFiles());

        generator.generate(parameters.getOutputDirectory());
    }

    private String makeCommit() {
        return "Wiki Has been updated via PactDoc application at " 
                + new Date().toString();
    }

    private void push(CreateWikiParameters parameters) {
        JGit git = new JGit();
        
        if(parameters.hasValidUserPass()){
            
            git.push(parameters.getRemote(), parameters.getUsername(),
                    parameters.getPassword(), parameters.getOutputDirectory());
            
        }else{
            git.push(parameters.getRemote(), parameters.getOutputDirectory());
        }
    }
    
    
    
    
}
