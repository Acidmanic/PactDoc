/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.dcoumentstructure.propertymappers;

import com.acidmanic.document.structure.propertymapped.PropertyMapper;
import com.acidmanic.pact.models.Service;
import com.acidmanic.pactdoc.dcoumentstructure.namextractors.EndpointNameExtractor;
import com.acidmanic.pactdoc.utility.LinQ;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author diego
 */
public class EndpointFromServicePropertyMapper implements PropertyMapper {

    @Override
    public List<String> keySegmentValues(Object parent) {

        if (parent instanceof Service) {

            Service service = (Service) parent;

            if (service.getEndpoints() != null) {

                EndpointNameExtractor extractor = new EndpointNameExtractor();

                return LinQ.select(service.getEndpoints(), ep -> extractor.extract(ep));
            }
        }
        return new ArrayList<>();
    }

    @Override
    public String keySegmentName() {
        return "Service";
    }

    @Override
    public Class parentType() {
        return Service.class;
    }

    @Override
    public Object propertyValue(Object parent, String keySegmentValue) {

        if (parent instanceof Service) {

            Service service = (Service) parent;

            if (service.getEndpoints() != null) {

                EndpointNameExtractor extractor = new EndpointNameExtractor();

                return LinQ.first(service.getEndpoints(), ep -> keySegmentValue.equals(extractor.extract(ep)));
            }
        }
        return null;
    }

}
