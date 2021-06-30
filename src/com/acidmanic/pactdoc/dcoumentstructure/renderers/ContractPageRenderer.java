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
package com.acidmanic.pactdoc.dcoumentstructure.renderers;

import com.acidmanic.document.structure.Key;
import com.acidmanic.pact.models.EndPoint;
import com.acidmanic.pactdoc.dcoumentstructure.badges.implementation.BadgeInfoProvider;
import com.acidmanic.pactmodels.Contract;

/**
 *
 * @author diego
 */
public class ContractPageRenderer extends MenuPageRendererBase<Contract> {

    public ContractPageRenderer() {
        super("End points");
    }

    @Override
    public Class renderingType() {
        return Contract.class;
    }

    @Override
    protected void preChildRender(Key child, PactRenderingState<Contract> state) {

        if (state.getContext().isAddEndpointImplementationBadges()) {

            BadgeInfoProvider provider = this.getEndpointImplementationBadgeInfoProvider();

            if (!provider.equals(BadgeInfoProvider.NULL)) {

                String imageUrl = state.getContext().getBadgesBaseUri();

                Object endpointObject = state.getAdapter().getContent(child);

                if (endpointObject != null && endpointObject instanceof EndPoint) {

                    EndPoint endpoint = (EndPoint) endpointObject;

                    String endpointPath = endpoint.getInteractions().get(0).getRequest().getPath();

                    String tag = provider.translateToBadgeTag(endpointPath);

                    if (imageUrl.endsWith("/")) {

                        imageUrl = imageUrl.substring(0, imageUrl.length() - 1);
                    }
                    imageUrl += "/" + tag;

                    state.getPageContext().openBold()
                            .image(imageUrl)
                            .closeBold()
                            .append("  ");
                }

            }
        }
    }

}
