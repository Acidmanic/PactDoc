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
package com.acidmanic.pactdoc.dcoumentstructure;

import com.acidmanic.pactdoc.contractverification.ContractVerifier;
import com.acidmanic.pactdoc.contractverification.ConventionTitle;
import com.acidmanic.pactdoc.dcoumentstructure.badges.implementation.BadgeInfoProvider;
import com.acidmanic.pactdoc.dcoumentstructure.badges.implementation.AcidmanicPactNetCoreBadgeProvider;
import com.acidmanic.pactdoc.dcoumentstructure.models.ConventionEntry;
import com.acidmanic.pactdoc.dcoumentstructure.propertymappers.EndpointFromContractPropertyMapper;
import com.acidmanic.pactdoc.dcoumentstructure.propertymappers.ContractFromPactPropertyMapper;
import com.acidmanic.pactdoc.dcoumentstructure.renderers.ContractPageRenderer;
import com.acidmanic.pactdoc.dcoumentstructure.renderers.ConventionsPageRenderer;
import com.acidmanic.pactdoc.dcoumentstructure.renderers.EndpointPageRenderer;
import com.acidmanic.pactdoc.dcoumentstructure.renderers.PactPageRenderer;
import com.acidmanic.pactdoc.dcoumentstructure.renderers.PageContextProvider;
import java.util.List;

/**
 *
 * @author diego
 */
public class DefaultDocumentDefinition extends WikiDefinitionBase {

    private final ContractVerifier verifier;
    private final BadgeInfoProvider badgeInfoProvider = new AcidmanicPactNetCoreBadgeProvider();

    public DefaultDocumentDefinition(PageContextProvider pageContextProvider,
            PageStore<String> pageStore) {
        super(pageContextProvider, pageStore);

        this.verifier = null;

        this.initialize();
    }

    public DefaultDocumentDefinition(ContractVerifier verifier,
            PageContextProvider pageContextProvider,
            PageStore<String> pageStore) {

        super(pageContextProvider, pageStore);

        this.verifier = verifier;

        this.initialize();
    }

    private void initialize() {

        ContractFromPactPropertyMapper providerPropertyMapper = new ContractFromPactPropertyMapper();

        if (this.verifier != null) {

            List<ConventionTitle> conventionTitles = verifier.conventionTitles();

            ConventionEntry conventionEntry = new ConventionEntry(conventionTitles);

            providerPropertyMapper.setConventionEntry(conventionEntry);

        }

        addLevel(providerPropertyMapper);

        addLevel(new EndpointFromContractPropertyMapper());

        registerRenderer(new PactPageRenderer());

        registerRenderer(new ContractPageRenderer());

        registerRenderer(new EndpointPageRenderer());

        registerRenderer(new ConventionsPageRenderer());
    }

    @Override
    protected BadgeInfoProvider endpointImplementationBadgeProvider() {
        return this.badgeInfoProvider;
    }

}
