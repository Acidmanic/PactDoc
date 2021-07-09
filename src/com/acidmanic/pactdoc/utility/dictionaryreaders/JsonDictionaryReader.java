/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.utility.dictionaryreaders;

import com.acidmanic.utilities.Result;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;

/**
 *
 * @author diego
 */
public class JsonDictionaryReader implements DictionaryReader {

    @Override
    public Result<HashMap<String, String>> read(String input) {

        try {
            ObjectMapper objectMapper = new ObjectMapper();

            HashMap<String, String> metadata = new HashMap<>();

            metadata = objectMapper.readValue(input, metadata.getClass());

            return new Result<>(true, metadata);

        } catch (Exception e) {
        }
        return Result.INVALIDATE;
    }

}
