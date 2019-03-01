/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package playgrounds;

import com.acidmanic.pactdoc.models.Contract;
import com.acidmanic.pactdoc.services.Glossary;
import com.acidmanic.pactdoc.services.GlossaryScanner;
import com.acidmanic.pactdoc.services.extendableindexing.ContractIndexer;
import com.acidmanic.pactdoc.services.extendableindexing.Function;
import com.acidmanic.pactdoc.services.extendableindexing.Service;
import com.acidmanic.pactdoc.services.extendableindexing.Version;
import com.acidmanic.pactdoc.services.wikigenerators.GlossaryGenerator;
import com.acidmanic.pactdoc.utility.PactFiles;
import java.util.List;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class ExtendedGlossary {
    
    
    
    public static void main(String[] args) {
        
        
        List<Contract> contracts = PactFiles.scanForAllContracts(".").getAllContracts();
        
        ContractIndexer indexer = new ContractIndexer(new Version(),new Service(),new Function());
        
        contracts.forEach((Contract c)->indexer.index(c));
        
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
