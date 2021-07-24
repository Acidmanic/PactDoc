/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.dcoumentstructure.renderers;

import com.acidmanic.pact.models.Service;

/**
 *
 * @author diego
 */
public class ServicesPageRenderer extends MenuPageRendererBase<Service> {

    public ServicesPageRenderer() {
        super("Services:");
    }

    @Override
    public Class renderingType() {
        return Service.class;
    }
    
    

}
