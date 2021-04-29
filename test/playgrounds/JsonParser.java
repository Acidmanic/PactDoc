/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package playgrounds;

import com.acidmanic.pactdoc.utility.jsonparsing.JsonPrettifierInterceptor;
import com.acidmanic.pactdoc.utility.jsonparsing.JsonInterceptorBase;
import com.acidmanic.pactdoc.utility.jsonparsing.JsonStringState;

/**
 *
 * @author diego
 */
public class JsonParser {

    private static class JsonLogger extends JsonInterceptorBase {

        @Override
        protected void deliver(String text, JsonStringState state) {

            String stateName = state.getClass().getSimpleName();

            stateName = stateName.replace("state", "");
            stateName = stateName.replace("State", "");
            stateName = stateName.replace("Object", "");
            stateName = stateName.replace("In", "");

            System.out.println(stateName + ":\t" + text);
        }

    }

    public static void main(String[] args) {

        String json = "{\n"
                + "         \n"
                + "         \"name\": \"John\", \"age\": 31, \"city\": \"NewYork\"\n,"
                + "\n\"data\":{"
                + "\n\"id\":834.2,\"token\": \"8fjfujrer40jldrt\""
                + "\n\n}, \"year\": 1397"
                + "     }";

        JsonLogger logger = new JsonLogger();

        logger.parse(json);

        JsonPrettifierInterceptor indenter = new JsonPrettifierInterceptor();

        indenter.parse(json);

        String reJson = indenter.getPrettifiedJson();

        System.out.println("-------------------------");

        System.out.println("real:");

        System.out.println(json);

        System.out.println("pretty:");

        System.out.println(reJson);
    }
}
