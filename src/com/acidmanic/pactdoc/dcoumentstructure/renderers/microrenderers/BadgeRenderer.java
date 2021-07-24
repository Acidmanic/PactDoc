/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.dcoumentstructure.renderers.microrenderers;

import com.acidmanic.document.structure.Key;
import com.acidmanic.pact.models.EndPoint;
import com.acidmanic.pact.models.Service;
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

        renderInteractionBadgeTable(state.getPageContext(), state.getContext(), state.getNode());
    }

    public void renderPerInteraction(PactRenderingState<Contract> state, Key child) {

        Object endpointObject = state.getAdapter().getContent(child);

        if (endpointObject != null && endpointObject instanceof EndPoint) {

            EndPoint endpoint = (EndPoint) endpointObject;

            renderInteractionBadgeTable(state.getPageContext(), state.getContext(), endpoint);
        }
    }

    public void renderPerEndpoint(PactRenderingState<EndPoint> state) {

        renderBadgeImage(state.getPageContext(), state.getContext(), state.getNode());
    }

    public void renderPerService(PactRenderingState<Contract> state, Key child) {

        Object serviceObject = state.getAdapter().getContent(child);

        if (serviceObject != null && serviceObject instanceof Service) {

            Service service = (Service) serviceObject;

            renderBadgeImage(state.getPageContext(), state.getContext(), service);
        }
    }

    public void renderPerEndpoint(PactRenderingState<Service> state, Key child) {

        Object endpointObject = state.getAdapter().getContent(child);

        if (endpointObject != null && endpointObject instanceof EndPoint) {

            EndPoint endpoint = (EndPoint) endpointObject;

            renderBadgeImage(state.getPageContext(), state.getContext(), endpoint);
        }
    }
    public void renderInteractionBadgeTable(
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


    public void renderBadgeImage(
            PageContext context,
            WikiRenderingContext renderingContext,
            Object object) {

        if (renderingContext.isAddEndpointImplementationBadges()) {

            if (badgeInfoProvider.providesFor(object.getClass())) {

                String imageUrl = renderingContext.getBadgesBaseUri();

                String tag = badgeInfoProvider.translateToBadgeTag(object);

                if (imageUrl.endsWith("/")) {

                    imageUrl = imageUrl.substring(0, imageUrl.length() - 1);
                }
                imageUrl += "/" + tag;

                context.image(imageUrl);

            }
        }
    }

}
