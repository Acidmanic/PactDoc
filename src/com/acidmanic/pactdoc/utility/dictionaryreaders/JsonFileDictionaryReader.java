/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.utility.dictionaryreaders;

import com.acidmanic.utilities.Result;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.util.Dictionary;
import java.util.HashMap;

/**
 *
 * @author diego
 */
public class JsonFileDictionaryReader implements DictionaryReader {

    @Override
    public Result<HashMap<String, String>> read(String input) {

        try {

            File jsonFile = new File(input);

            if (jsonFile.exists()) {

                ObjectMapper objectMapper = new ObjectMapper();

                HashMap<String, String> data = new HashMap<>();

                data = objectMapper.readValue(jsonFile, data.getClass());

                return new Result<>(true, data);
            }

        } catch (Exception e) {
        }

        return Result.INVALIDATE;
    }

}
