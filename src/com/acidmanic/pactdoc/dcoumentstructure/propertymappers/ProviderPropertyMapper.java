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
package com.acidmanic.pactdoc.dcoumentstructure.propertymappers;

import com.acidmanic.document.structure.propertymapped.PropertyMapper;
import com.acidmanic.pact.models.Pact;
import com.acidmanic.pactmodels.Contract;
import com.acidmanic.pactmodels.Provider;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author diego
 */
public class ProviderPropertyMapper implements PropertyMapper {

    @Override
    public List<String> keySegmentValues(Object o) {

        List<String> segmentValues = new ArrayList<>();

        if (o instanceof Pact) {

            Pact pact = (Pact) o;

            for (Contract contract : pact.getContracts()) {

                Provider provider = contract.getProvider();

                if (provider != null && provider.getName() != null) {

                    String providerName = provider.getName();

                    if (!segmentValues.contains(providerName)) {

                        segmentValues.add(providerName);
                    }
                }
            }
        }
        return segmentValues;
    }

    @Override
    public String keySegmentName() {
        return "Provider";
    }

    @Override
    public Class parentType() {
        return Pact.class;
    }

    @Override
    public Class valueType() {
        return Contract.class;
    }

    @Override
    public Object propertyValue(Object parent, String segmentValue) {
        Contract resultingContract = new Contract();

        resultingContract.setInteractions(new ArrayList<>());
        
        if (parent instanceof Pact) {

            Pact pact = (Pact) parent;

            for (Contract contract : pact.getContracts()) {

                Provider provider = contract.getProvider();

                if (provider != null && provider.getName() != null) {

                    String providerName = provider.getName();

                    if (providerName.equalsIgnoreCase(segmentValue)) {

                        mergeIn(resultingContract, contract);
                    }
                }
            }
        }
        return resultingContract;
    }

    private void mergeIn(Contract main, Contract merging) {

        main.getInteractions().addAll(merging.getInteractions());

        main.setConsumer(merging.getConsumer());

        main.setMetadata(merging.getMetadata());
    }

}
