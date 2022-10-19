/*
 * The MIT License
 *
 * Copyright 2021 diego.
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
package com.acidmanic.pactdoc.storage;

import com.acidmanic.lightweight.logger.ConsoleLogger;
import com.acidmanic.pact.models.Pact;
import com.acidmanic.pactmodels.Contract;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author diego
 */
public class PactGather {

    public Pact loadAllContractsAsPact(File root) {

        return new Pact(
                loadAllContracts(root)
        );
    }

    public List<Contract> loadAllContracts(File root) {

        List<Contract> result = new ArrayList<>();

        loadAllContracts(root, result);

        return result;
    }

    private void loadAllContracts(File dir, List<Contract> result) {

        
        if(!dir.exists()){
            dir.mkdirs();
        }
        
        File[] files = dir.listFiles();

        for (File file : files) {

            if (file.isDirectory()) {
                loadAllContracts(file, result);
            } else if (file.getName().toLowerCase().endsWith(".json")) {

                Contract contract = new PactFileStorage(file, new ConsoleLogger()).load();

                if (contract != null) {
                    result.add(contract);
                }
            }
        }
    }
}
