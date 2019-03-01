/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package playgrounds;

import com.acidmanic.pactdoc.services.ContractContentIndexer;
import com.acidmanic.pactdoc.services.ContractIndexer;
import com.acidmanic.pactdoc.services.wikigenerators.MarkdownWikiGenerator;
import com.acidmanic.pactdoc.services.HashContractContentIndexer;
import java.io.IOException;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class Playground {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here

        
        ContractIndexer indexer = new ContractIndexer();
        
        ContractContentIndexer contentIndexer = new HashContractContentIndexer();
        
        indexer.setContentIndexer(contentIndexer);
        
        indexer.index("data/");
        
    }
}
