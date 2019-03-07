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
import com.acidmanic.pactdoc.commands.createwiki.WikiCommandParameters;
import com.acidmanic.pactdoc.commands.typeregisteries.UpdateWikiTypesRegistery;
import com.acidmanic.pactdoc.commands.parametervalidation.UpdateWikiAutoValidator;
import com.acidmanic.pactdoc.commands.parametervalidation.ValidationResult;
import com.acidmanic.pactdoc.services.JGit;
import com.acidmanic.pactdoc.services.WikiGeneratingParamsBuilder;
import com.acidmanic.pactdoc.services.contractindexing.ContractIndexer;
import com.acidmanic.pactdoc.services.WikiGenerator;
import static com.acidmanic.pactdoc.utility.PactFiles.*;
import java.util.Date;
import com.acidmanic.pactdoc.utility.Func;
/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class UpdateWiki extends CommandBase{

    private final WikiCommandParameters parameters;
    
    private final ExecutionEnvironment parametersEnvironment;

    public UpdateWiki() {
        this.parameters = new WikiCommandParameters();
        
        this.parametersEnvironment 
                = new ExecutionEnvironment(new UpdateWikiTypesRegistery(),this);
        
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
            
            ValidationResult<WikiCommandParameters> result 
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

    private boolean cloneGitRepo(WikiCommandParameters parameters) {
        JGit git = new JGit();
        
        if(parameters.hasValidUserPass()){
            return git.clone(parameters.getRepository(), parameters.getUsername()
                    ,parameters.getPassword(),parameters.getOutputDirectory());
        }else{
            return git.clone(parameters.getRepository(), parameters.getOutputDirectory());
        }
        
    }

    private boolean generateWiki(WikiCommandParameters parameters) {
                        
        WikiGeneratingParamters genParams = new WikiGeneratingParamsBuilder()
                    .withCommandParamters(parameters)
                    .build();
                    
        WikiGenerator generator = new WikiGenerator(genParams);
            
        generator.generate(parameters.getResolvedWikiPath());
        
        return true;
    }

    private String makeCommit() {
        return "Wiki Has been updated via PactDoc application at " 
                + new Date().toString();
    }

    private boolean push(WikiCommandParameters parameters) {
        JGit git = new JGit();
        
        if(parameters.hasValidUserPass()){
            
            return git.push(parameters.getRemote(), parameters.getUsername(),
                    parameters.getPassword(), parameters.getOutputDirectory());
            
        }else{
            return git.push(parameters.getRemote(), parameters.getOutputDirectory());
        }
    }
    
    
    
}
