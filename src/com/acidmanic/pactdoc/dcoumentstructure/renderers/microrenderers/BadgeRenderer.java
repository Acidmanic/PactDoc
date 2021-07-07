/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.dcoumentstructure.renderers.microrenderers;

import com.acidmanic.document.structure.Key;
import com.acidmanic.pact.models.EndPoint;
import com.acidmanic.pactdoc.dcoumentstructure.badges.implementation.BadgeInfoProvider;
import com.acidmanic.pactdoc.dcoumentstructure.renderers.PactRenderingState;
import com.acidmanic.pactdoc.dcoumentstructure.renderers.PageContext;
import com.acidmanic.pactdoc.wiki.WikiRenderingContext;
import com.acidmanic.pactmodels.Contract;
import com.acidmanic.pactmodels.Interaction;
import java.util.ArrayList;

/**
 *
 * @author diego
 */
public class BadgeRenderer {

    private final BadgeInfoProvider badgeInfoProvider;

    public BadgeRenderer(BadgeInfoProvider badgeInfoProvider) {
        this.badgeInfoProvider = badgeInfoProvider;
    }

    public void renderPerInteraction(PactRenderingState<EndPoint> state) {

        renderPerInteraction(state.getPageContext(), state.getContext(), state.getNode());
    }

    public void renderPerInteraction(PactRenderingState<Contract> state, Key child) {

        Object endpointObject = state.getAdapter().getContent(child);

        if (endpointObject != null && endpointObject instanceof EndPoint) {

            EndPoint endpoint = (EndPoint) endpointObject;

            renderPerInteraction(state.getPageContext(), state.getContext(), endpoint);
        }
    }

    public void renderPerEndpoint(PactRenderingState<EndPoint> state) {

        renderPerEndpoint(state.getPageContext(), state.getContext(), state.getNode());
    }

    public void renderPerEndpoint(PactRenderingState<Contract> state, Key child) {

        Object endpointObject = state.getAdapter().getContent(child);

        if (endpointObject != null && endpointObject instanceof EndPoint) {

            EndPoint endpoint = (EndPoint) endpointObject;

            renderPerEndpoint(state.getPageContext(), state.getContext(), endpoint);
        }
    }

    public void renderPerInteraction(
            PageContext context,
            WikiRenderingContext renderingContext,
            EndPoint endpoint) {

        if (renderingContext.isAddEndpointImplementationBadges()) {

            if (badgeInfoProvider.providesFor(Interaction.class)) {

                ArrayList<String> headers = new ArrayList<>();
                headers.add("Provider State        ");
                headers.add("Method");
                headers.add("Status");

                context.openTable(headers);

                for (Interaction interaction : endpoint.getInteractions()) {

                    String imageUrl = renderingContext.getBadgesBaseUri();

                    String tag = badgeInfoProvider.translateToBadgeTag(interaction);

                    if (imageUrl.endsWith("/")) {

                        imageUrl = imageUrl.substring(0, imageUrl.length() - 1);
                    }
                    imageUrl += "/" + tag;

                    context.openTableRow()
                            .openCell().append(interaction.getProviderState()).closeCell()
                            .openCell().badge(interaction.getRequest().getMethod()).closeCell()
                            .openCell().image(imageUrl).closeCell()
                            .closeTableRow();
                }

                context.closeTable();
            }
        }
    }

    public void renderPerEndpoint(
            PageContext context,
            WikiRenderingContext renderingContext,
            EndPoint endpoint) {

        if (renderingContext.isAddEndpointImplementationBadges()) {

            if (badgeInfoProvider.providesFor(EndPoint.class)) {

                if (!endpoint.getInteractions().isEmpty()) {

                    String imageUrl = renderingContext.getBadgesBaseUri();

                    String tag = badgeInfoProvider.translateToBadgeTag(endpoint);

                    if (imageUrl.endsWith("/")) {

                        imageUrl = imageUrl.substring(0, imageUrl.length() - 1);
                    }
                    imageUrl += "/" + tag;

                    context.image(imageUrl);

                }

            }
        }
    }

}
