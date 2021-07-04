/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.dcoumentstructure.renderers.microrenderers;

import com.acidmanic.pactdoc.dcoumentstructure.renderers.PactRenderingState;
import com.acidmanic.pactmodels.MatchingRule;
import com.acidmanic.pactmodels.Request;
import com.acidmanic.pactmodels.Response;
import java.util.HashMap;

/**
 *
 * @author diego
 */
public class RulesRenderer {

    public void render(PactRenderingState state, Request request, String dataPath) {

        render(state, request.getMatchingRules(), dataPath);
    }

    public void render(PactRenderingState state, Response response, String dataPath) {

        render(state, response.getMatchingRules(), dataPath);
    }

    public void render(PactRenderingState state, HashMap<String, MatchingRule> matchingRules, String dataPath) {

        if (matchingRules != null && !matchingRules.isEmpty()) {

            state.getPageContext()
                    .newLine()
                    .append("Where")
                    .newLine()
                    .openList();

            for (String rulePath : matchingRules.keySet()) {

                if (rulePath.startsWith(dataPath)) {

                    MatchingRule rule = matchingRules.get(rulePath);

                    render(state, rulePath, rule);
                }
            }
        }
    }

    private void render(PactRenderingState state, String rulePath, MatchingRule rule) {

        FieldPath fieldPath = new FieldPath().fromString(rulePath);

        state.getPageContext().openListItem();

        if (!fieldPath.isWholeObject) {
            state.getPageContext()
                    .append("The field: ")
                    .openBold()
                    .append(fieldPath.path)
                    .closeBold()
                    .append(" of ");
        }
        state.getPageContext()
                .openItalic()
                .append(fieldPath.objectName)
                .closeItalic()
                .append(" ");

        if ("type".equals(rule.getMatch())) {
            state.getPageContext()
                    .append(" must have the same data type with given data in the example.");
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

    private class FieldPath {

        public String objectName;
        public String path;
        public String[] segments;
        private boolean isWholeObject;

        public FieldPath fromString(String rulePath) {

            String[] segments = rulePath.split("\\.");

            if (segments.length >= 2 && "$".equals(segments[0])) {

                this.objectName = segments[1];

                int length = segments.length - 2;

                this.segments = new String[length];

                this.path = "";
                String sep = "";
                for (int i = 0; i < length; i++) {

                    this.segments[i] = segments[i + 2];

                    this.path = sep + this.segments[i];

                    sep = ".";
                }
                this.isWholeObject = this.segments.length == 0;
            }

            return this;
        }
    }
}
