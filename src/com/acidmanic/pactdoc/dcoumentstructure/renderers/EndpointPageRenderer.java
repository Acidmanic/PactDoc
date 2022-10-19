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

import com.acidmanic.pactdoc.dcoumentstructure.renderers.microrenderers.BadgeRenderer;
import com.acidmanic.pact.models.EndPoint;
import com.acidmanic.pactdoc.dcoumentstructure.renderers.microrenderers.JsonRenderer;
import com.acidmanic.pactdoc.dcoumentstructure.renderers.microrenderers.RequestQueryRenderer;
import com.acidmanic.pactdoc.dcoumentstructure.renderers.microrenderers.RulesRenderer;
import com.acidmanic.pactmodels.Interaction;
import com.acidmanic.pactmodels.Request;
import com.acidmanic.pactmodels.Response;

/**
 *
 * @author diego
 */
public class EndpointPageRenderer extends PageRendererBase<EndPoint> {

    public EndpointPageRenderer() {
        super("Endpoint details:");
    }

    @Override
    protected void renderContent(PactRenderingState<EndPoint> state) {

        if (state.getNode().getInteractions().isEmpty()) {

            state.getPageContext().openTitle()
                    .append("Something does not add up!")
                    .closeTitle()
                    .openSubtitle()
                    .append("There must be at least on interaction defined for each Endpoint.")
                    .closeSubtitle();
            return;
        }

        state.getPageContext()
                .openBold()
                .append("Implementation Status:")
                .closeBold().append(" ");

        BadgeRenderer badgeRenderer = new BadgeRenderer(this.getEndpointImplementationBadgeInfoProvider());

        badgeRenderer.renderPerInteraction(state);

        state.getPageContext().horizontalLine();

        for (Interaction interaction : state.getNode().getInteractions()) {

            Request request = interaction.getRequest();

            String method = request.getMethod().toUpperCase();

            state.getPageContext()
                    .openBold()
                    .append(request.getPath()).append("-").append(method)
                    .closeBold()
                    .horizontalLine()
                    .newLine()
                    .newLine()
                    .append(interaction.getDescription())
                    .newLine()
                    .append("When ")
                    .append(interaction.getProviderState())
                    .append(" an http ").badge(method).append(" request to '")
                    .append(request.getPath()).append("' ");

            if (request.getHeaders()!=null && !request.getHeaders().isEmpty()) {

                state.getPageContext().append("with headers: ")
                        .newLine()
                        .table("Key", "Value", request.getHeaders())
                        .newLine();

                new RulesRenderer().render(state, request, "$.headers");
            }

            new RequestQueryRenderer().render(state, request);

            new JsonRenderer().Render(state, request.getBody(), "with body: ");

            new RulesRenderer().render(state, request, "$.body");

            Response response = interaction.getResponse();

            state.getPageContext().newLine()
                    .append("This request, will be responded with status code: ")
                    .openBold().append(Integer.toString(response.getStatus()))
                    .closeBold()
                    .newLine();

            new JsonRenderer().Render(state, response.getBody(), "And response will have a body like: ");

            new RulesRenderer().render(state, response, "$.body");

            if (!response.getHeaders().isEmpty()) {

                state.getPageContext().append("with headers: ")
                        .newLine()
                        .table("Key", "Value", response.getHeaders())
                        .newLine();

                new RulesRenderer().render(state, response, "$.headers");
            }

            state.getPageContext().horizontalLine().newLine();
        }
    }

    @Override
    public Class renderingType() {
        return EndPoint.class;
    }

}
