/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.utility;

import com.acidmanic.pactdoc.models.Contract;
import com.acidmanic.pactdoc.services.extendableindexing.ContractIndexer;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Consumer;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class PactFiles {

    public static void scanForAllContracts(String rootDirectory
            ,Consumer<Contract> scanner) {
        try {
            File root = new File(rootDirectory);
            Files.walkFileTree(root.toPath(), new SimpleFileVisitor() {
                @Override
                public void onFile(Path path) {
                    String file = path.toAbsolutePath().toString();
                    if (file.endsWith(".json")) {
                        try {
                            Contract contract = Contract.load(file);
                            scanner.accept(contract);
                        } catch (Exception e) {
                        }
                    }
                }
            });
        } catch (IOException ex) {
        }
    }
    
    
    public static void scanForAllContracts(String rootDirectory
            ,List<Contract> list){
        scanForAllContracts(rootDirectory, list::add);
    }
    
    public static void scanForAllContracts(String rootDirectory
            ,ContractIndexer indexer){
        scanForAllContracts(rootDirectory, indexer::index);
    }
}
