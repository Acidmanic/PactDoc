/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.dcoumentstructure.renderers.microrenderers;

import com.acidmanic.consoletools.rendering.RenderingContext;
import com.acidmanic.document.structure.Key;
import com.acidmanic.pact.models.EndPoint;
import com.acidmanic.pactdoc.dcoumentstructure.badges.implementation.BadgeInfoProvider;
import com.acidmanic.pactdoc.dcoumentstructure.renderers.PactRenderingState;
import com.acidmanic.pactdoc.dcoumentstructure.renderers.PageContext;
import com.acidmanic.pactdoc.wiki.WikiRenderingContext;
import com.acidmanic.pactmodels.Contract;

/**
 *
 * @author diego
 */
public class BadgeRenderer {

    private final BadgeInfoProvider badgeInfoProvider;

    public BadgeRenderer(BadgeInfoProvider badgeInfoProvider) {
        this.badgeInfoProvider = badgeInfoProvider;
    }

    public void render(PactRenderingState<EndPoint> state) {
        
        render(state.getPageContext(), state.getContext(),state.getNode());
    }

    public void render(PactRenderingState<Contract> state, Key child) {

        Object endpointObject = state.getAdapter().getContent(child);

        if (endpointObject != null && endpointObject instanceof EndPoint) {

            EndPoint endpoint = (EndPoint) endpointObject;
            
            render(state.getPageContext(), state.getContext(),endpoint);
        }
    }

    public void render(
            PageContext context,
            WikiRenderingContext renderingContext,
            EndPoint endpoint) {

        if (renderingContext.isAddEndpointImplementationBadges()) {

            if (!badgeInfoProvider.equals(BadgeInfoProvider.NULL)) {

                String imageUrl = renderingContext.getBadgesBaseUri();

                String endpointPath = endpoint.getInteractions().get(0).getRequest().getPath();

                String tag = badgeInfoProvider.translateToBadgeTag(endpointPath);

                if (imageUrl.endsWith("/")) {

                    imageUrl = imageUrl.substring(0, imageUrl.length() - 1);
                }
                imageUrl += "/" + tag;

                context.openBold()
                        .image(imageUrl)
                        .closeBold();
            }
        }
    }

}
