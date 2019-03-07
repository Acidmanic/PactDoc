/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.commands.typeregisteries;

import com.acidmanic.pactdoc.commands.createwiki.Pass;
import com.acidmanic.pactdoc.commands.createwiki.PassFile;
import com.acidmanic.pactdoc.commands.createwiki.Remote;
import com.acidmanic.pactdoc.commands.createwiki.Repository;
import com.acidmanic.pactdoc.commands.createwiki.User;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class UpdateWikiTypesRegistery extends CreateWikiTypeRegistery{

    public UpdateWikiTypesRegistery() {
        
        registerClass(User.class);
        
        registerClass(Pass.class);
        
        registerClass(Repository.class);
        
        registerClass(Remote.class);
        
        registerClass(PassFile.class);
    
    }
}
