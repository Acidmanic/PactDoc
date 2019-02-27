/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.commands.createwiki;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class UpdateWikiTypesRegistery extends CreateMarkdownWikiTypeRegistery{

    public UpdateWikiTypesRegistery() {
        
        registerClass(User.class);
        
        registerClass(Pass.class);
        
        registerClass(HttpRepo.class);
    
    }
}
