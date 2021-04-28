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

import com.acidmanic.lightweight.logger.Logger;
import com.acidmanic.pact.models.Pact;
import com.acidmanic.pactdoc.commands2.ParametersContext;
import com.acidmanic.pactdoc.storage.PactGather;
import com.acidmanic.pactdoc.wiki.WikiEngine;
import com.acidmanic.pactdoc.wiki.WikiEngineOptions;
import com.acidmanic.pactdoc.wiki.format.WikiFormat;

/**
 *
 * @author diego
 */
public class GenerateWiki extends PactDocCommandTaskBase {

    public GenerateWiki(ParametersContext context, Logger logger) {
        super(context, logger);
    }

    @Override
    public String getTitleing() {
        return "Generating Wiki...";
    }

    @Override
    protected boolean perform(ParametersContext context, Logger logger) {

        WikiEngineOptions options = new WikiEngineOptions();

        WikiFormat format = context.getWebWikiFormatBuilder().build();

        options.setFormat(format);

        options.setPluggedDocumentDefinition(null);

        options.setPluggedContractVerifier(context.getContractVerifier());

        WikiEngine engine = new WikiEngine(options);

        Pact pact = new PactGather().loadAllContractsAsPact(context.getPactsRoot());

        engine.generate(pact);

        return true;
    }

}
