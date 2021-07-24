/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.dcoumentstructure.namextractors;

import com.acidmanic.pact.helpers.NameExtractor;
import com.acidmanic.pact.helpers.Normalizer;
import com.acidmanic.pact.models.EndPoint;
import com.acidmanic.pactmodels.Interaction;
import com.acidmanic.pactmodels.Request;

/**
 *
 * @author diego
 */
public class ServiceFromEndpointNameExtractor implements NameExtractor<EndPoint>, Normalizer<String> {

    @Override
    public String extract(EndPoint sourceData) {

        if (sourceData.getInteractions() != null && !sourceData.getInteractions().isEmpty()) {

            Interaction interaction = sourceData.getInteractions().get(0);

            if (interaction != null && interaction.getRequest() != null) {

                Request request = interaction.getRequest();

                String path = request.getPath();

                if (path != null && path.length() > 0) {

                    String[] uriSegments = path.split("/");

                    if (uriSegments.length > 0) {
                        
                        return uriSegments[0];
                    }
                }
            }
        }
        return "";
    }

    @Override
    public String normalize(String value) {

        return value;
    }

}
