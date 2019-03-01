/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package playgrounds;

import com.acidmanic.pactdoc.models.Contract;
import com.acidmanic.pactdoc.services.extendableindexing.ContractIndexer;
import com.acidmanic.pactdoc.services.extendableindexing.Function;
import com.acidmanic.pactdoc.services.extendableindexing.Service;
import com.acidmanic.pactdoc.services.extendableindexing.Version;
import com.acidmanic.pactdoc.services.wikigenerators.MarkdownWikiGenerator;
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
        
        
        MarkdownWikiGenerator wikiGenerator 
                = new MarkdownWikiGenerator(indexer, "ApiEx");
        
        wikiGenerator.generate("build");
    }
}
