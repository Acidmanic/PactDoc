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
package com.acidmanic.pactdoc.commands2.tasks;

import com.acidmanic.delegates.arg1.Action;
import com.acidmanic.lightweight.logger.ArchiveLogger;
import com.acidmanic.lightweight.logger.LogTypes;
import com.acidmanic.lightweight.logger.Logger;
import com.acidmanic.lightweight.logger.ResultTypes;
import com.acidmanic.pact.models.Pact;
import com.acidmanic.pactdoc.commands2.ParametersContext;
import com.acidmanic.pactdoc.contractverification.ContractVerifier;
import com.acidmanic.pactdoc.storage.PactGather;
import java.io.File;
import java.util.HashMap;

/**
 *
 * @author diego
 */
public class Verify extends PactDocCommandTaskBase {

    private final HashMap<String, Action<String>> mappedLogger = new HashMap<>();

    public Verify(ParametersContext context, Logger logger) {
        super(context, logger);
        mappedLogger.put(LogTypes.ERROR, message -> logger.error(message));
        mappedLogger.put(LogTypes.INFO, message -> logger.info(message));
        mappedLogger.put(LogTypes.LOG, message -> logger.log(message));
        mappedLogger.put(LogTypes.WARNING, message -> logger.warning(message));

    }

    @Override
    protected boolean perform(ParametersContext context, Logger logger) {

        File root = context.getPactsRoot();

        Pact pact = new PactGather().loadAllContractsAsPact(root);

        ContractVerifier verifier = context.getContractVerifier();

        ArchiveLogger veryfierLogger = new ArchiveLogger();

        verifier.setLogger(veryfierLogger);

        verifier.verify(pact.getContracts());

        log(veryfierLogger);

        return !(veryfierLogger.getOverallSuccess() == ResultTypes.Failed);
    }

    @Override
    public String getTitleing() {
        return "Verifying available Pact contracts.";
    }

    protected void log(ArchiveLogger logArchive) {
        logArchive.getRecords().forEach(li
                -> mappedLogger.get(li.getLogType()).perform(li.getMessage())
        );
    }

}
