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
import com.acidmanic.pactdoc.services.JGit;
import com.acidmanic.pactdoc.services.extendableindexing.ContractIndexer;
import com.acidmanic.pactdoc.services.wikigenerators.MarkdownWikiGenerator;
import static com.acidmanic.pactdoc.utility.PactFiles.*;
import java.util.Date;
import com.acidmanic.pactdoc.utility.Func;
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
        
        this.parametersEnvironment.getDataRepository()
                .set("params", this.parameters);
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
            
            result.getInfos().forEach((String v)-> log(v));
            result.getWarnings().forEach((String v)-> warning(v));
            result.getErrors().forEach((String v)-> error(v));
            
            if (result.isValid()){
                
                boolean res = logPerformTask("Cloning Wiki Repo",()->cloneGitRepo(parameters)) && 
                
                logPerformTask("Updating Wiki Files",()->generateWiki(parameters)) && 
                
                logPerformTask("Commiting Changes",()-> 
                        git.acceptLocalChanges(parameters.getOutputDirectory(), makeCommit())) &&
                
                logPerformTask("Updating Remote Wiki",()->push(parameters));
               
            }
            
            
        }
    }
    
    private boolean logPerformTask(String titleing, Func<Boolean> task){
        if(task.perform()){
            log(titleing+" : OK");
            return true;
        }else{
            error("There was a problem " + titleing);
            return false;
        }
    }

    @Override
    public ArgumentValidationResult validateArguments() {
      
        this.parametersEnvironment.execute(args);
        
        return anyAvailable();
        
    }

    private boolean cloneGitRepo(CreateWikiParameters parameters) {
        JGit git = new JGit();
        
        if(parameters.hasValidUserPass()){
            return git.clone(parameters.getRepository(), parameters.getUsername()
                    ,parameters.getPassword(),parameters.getOutputDirectory());
        }else{
            return git.clone(parameters.getRepository(), parameters.getOutputDirectory());
        }
        
    }

    private boolean generateWiki(CreateWikiParameters parameters) {
        ContractIndexer indexer = new ContractIndexer(
                parameters.getPropertyProvider().makeProperties()
        );
        
        scanForAllContracts(parameters.getPactsRoot(),indexer);

        MarkdownWikiGenerator generator = new MarkdownWikiGenerator(indexer
                , parameters.getDocumentsSubDirectory()
                ,parameters.isExtensionForMarkDownFiles());

        generator.generate(parameters.getOutputDirectory());
        return true;
    }

    private String makeCommit() {
        return "Wiki Has been updated via PactDoc application at " 
                + new Date().toString();
    }

    private boolean push(CreateWikiParameters parameters) {
        JGit git = new JGit();
        
        if(parameters.hasValidUserPass()){
            
            return git.push(parameters.getRemote(), parameters.getUsername(),
                    parameters.getPassword(), parameters.getOutputDirectory());
            
        }else{
            return git.push(parameters.getRemote(), parameters.getOutputDirectory());
        }
    }
    
    
    
}
