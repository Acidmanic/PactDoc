/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.dcoumentstructure.propertymappers;

import com.acidmanic.document.structure.propertymapped.PropertyMapper;
import com.acidmanic.pact.helpers.PactClassifier;
import com.acidmanic.pact.models.EndPoint;
import com.acidmanic.pact.models.Service;
import com.acidmanic.pactdoc.dcoumentstructure.namextractors.EndpointNameExtractor;
import com.acidmanic.pactdoc.dcoumentstructure.namextractors.ServiceNameExtractor;
import com.acidmanic.pactmodels.Contract;
import com.acidmanic.pactmodels.Interaction;
import com.acidmanic.pactmodels.Request;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author diego
 */
public class ServicePropertyMapper implements PropertyMapper {

    @Override
    public List<String> keySegmentValues(Object parent) {

        List<String> segments = new ArrayList<>();

        if (parent instanceof Contract) {

            Contract contract = (Contract) parent;

            for (Interaction interaction : contract.getInteractions()) {

                String serviceName = getServiceName(interaction);

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

    /**
     * This method defines service as first segment of the request path
     *
     * @param interaction
     * @return
     */
    public static String getServiceName(Interaction interaction) {

        if (interaction.getRequest() != null) {

            Request request = interaction.getRequest();

            String path = request.getPath();

            if (path != null && path.length() > 0) {

                String[] uriSegments = path.split("/");

                if (uriSegments.length > 0) {
                    return uriSegments[0];
                }
            }
        }
        return "";
    }

    public static String getServiceName(EndPoint endpoint) {

        if (endpoint.getInteractions() != null && !endpoint.getInteractions().isEmpty()) {

            return getServiceName(endpoint.getInteractions().get(0));
        }
        return "";
    }

    public static String getServiceName(Service service) {

        if (service.getEndpoints() != null && !service.getEndpoints().isEmpty()) {

            return getServiceName(service.getEndpoints().get(0));
        }
        return "";
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

            List<EndPoint> endPoints = classifier.splitByEndpoint(
                    contract.getInteractions(),
                    new EndpointNameExtractor());

            List<Service> services = classifier.splitByService(
                    endPoints, new ServiceNameExtractor());

            if (keySegmentValue != null) {

                for (Service service : services) {

                    if (keySegmentValue.equalsIgnoreCase(getServiceName(service))) {

                        return service;
                    }
                }
            }

        }
        return null;
    }

}
