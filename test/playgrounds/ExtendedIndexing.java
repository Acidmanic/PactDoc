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
import com.acidmanic.pactdoc.utility.PactFiles;
import java.util.List;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class ExtendedIndexing {
    
    
    
    public static void main(String[] args) {
        
        
        
        List<Contract> contracts = PactFiles.scanForAllContracts(".").getAllContracts();
        
        ContractIndexer indexer = new ContractIndexer(new Version(),new Service(),new Function());
        
        contracts.forEach((Contract c)->indexer.index(c));
        
        List<String> res;
        
        System.out.println("All services by version 1.0.0:");
        res = indexer.getAllXsByY(Service.class, "1.0.0");
        res.forEach((String s)->System.out.println(s));
        
        
        
        
        System.out.println("All Functions by Service PostsAPI:");
        res = indexer.getAllXsByY(Function.class, "PostsAPI");
        res.forEach((String s)->System.out.println(s));
        
        
        
        System.out.println("All Versions:");
        res = indexer.getAll(Version.class);
        res.forEach((String s)->System.out.println(s));
        
        
        System.out.println("All Services:");
        res = indexer.getAll(Service.class);
        res.forEach((String s)->System.out.println(s));
        
        
        System.out.println("All Functions:");
        res = indexer.getAll(Function.class);
        res.forEach((String s)->System.out.println(s));
        
        
        System.out.println("Ok");
        
        
    }
}
