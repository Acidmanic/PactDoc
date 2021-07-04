/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.dcoumentstructure.renderers.microrenderers;

import com.acidmanic.pact.helpers.RequestPathBuilder;
import com.acidmanic.pact.models.RequestPath;
import com.acidmanic.pactdoc.dcoumentstructure.renderers.PactRenderingState;
import com.acidmanic.pactmodels.Request;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

/**
 *
 * @author diego
 */
public class RequestQueryRenderer {

    public void render(PactRenderingState state, Request request) {

        String fullUri = request.getPath();

        if (request.getQuery() != null && request.getQuery().length() > 0) {

            fullUri += "?" + request.getQuery();
        }

        RequestPath path = new RequestPathBuilder().build(fullUri);

        if (!path.getParameters().isEmpty()) {

            HashMap<String, String> decodedParameters = httpDecode(path.getParameters());

            state.getPageContext().append("with query parameters: ")
                    .newLine()
                    .table("Parameter", "Value", decodedParameters)
                    .newLine();

            String encodedUri = addEncodedParameters(request.getPath(), decodedParameters);

            state.getPageContext()
                    .append(" example:  ")
                    .badge(encodedUri)
                    .append("  .")
                    .newLine().newLine();

            new RulesRenderer().render(state, request, "$.query");
        }
    }

    private HashMap<String, String> httpDecode(HashMap<String, String> parameters) {

        HashMap<String, String> decodedParameters = new HashMap<>();

        for (String parameter : parameters.keySet()) {

            String value = parameters.get(parameter);

            String decodedKey = safeHttpDecode(parameter);

            String decodedValue = safeHttpDecode(value);

            decodedParameters.put(decodedKey, decodedValue);
        }
        return decodedParameters;
    }

    private String safeHttpDecode(String value) {
        try {

            return URLDecoder.decode(value, StandardCharsets.UTF_8.name());
        } catch (Exception e) {
        }
        return value;
    }

    private String safeHttpEncode(String value) {
        try {

            return URLEncoder.encode(value, StandardCharsets.UTF_8.name());
        } catch (Exception e) {
        }
        return value;
    }

    private String addEncodedParameters(String path, HashMap<String, String> decodedParameters) {

        String fullPath = path;

        if (decodedParameters != null && !decodedParameters.isEmpty()) {
            fullPath += "?";

            String sep = "";

            for (String parameter : decodedParameters.keySet()) {

                String value = decodedParameters.get(parameter);

                fullPath += sep + parameter + "=" + safeHttpEncode(value);

                sep = "&";
            }
        }
        return fullPath;
    }
}
