/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.commands.createmarkdownwiki;

import acidmanic.commandline.commands.TypeRegistery;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class CMDTypeRegistery extends TypeRegistery{

        public CMDTypeRegistery() {
            registerClass(AddExtensions.class);
            registerClass(Output.class);
            registerClass(APIsSubDir.class);
            registerClass(PactsRoot.class);
        }
        
        
    }