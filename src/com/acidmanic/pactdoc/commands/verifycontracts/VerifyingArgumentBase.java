/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.commands.verifycontracts;

import acidmanic.commandline.commandnames.DoubleDashedSnakeCaseNameGenerator;
import acidmanic.commandline.commands.CommandBase;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public abstract class VerifyingArgumentBase extends CommandBase{

    public VerifyingArgumentBase() {
        
        this.setNameGenerator(new DoubleDashedSnakeCaseNameGenerator(this.getClass()));
    
    }

    

    @Override
    public void execute() {
        VerifyingParameters params =  getExecutionEnvironment()
                .getDataRepository().get("params");
        
        update(params);
    }
    
    
    protected abstract void update(VerifyingParameters params);
    
}
