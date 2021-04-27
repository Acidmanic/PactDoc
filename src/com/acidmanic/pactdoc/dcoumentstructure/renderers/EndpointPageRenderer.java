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
import com.acidmanic.pactmodels.Interaction;
import com.acidmanic.pactmodels.Request;
import com.acidmanic.pactmodels.Response;
import java.util.List;

/**
 *
 * @author diego
 */
public class EndpointPageRenderer extends PageRendererBase<EndPoint> {

    public EndpointPageRenderer() {
        super("Endpoint details:");
    }

    @Override
    protected void renderContent(Key key, EndPoint node, Pact root, List<Key> childs, PageContext pageContext) {

        for (Interaction interaction : node.getInteractions()) {

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

            String body = request.getBody();

            if (body != null && body.length() > 0) {

                pageContext.append("with body: ")
                        .newLine()
                        .json(body)
                        .newLine();
            }

            Response response = interaction.getResponse();

            pageContext.append("will be responded with status code: ")
                    .openBold().append(Integer.toString(response.getStatus()))
                    .closeBold()
                    .newLine();

            body = response.getBody();

            if (body != null && body.length() > 0) {

                pageContext.append("Response will have a body like: ")
                        .newLine()
                        .json(body)
                        .newLine();
            }

            pageContext.horizontalLine().newLine();
        }
    }

    @Override
    public Class renderingType() {
        return EndPoint.class;
    }

}
