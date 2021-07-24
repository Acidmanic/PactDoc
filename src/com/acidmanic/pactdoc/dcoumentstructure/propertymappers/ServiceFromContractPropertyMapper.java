/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.dcoumentstructure.propertymappers;

import com.acidmanic.document.structure.propertymapped.PropertyMapper;
import com.acidmanic.pact.helpers.NameExtractor;
import com.acidmanic.pact.helpers.PactClassifier;
import com.acidmanic.pact.models.EndPoint;
import com.acidmanic.pact.models.Service;
import com.acidmanic.pactdoc.dcoumentstructure.namextractors.EndpointFromInteractionNameExtractor;
import com.acidmanic.pactdoc.dcoumentstructure.namextractors.ServiceFromEndpointNameExtractor;
import com.acidmanic.pactdoc.dcoumentstructure.namextractors.ServiceFromInteractionNameExtractor;
import com.acidmanic.pactdoc.dcoumentstructure.namextractors.ServiceNameExtractor;
import com.acidmanic.pactmodels.Contract;
import com.acidmanic.pactmodels.Interaction;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author diego
 */
public class ServiceFromContractPropertyMapper implements PropertyMapper {

    private final NameExtractor<Interaction> serviceFromInteractionNameExtractor = new ServiceFromInteractionNameExtractor();
    private final NameExtractor<Service> serviceNameExtractor = new ServiceNameExtractor();

    @Override
    public List<String> keySegmentValues(Object parent) {

        List<String> segments = new ArrayList<>();

        if (parent instanceof Contract) {

            Contract contract = (Contract) parent;

            for (Interaction interaction : contract.getInteractions()) {

                String serviceName = serviceFromInteractionNameExtractor.extract(interaction);

                if (serviceName != null) {

                    if (!containesIgnoreCase(segments, serviceName)) {

                        segments.add(serviceName);
                    }
                }

            }
        }
        return segments;
    }

    private boolean containesIgnoreCase(List<String> list, String value) {

        for (String item : list) {

            if (item.equalsIgnoreCase(value)) {

                return true;
            }
        }
        return false;
    }

    @Override
    public String keySegmentName() {
        return "Service";
    }

    @Override
    public Class parentType() {
        return Contract.class;
    }

    @Override
    public Object propertyValue(Object parent, String keySegmentValue) {

        if (parent instanceof Contract) {

            Contract contract = (Contract) parent;

            PactClassifier classifier = new PactClassifier();

            List<EndPoint> endPoints = classifier.splitByEndpoint(contract.getInteractions(),
                    new EndpointFromInteractionNameExtractor());

            List<Service> services = classifier.splitByService(endPoints, new ServiceFromEndpointNameExtractor());

            if (keySegmentValue != null) {

                for (Service service : services) {

                    if (keySegmentValue.equals(serviceNameExtractor.extract(service))) {

                        return service;
                    }
                }
            }

        }
        return null;
    }

}
