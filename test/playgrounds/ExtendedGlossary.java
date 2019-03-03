/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package playgrounds;

import com.acidmanic.pactdoc.services.wiki.glossary.Glossary;
import com.acidmanic.pactdoc.services.wiki.glossary.GlossaryScanner;
import com.acidmanic.pactdoc.services.contractindexing.ContractIndexer;
import com.acidmanic.pactdoc.services.contractindexing.Function;
import com.acidmanic.pactdoc.services.contractindexing.properties.Service;
import com.acidmanic.pactdoc.services.contractindexing.properties.Version;
import com.acidmanic.pactdoc.services.wiki.glossary.GlossaryGenerator;
import com.acidmanic.pactdoc.utility.PactFiles;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class ExtendedGlossary {
    
    
    
    public static void main(String[] args) {
        
        ContractIndexer indexer = new ContractIndexer(new Version(),new Service(),new Function());
        
        PactFiles.scanForAllContracts(".",indexer);
        
        Glossary glossary = new GlossaryGenerator(indexer).generate("Api", ".md");
        
        println(glossary);
        
        System.out.println("Ok");
    }

    private static void println(Glossary glossary) {
        glossary.scan(new GlossaryScanner() {
            @Override
            public void scan(String link, String[] contentKey) {
                
                System.out.println(getKey(contentKey)+" <-----> " +link);
            }

            private String getKey(String[] contentKey) {
                String sep="";
                String ret ="";
                for(String key:contentKey){
                    ret+=sep+key;
                    sep="_";
                }
                return ret;
            }
        });
    }
}
