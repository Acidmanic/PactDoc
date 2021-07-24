/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.dcoumentstructure.renderers;

import com.acidmanic.document.structure.Key;
import com.acidmanic.pact.models.Service;
import com.acidmanic.pactdoc.dcoumentstructure.renderers.microrenderers.BadgeRenderer;

/**
 *
 * @author diego
 */
public class ServicesPageRenderer extends MenuPageRendererBase<Service> {

    public ServicesPageRenderer() {
        super("Endpoints:");
    }

    @Override
    public Class renderingType() {
        return Service.class;
    }
    
     @Override
    protected void preChildRender(Key child, PactRenderingState<Service> state) {

        BadgeRenderer badgeRenderer = new BadgeRenderer(this.getEndpointImplementationBadgeInfoProvider());

        badgeRenderer.renderPerEndpoint(state, child);

        state.getPageContext().append("  ");
    }
    

}
