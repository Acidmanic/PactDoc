/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.commands.typeregisteries;

import acidmanic.commandline.commands.TypeRegistery;
import com.acidmanic.pactdoc.commands.verifycontracts.PactsRoot;
import com.acidmanic.pactdoc.commands.verifycontracts.PlugVerifier;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class VerificationTypeRegistery extends TypeRegistery{

    public VerificationTypeRegistery() {
        
        registerClass(PactsRoot.class);
        
        registerClass(PlugVerifier.class);
    
    }
    
    
    
}
