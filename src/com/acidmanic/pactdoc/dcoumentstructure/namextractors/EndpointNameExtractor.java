/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.dcoumentstructure.namextractors;

import com.acidmanic.pact.helpers.NameExtractor;
import com.acidmanic.pact.models.EndPoint;

/**
 *
 * @author diego
 */
public class EndpointNameExtractor implements NameExtractor<EndPoint> {

    @Override
    public String extract(EndPoint sourceData) {

        if (sourceData.getInteractions() != null && !sourceData.getInteractions().isEmpty()) {

            return new EndpointFromInteractionNameExtractor()
                    .extract(sourceData.getInteractions().get(0));
        }
        return "";
    }

}
