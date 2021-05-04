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
import com.acidmanic.pact.helpers.RequestPathBuilder;
import com.acidmanic.pact.models.EndPoint;
import com.acidmanic.pact.models.Pact;
import com.acidmanic.pact.models.RequestPath;
import com.acidmanic.pactdoc.dcoumentstructure.badges.implementation.BadgeInfoProvider;
import com.acidmanic.pactdoc.wiki.WikiRenderingContext;
import com.acidmanic.pactmodels.Interaction;
import com.acidmanic.pactmodels.Request;
import com.acidmanic.pactmodels.Response;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author diego
 */
public class EndpointPageRenderer extends PageRendererBase<EndPoint> {

    public EndpointPageRenderer() {
        super("Endpoint details:");
    }

    @Override
    protected void renderContent(Key key,
            EndPoint endpoint,
            Pact root,
            List<Key> childs,
            PageContext pageContext,
            WikiRenderingContext renderingContext) {

        if (endpoint.getInteractions().isEmpty()) {

            pageContext.openTitle()
                    .append("Something does not add up!")
                    .closeTitle()
                    .openSubtitle()
                    .append("There must be at least on interaction defined for each Endpoint.")
                    .closeSubtitle();
            return;
        }

        if (renderingContext.isAddEndpointImplementationBadges()) {

            BadgeInfoProvider provider = this.getEndpointImplementationBadgeInfoProvider();

            if (!provider.equals(BadgeInfoProvider.NULL)) {

                String imageUrl = renderingContext.getBadgesBaseUri();

                String endpointPath = endpoint.getInteractions().get(0).getRequest().getPath();

                String tag = provider.translateToBadgeTag(endpointPath);

                if (imageUrl.endsWith("/")) {

                    imageUrl = imageUrl.substring(0, imageUrl.length() - 1);
                }
                imageUrl += "/" + tag;

                pageContext.openBold()
                        .append("Implementation Status: ")
                        .image(imageUrl)
                        .closeBold()
                        .horizontalLine();
            }

        }

        for (Interaction interaction : endpoint.getInteractions()) {

            Request request = interaction.getRequest();

            String pathString = request.getPath();

            RequestPath path = new RequestPathBuilder().build(pathString);

            pathString = new RequestPathBuilder().stripParameters(pathString);

            String method = request.getMethod().toUpperCase();

            pageContext
                    .openBold()
                    .append(pathString).append("-").append(method)
                    .closeBold()
                    .horizontalLine()
                    .newLine()
                    .newLine()
                    .append(interaction.getDescription())
                    .newLine()
                    .append("When ")
                    .append(interaction.getProviderState())
                    .append(" an http ").badge(method).append(" request to '")
                    .append(pathString).append("' ");

            if (!request.getHeaders().isEmpty()) {

                pageContext.append("with headers: ")
                        .newLine()
                        .table("Key", "Value", request.getHeaders())
                        .newLine();
            }

            if (!path.getParameters().isEmpty()) {

                pageContext.append("with path parameters: ")
                        .newLine()
                        .table(path.getParameters())
                        .newLine();
            }

            LinkedHashMap body = request.getBody();

            if (body != null) {

                String jsonBody = toJson(body);

                pageContext.append("with body: ")
                        .newLine()
                        .json(jsonBody)
                        .newLine();
            }

            Response response = interaction.getResponse();

            pageContext.append("will be responded with status code: ")
                    .openBold().append(Integer.toString(response.getStatus()))
                    .closeBold()
                    .newLine();

            body = response.getBody();

            if (body != null) {

                String jsonBody = toJson(body);

                pageContext.append("Response will have a body like: ")
                        .newLine()
                        .json(jsonBody)
                        .newLine();
            }

            pageContext.horizontalLine().newLine();
        }
    }

    @Override
    public Class renderingType() {
        return EndPoint.class;
    }

    private String toJson(LinkedHashMap body) {

        try {
            ObjectMapper mapper = new ObjectMapper();

            String json = mapper.writeValueAsString(body);

            return json;
        } catch (JsonProcessingException ex) {
            Logger.getLogger(EndpointPageRenderer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }

}
