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
import com.acidmanic.pactmodels.Contract;
import com.acidmanic.pactmodels.Interaction;
import com.acidmanic.pactmodels.Request;
import com.acidmanic.pact.models.EndPoint;
import com.acidmanic.pact.helpers.RequestPathBuilder;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author diego
 */
public class EndpointPropertyMapper implements PropertyMapper {

    @Override
    public List<String> keySegmentValues(Object parent) {

        List<String> segments = new ArrayList<>();

        if (parent instanceof Contract) {

            Contract contract = (Contract) parent;

            for (Interaction interaction : contract.getInteractions()) {

                Request request = interaction.getRequest();

                if (request != null && request.getPath() != null) {

                    String pathString = request.getPath();

                    String endPoint = new RequestPathBuilder().stripParameters(pathString);

                    if (!containsIgnoreCase(segments, endPoint)) {

                        segments.add(endPoint);
                    }
                }
            }
        }
        return segments;
    }

    @Override
    public String keySegmentName() {
        return "Endpoint";
    }

    @Override
    public Class parentType() {
        return Contract.class;
    }

    @Override
    public Class valueType() {
        return EndPoint.class;
    }

    @Override
    public Object propertyValue(Object parent, String segmentValue) {

        EndPoint endPoint = new EndPoint();

        if (parent instanceof Contract) {

            Contract contract = (Contract) parent;

            for (Interaction interaction : contract.getInteractions()) {

                Request request = interaction.getRequest();

                if (request != null && request.getPath() != null) {

                    String pathString = request.getPath();

                    String endPointUri = new RequestPathBuilder().stripParameters(pathString);

                    if (segmentValue.equalsIgnoreCase(endPointUri)) {

                        endPoint.getInteractions().add(interaction);
                    }
                }
            }
        }
        return endPoint;
    }

    private boolean containsIgnoreCase(List<String> segments, String endPoint) {
        for (String segment : segments) {

            if (segment.equalsIgnoreCase(endPoint)) {
                return true;
            }
        }
        return false;
    }

}
