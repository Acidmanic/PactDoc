/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.dcoumentstructure.namextractors;

import com.acidmanic.pact.helpers.NameExtractor;
import com.acidmanic.pact.models.Service;

/**
 *
 * @author diego
 */
public class ServiceNameExtractor implements NameExtractor<Service> {

    @Override
    public String extract(Service sourceData) {

        if (sourceData.getEndpoints() != null && !sourceData.getEndpoints().isEmpty()) {

            return new ServiceFromEndpointNameExtractor().extract(sourceData.getEndpoints().get(0));
        }
        return "";
    }

}
