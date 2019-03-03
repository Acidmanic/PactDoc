/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package playgrounds;

import com.acidmanic.pactdoc.models.Contract;
import com.acidmanic.pactdoc.services.contentproviders.MarkdownContext;
import com.acidmanic.pactdoc.services.contentproviders.WikiGenerator;
import com.acidmanic.pactdoc.services.contractindexing.ContractIndexer;
import com.acidmanic.pactdoc.services.contractindexing.Function;
import com.acidmanic.pactdoc.services.contractindexing.Service;
import com.acidmanic.pactdoc.services.contractindexing.Version;
import com.acidmanic.pactdoc.utility.PactFiles;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class ExtendedWikiGenerator {
    
    
    
    public static void main(String[] args) {
        
        
        ContractIndexer indexer = new ContractIndexer(
                new Version(), new Service(), new Function()
        );
        
        
        PactFiles.scanForAllContracts(".",indexer);
        
        
        WikiGenerator wikiGenerator 
                = new WikiGenerator(false, indexer, "api", MarkdownContext.class);
        
        wikiGenerator.generate("build");
    }
}
