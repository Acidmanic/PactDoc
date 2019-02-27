/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.commands.createwiki;

import acidmanic.commandline.commandnames.DoubleDashedSnakeCaseNameGenerator;
import acidmanic.commandline.commands.CommandBase;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public  abstract class CreateWikiArgBase extends CommandBase{

    public CreateWikiArgBase() {
        setNameGenerator(new DoubleDashedSnakeCaseNameGenerator(this.getClass()));
    }

    
    @Override
    protected String getUsageString() {
        return "This will set the '" + this.getClass().getSimpleName()+ "' Parameter for Markdown"
                + " wiki generation.";
    }

    @Override
    public void execute() {
        CreateWikiParameters params = this.getExecutionEnvironment()
                .getDataRepository().get("params");
        
        update(params);
        
    }
    
    protected abstract void update(CreateWikiParameters params);
    
    
}
