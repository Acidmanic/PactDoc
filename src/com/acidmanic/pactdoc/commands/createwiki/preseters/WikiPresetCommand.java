/* 
 * The MIT License
 *
 * Copyright 2019 Mani Moayedi (acidmanic.moayedi@gmail.com).
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.acidmanic.pactdoc.commands.createwiki.preseters;

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
import com.acidmanic.pactdoc.businessmodels.WikiCommandParameters;

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
                getCSListOfFillingArguments() + ", regarding the " + gitName()
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
    
    
    protected String getCSListOfFillingArguments(){
        return (new AddExtensions().getName())
                +", "+ (new Format().getName())
                +", "+ (new Remote().getName()) 
                +", "+ (new RootRelativeLinks().getName());
    }
}
