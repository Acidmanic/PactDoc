/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.dcoumentstructure.renderers.microrenderers;

import com.acidmanic.pactdoc.dcoumentstructure.renderers.PactRenderingState;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.LinkedHashMap;

/**
 *
 * @author diego
 */
public class JsonRenderer {
    

    public void Render(PactRenderingState state, LinkedHashMap body, String description) {

        if (body != null && !body.isEmpty()) {

            String jsonBody = toJson(body);

            state.getPageContext().append("with body: ")
                    .newLine()
                    .json(jsonBody)
                    .newLine();
        }
    }

    private String toJson(LinkedHashMap body) {

        try {
            ObjectMapper mapper = new ObjectMapper();

            String json = mapper.writeValueAsString(body);

            return json;
        } catch (JsonProcessingException ex) {
            
        }
        return "";
    }
}
