/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.dcoumentstructure.renderers.microrenderers;

import com.acidmanic.delegates.arg1.Function;
import com.acidmanic.pact.helpers.DataReader;
import com.acidmanic.pact.models.DataPath;
import com.acidmanic.pactdoc.dcoumentstructure.renderers.PactRenderingState;
import com.acidmanic.pactmodels.MatchingRule;
import com.acidmanic.pactmodels.Request;
import com.acidmanic.pactmodels.Response;
import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 *
 * @author diego
 */
public class RulesRenderer {

    private final static String DEFAULT_TYPE_DESCRIPTION = " same as the data type in the example";
    private final static String STRING_TYPE_DESCRIPTION = " a string";

    /**
     * This method will render matching rules of request, which start with
     * dataPath. This way, client code can choose when to render body, query,
     * headers or any other sub-paths.
     *
     * @param state
     * @param request
     * @param dataPath
     */
    public void render(PactRenderingState state, Request request, String dataPath) {

        if (dataPath != null) {

            if (dataPath.startsWith("$.body")) {

                render(state, request.getMatchingRules(), dataPath,
                        s -> describeBodyData(request.getBody(), s));

            } else if (dataPath.startsWith("$.headers") || dataPath.startsWith("$.query")) {

                render(state, request.getMatchingRules(), dataPath,
                        s -> STRING_TYPE_DESCRIPTION);

            } else {
                render(state, request.getMatchingRules(), dataPath, s -> DEFAULT_TYPE_DESCRIPTION);
            }
        }
    }

    /**
     * This method will render matching rules of response, which start with
     * dataPath. This way, client code can choose when to render headers or body
     * or any other sub-paths.
     *
     * @param state
     * @param response
     * @param dataPath
     */
    public void render(PactRenderingState state, Response response, String dataPath) {

        if (dataPath != null) {

            if (dataPath.startsWith("$.body")) {

                render(state, response.getMatchingRules(), dataPath,
                        s -> describeBodyData(response.getBody(), s));

            } else if (dataPath.startsWith("$.headers")) {

                render(state, response.getMatchingRules(), dataPath,
                        s -> STRING_TYPE_DESCRIPTION);

            } else {
                render(state, response.getMatchingRules(), dataPath, s -> DEFAULT_TYPE_DESCRIPTION);
            }
        }
    }

    /**
     * This method will get the type name of given path if possible. and will
     * return a description otherwise.
     *
     * @param data
     * @param bodyDataPath
     * @return
     */
    private String describeBodyData(LinkedHashMap data, String bodyDataPath) {

        String starter = "$.body.";

        if (bodyDataPath.startsWith(starter)) {

            int starterLength = starter.length();

            String dataPath = "$." + bodyDataPath.substring(starterLength,bodyDataPath.length());

            DataPath path = new DataPath().fromString(dataPath);

            Object value = new DataReader().read(data, path);

            if (value == null) {
                return DEFAULT_TYPE_DESCRIPTION;
            }
            if (value instanceof LinkedHashMap) {
                return " with same structure as " + path.toString()
                        + " in the example";
            }
            return value.getClass().getSimpleName();

        } else {
            return DEFAULT_TYPE_DESCRIPTION;
        }
    }

    /**
     * This method can render only matching rules without knowing their data
     * types, so it uses default description for all rules.
     *
     * @param state
     * @param matchingRules
     * @param dataPath
     */
    public void render(PactRenderingState state, HashMap<String, MatchingRule> matchingRules, String dataPath) {

        render(state, matchingRules, dataPath, s -> DEFAULT_TYPE_DESCRIPTION);
    }

    /**
     * This method will render all given rules, and for type rules, it will use
     * the given type descriptor
     *
     * @param state
     * @param matchingRules
     * @param dataPath
     * @param typeDescriptor
     */
    private void render(
            PactRenderingState state,
            HashMap<String, MatchingRule> matchingRules,
            String dataPath,
            Function<String, String> typeDescriptor) {

        if (matchingRules != null && !matchingRules.isEmpty()) {

            state.getPageContext()
                    .newLine()
                    .append("Where")
                    .newLine()
                    .openList();

            for (String rulePath : matchingRules.keySet()) {

                if (rulePath.startsWith(dataPath)) {

                    MatchingRule rule = matchingRules.get(rulePath);

                    render(state, rulePath, rule, typeDescriptor);
                }
            }
        }
    }

    /**
     * Renders Given rule. for a type rule, it uses given descriptor
     *
     * @param state
     * @param rulePath
     * @param rule
     * @param typeDescriptor
     */
    private void render(PactRenderingState state,
            String rulePath,
            MatchingRule rule,
            Function<String, String> typeDescriptor) {

        DataPath fieldPath = new DataPath().fromString(rulePath);

        state.getPageContext().openListItem();

        if (!fieldPath.pointsToWholeObject()) {
            state.getPageContext()
                    .append("The field: ")
                    .openBold()
                    .append(fieldPath.relativizeToBaseObject().toString())
                    .closeBold()
                    .append(" of ");
        }
        state.getPageContext()
                .openItalic()
                .append(fieldPath.getObjectName())
                .closeItalic()
                .append(" ");

        if ("type".equals(rule.getMatch())) {

            String dataTypeDescription = typeDescriptor.perform(rulePath);

            state.getPageContext()
                    .append(" must have the data type " + dataTypeDescription + ".");

        } else if ("regex".equals(rule.getMatch())) {
            state.getPageContext()
                    .append(" must be a string matching: ")
                    .badge(rule.getRegex())
                    .append("  expression.");
        } else {
            state.getPageContext()
                    .append(" must match exactly the given data.");
        }
        state.getPageContext().closeListItem();
    }

}
