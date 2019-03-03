/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package playgrounds;

import com.acidmanic.pactdoc.models.Contract;
import com.acidmanic.pactdoc.services.wiki.contentproviders.ContentProvider;
import com.acidmanic.pactdoc.services.wiki.contentproviders.DirectoriyContentProvider;
import com.acidmanic.pactdoc.services.wiki.interpreter.MarkdownContext;
import com.acidmanic.pactdoc.services.WikiGenerator;
import com.acidmanic.pactdoc.services.contractindexing.ContractIndexer;
import com.acidmanic.pactdoc.services.contractindexing.Function;
import com.acidmanic.pactdoc.services.contractindexing.properties.Service;
import com.acidmanic.pactdoc.services.contractindexing.properties.Version;
import com.acidmanic.pactdoc.services.wiki.wikiformat.WikiFormats;
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
                = new WikiGenerator(false, indexer, "api",
                        new DirectoriyContentProvider(indexer,WikiFormats.MARKDOWN));
        
        wikiGenerator.generate("build");
    }
}
