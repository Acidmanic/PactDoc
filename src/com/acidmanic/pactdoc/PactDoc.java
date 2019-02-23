/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc;

import com.acidmanic.pactdoc.models.Contract;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class PactDoc {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here

        
        String path = "data/dotnetdesktop-posts_api_-_posts.json";
        
        ObjectMapper objectMapper = new ObjectMapper();
    
        Contract contract = Contract.load(path);
    
        String json = objectMapper.writeValueAsString(contract);
        
        System.out.println(json);
         
         
    }
    
}
