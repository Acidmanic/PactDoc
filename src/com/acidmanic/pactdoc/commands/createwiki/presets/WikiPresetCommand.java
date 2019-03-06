/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.commands.createwiki.presets;

import acidmanic.commandline.application.CommandStack;
import acidmanic.commandline.commands.Command;
import acidmanic.commandline.utility.ArgumentValidationResult;
import com.acidmanic.pactdoc.commands.UpdateWiki;
import com.acidmanic.pactdoc.commands.createwiki.AddExtensions;
import com.acidmanic.pactdoc.commands.createwiki.CreateWikiArgBase;
import com.acidmanic.pactdoc.commands.createwiki.Format;
import com.acidmanic.pactdoc.commands.createwiki.Remote;
import com.acidmanic.pactdoc.commands.createwiki.Repository;
import com.acidmanic.pactdoc.commands.createwiki.RootRelativeLinks;
import com.acidmanic.pactdoc.commands.createwiki.User;
import com.acidmanic.pactdoc.commands.createwiki.WikiCommandParameters;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public abstract class WikiPresetCommand extends CreateWikiArgBase{
    
    @Override
    protected String declareArguments() {
        return "[<repository-name> <user-name>]";
    }
    
    
    @Override
    protected String getUsageString() {
        return "This will set arguments default values for the arguments: " +
                (new AddExtensions().getName())
                +", "+ (new Format().getName())
                +", "+ (new Remote().getName()) 
                +", "+ (new RootRelativeLinks().getName())+", regarding the " + gitName()
                + " wikis characteristics.\n"
                + "Using " + this.getName() + " for " +( new UpdateWiki().getName()) 
                + ", it expects to get repository-name and user-name parameters. "
                + "then it will set " + (new Repository().getName())
                + " and " + (new User().getName())
                + "\nall parameters set by this command, can be overriden with "
                + "other commands coming after it.";
                
    }
    
    protected boolean isCalledForRepositories(){
        Command parent = CommandStack.lastMasterCommand();
        
        if(parent instanceof UpdateWiki){
            return true;
        }
        return false;
    }

    @Override
    public ArgumentValidationResult validateArguments() {
        if(isCalledForRepositories()){
            return enoughOrNothing(2);
        }else{
            return new ArgumentValidationResult(0);
        }
    }

    @Override
    protected void update(WikiCommandParameters params) {
        if(isCalledForRepositories() && !noArguments(2)){
            params.setUsername(args[1]);
            params.setRepository(getRepositoryFor(args[0],args[1]));
        }
    }

    
    
    
    
    
    protected abstract String gitName();

    protected abstract String getRepositoryFor(String repoName, String userName);
    
    
    
}
