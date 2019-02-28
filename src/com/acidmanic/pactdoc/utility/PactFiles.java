/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.utility;

import com.acidmanic.pactdoc.services.ContractIndexer;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class PactFiles {

    public static ContractIndexer scanForAllContracts(String rootDirectory) {
        final ContractIndexer indexer = new ContractIndexer();
        try {
            File root = new File(rootDirectory);
            Files.walkFileTree(root.toPath(), new SimpleFileVisitor() {
                @Override
                public void onFile(Path path) {
                    if (path.toString().endsWith(".json")) {
                        indexer.index(path.toAbsolutePath().toString());
                    }
                }
            });
        } catch (IOException ex) {
        }
        return indexer;
    }
    
}
