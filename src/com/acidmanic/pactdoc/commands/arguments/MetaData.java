/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.commands.arguments;

import com.acidmanic.pactdoc.commands.ParametersContext;
import com.acidmanic.pactdoc.utility.InputDictionaryReader;
import java.util.HashMap;

/**
 *
 * @author diego
 */
public class MetaData extends PactDocArgumentCommandBase {

    @Override
    protected void execute(ParametersContext context, String[] args) {

        if (args != null && args[0] != null && args[0].length() > 0) {

            HashMap<String, String> metadata = new InputDictionaryReader().read(args[0]);
            
            if (!metadata.isEmpty()) {

                context.setWikiMetaData(metadata);
            }
        }
    }

    @Override
    protected String getUsageDescription() {
        return "This argument will take a dictionary of key-values. "
                + "This argument can be followed with the file name of a json "
                + "file, a .properties file or a csv file. it also can take "
                + "an inline json string, coma separated 'key: value's, or just "
                + "even number of coma separated strings."
                + "This information will be rendered on index"
                + " page of the wiki as a two columns table."
                + " This argument is optional.";
    }

    @Override
    public boolean hasArguments() {
        return true;
    }

}
