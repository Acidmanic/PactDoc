/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.acidmanic.pactdoc.commands.arguments;

import com.acidmanic.pactdoc.commands.ParametersContext;
import com.acidmanic.pactdoc.mark.Mark;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;

/**
 *
 * @author diego
 */
public class Marks extends PactDocArgumentCommandBase {

    @Override
    protected void execute(ParametersContext context, String[] args) {

        if (args != null && args.length > 0) {

            String input = args[0];

            try {

                File jsonFile = new File(input);

                if (jsonFile.exists()) {

                    ObjectMapper objectMapper = new ObjectMapper();

                    ArrayList<Mark> marks = new ArrayList<>();

                    Mark[] marksRead = objectMapper.readValue(jsonFile, Mark[].class);
                    
                    if(marksRead!=null){
                        
                        marks.addAll(Arrays.asList(marksRead));
                    }

                    context.setMarks(marks);

                    return;
                }

            } catch (Exception e) {

            }

        }

        context.setMarks(new ArrayList<>());
    }

    @Override
    protected String getUsageDescription() {

        return "This argument will take a file name containing json data for "
                + "marks. the file content would be an array of mark objects. "
                + "these marks would be rendered on every wiki page.";
    }

    @Override
    public boolean hasArguments() {

        return true;
    }

}
