/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.commands.createwiki;

import acidmanic.commandline.utility.ArgumentValidationResult;
import com.acidmanic.pactdoc.plugin.PluginLoader;
import com.acidmanic.pactdoc.services.contractindexing.properties.DefaultPropertyProvider;
import com.acidmanic.pactdoc.services.contractindexing.properties.PropertyProvider;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class PlugWikiProps extends CreateWikiArgBase{

    @Override
    protected String getUsageString() {
        return "This command will plug your PropertyProvider from your jar "
                + "file into the command. A property provider is an implementation "
                + "of the interface PropertyProvider which has one method returning "
                + "an array of Property objects. Propery is an interface that take a "
                + "piece of information out of a Contract object. ";
    }

   
    @Override
    protected void update(CreateWikiParameters params) {
         PropertyProvider provider = new DefaultPropertyProvider();
         
         if(!noArguments(2)){
            try {
                log("Loading property provider: "+args[0] + " from library: "+args[0] );
                provider = new PluginLoader().makeObject(args[0], args[1]);
            } catch (Exception e) {
                error("Unable to load property provider because of a " + 
                        e.getClass().getSimpleName() + " error.");
            }
        }
         params.setPropertyProvider(provider);
         
    }
    
    @Override
    public ArgumentValidationResult validateArguments() {
        return enoughOrNothing(2);
    }
    
    
}
