/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc;

import com.acidmanic.pactdoc.models.Contract;
import com.acidmanic.pactdoc.models.Request;
import com.acidmanic.pactdoc.services.ContractIndexer;
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

        
        ContractIndexer indexer = new ContractIndexer();
        
        indexer.index("data/");
        
        System.out.println("Services:");
        
        for(String service : indexer.getServices()){
            
            System.out.println("Service:");
            
            for(Contract contract:indexer.getContracts(service)){
                System.out.println("\tAPI:"+contract.getConsumer().getName());
                System.out.println("\t"+contract.getInteractions().get(0).getDescription());
                System.out.println("\tRequest:" );
                Request req = contract.getInteractions().get(0).getRequest();
                System.out.println("\t\tMethod:"+req.getMethod());
                System.out.println("\t\tPath:"+req.getPath());
                System.out.println("\t\tHeaders:"+req.getHeaders());
                System.out.println("\tResponse:" );
                System.out.println(contract.getInteractions().get(0).getResponse());
                
                
            }
        }
        
        
        
         
         
    }
    
}
