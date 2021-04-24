/* 
 * The MIT License
 *
 * Copyright 2019 Mani Moayedi (acidmanic.moayedi@gmail.com).
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.acidmanic.pactdoc.utility;

import com.acidmanic.lightweight.logger.SilentLogger;
import com.acidmanic.pactdoc.services.contractindexing.ContractIndexer;
import com.acidmanic.pactdoc.storage.PactFileStorage;
import com.acidmanic.pactmodels.Contract;
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

    public static void scanForAllContracts(String rootDirectory,
            Consumer<Contract> scanner) {
        try {
            File root = new File(rootDirectory);
            Files.walkFileTree(root.toPath(), new SimpleFileVisitor() {
                
                @Override
                public void onFile(Path path) {

                    String file = path.toAbsolutePath().toString();

                    if (file.endsWith(".json")) {

                        Contract contract = new PactFileStorage(path.toFile(), new SilentLogger()).load();

                        if (contract != null) {
                            
                            scanner.accept(contract);
                        }
                    }
                }
            });
        } catch (IOException ex) {
        }
    }

    public static void scanForAllContracts(String rootDirectory,
            List<Contract> list) {
        scanForAllContracts(rootDirectory, list::add);
    }

    public static void scanForAllContracts(String rootDirectory,
            ContractIndexer indexer) {
        scanForAllContracts(rootDirectory, indexer::index);
    }
}
