/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.commands.arguments;

import com.acidmanic.pactdoc.commands.ParametersContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;

/**
 *
 * @author diego
 */
public class MetaData extends PactDocArgumentCommandBase{

    @Override
    protected void execute(ParametersContext context, String[] args) {
        
        if(args!=null && args[0]!=null&& args[0].length()>0){
            
            String json = args[0];
            
            ObjectMapper objectMapper = new ObjectMapper();
            
            HashMap<String,String> metadata = new HashMap<>();
            
            try {
                metadata = objectMapper.readValue(json, metadata.getClass());
                
            } catch (Exception e) {
            }
            if(!metadata.isEmpty()){
                
                context.setWikiMetaData(metadata);
            }
        }
    }

    @Override
    protected String getUsageDescription() {
        return "This argument will take a dictionary of key-values in "
                + "json format. This information will be rendered on index"
                + " page of the wiki. This argument is optional.";
    }

    @Override
    public boolean hasArguments() {
        return true;
    }
    
}
