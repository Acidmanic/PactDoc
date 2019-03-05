/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package playgrounds;

import com.acidmanic.pactdoc.services.contractindexing.ContractIndexer;
import com.acidmanic.pactdoc.services.contractindexing.properties.Function;
import com.acidmanic.pactdoc.services.contractindexing.properties.Service;
import com.acidmanic.pactdoc.services.contractindexing.properties.Version;
import com.acidmanic.pactdoc.utility.PactFiles;
import java.util.List;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class ExtendedIndexing {
    
    
    
    public static void main(String[] args) {
        
        
        
        
        
        ContractIndexer indexer = new ContractIndexer(new Version(),new Service(),new Function());
        
        PactFiles.scanForAllContracts(".",indexer);
        
        List<String> res;
        
        System.out.println("All services by version 1.0.0:");
        res = indexer.getAll( "1.0.0");
        res.forEach((String s)->System.out.println(s));
        
        
        System.out.println("All services by version v2:");
        res = indexer.getAll( "v2");
        res.forEach((String s)->System.out.println(s));
        
        
        System.out.println("All Functions by version 1.0.0 > Service PostsAPI:");
        res = indexer.getAll("1.0.0","PostsAPI");
        res.forEach((String s)->System.out.println(s));
        
        System.out.println("All Functions by version v2 > Service PostsAPI:");
        res = indexer.getAll("v2","PostsAPI");
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
