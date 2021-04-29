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
package com.acidmanic.pactdoc.contractverification;

import com.acidmanic.lightweight.logger.Logger;
import com.acidmanic.lightweight.logger.SilentLogger;
import com.acidmanic.pactmodels.Contract;
import com.acidmanic.pactmodels.Interaction;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class DefaultContractVerifier implements ContractVerifier {

    private Logger logger = new SilentLogger();

    @Override
    public void verify(List<Contract> contracts) {

        for (Contract contract : contracts) {

            if (contract.getInteractions().isEmpty()) {
                logger.error("Contract Must atleat have one Intraction.");
            }

            for (Interaction interaction : contract.getInteractions()) {
                if (isEmpty(interaction.getDescription())) {
                    logger.warning("Intraction with no description.");
                }
            }

            logger.log("Contract " + contract.getProvider().getName()
                    + " with version "
                    + contract.getMetadata().getPactSpecification().getVersion()
                    + " verified.");
        }
    }

    private boolean isEmpty(String text) {
        return (text == null) || text.length() == 0;
    }

    @Override
    public List<ConventionTitle> conventionTitles() {

        List<ConventionTitle> conventions = new ArrayList<>();

        conventions.add(new ConventionTitle(ConventionType.Must, "Contract Must at least have one Intraction."));

        conventions.add(new ConventionTitle(ConventionType.Warnable, "Intraction with no description."));

        return conventions;
    }

    @Override
    public void setLogger(Logger logger) {
        this.logger = logger;
    }

}
