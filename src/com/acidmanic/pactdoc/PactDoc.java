/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc;

import com.acidmanic.pactdoc.models.Contract;
import com.acidmanic.pactdoc.models.ConventionedContract;
import com.acidmanic.pactdoc.models.Request;
import com.acidmanic.pactdoc.models.Response;
import com.acidmanic.pactdoc.services.ContractContentIndexer;
import com.acidmanic.pactdoc.services.ContractIndexer;
import com.acidmanic.pactdoc.services.ContractMarkDown;
import com.acidmanic.pactdoc.services.HashContractContentIndexer;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

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
        
        ContractContentIndexer contentIndexer = new HashContractContentIndexer();
        
        indexer.setContentIndexer(contentIndexer);
        
        indexer.index("data/");
        
        ContractMarkDown markDown = new ContractMarkDown();
        
        
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
                Response res = contract.getInteractions().get(0).getResponse();
                
                System.out.println("\t\tStatus:"+res.getStatus());
                System.out.println("\t\tHeaders:"+res.getHeaders());
                System.out.println("\t\tBody:"+res.getBody());
                                
                String md = markDown.getMarkDown(contract);
                
                write(md,"build/"+contract.getProvider().getName()+".md");
            }
        }
        
        
        List<ConventionedContract> result = indexer.getContentIndexer().searchForWord("DB");
        
        System.out.println("*******             Search result               **********");
        
        
        
        for(ConventionedContract contract:result){
            
            System.out.println("Contract: " + contract.getServiceName()
             + " - " + contract.getApiName());
            System.out.println("\t"+ contract.getInteractions().get(0).getRequest().getMethod()
                +   "\t"+contract.getInteractions().get(0).getRequest().getPath());
            System.out.println("\tResponse Code: "+ contract.getInteractions().get(0).getResponse().getStatus());
            System.out.println("");
            
            
        }
        
        
         
         
    }

    private static void write(String md, String path) {
        try {
            
            File f = new File(path);
            
            if(f.exists()){
                f.delete();
            }
            
            Files.write(Paths.get(path),md.getBytes() , StandardOpenOption.CREATE_NEW);
            
        } catch (Exception e) {
        }
    }
    
}
