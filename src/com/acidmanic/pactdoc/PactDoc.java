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
import com.acidmanic.pactdoc.services.GitLabWikiGenerator;
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
        
        
        GitLabWikiGenerator wikiGenerator = new GitLabWikiGenerator(indexer, "Api");
        
        wikiGenerator.generate("build");
    }
}
