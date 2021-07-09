/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.utility.dictionaryreaders;

import com.acidmanic.utilities.Result;
import java.util.HashMap;

/**
 *
 * @author diego
 */
public class ComaSeparatedStreamDictionaryReader implements DictionaryReader {

    @Override
    public Result<HashMap<String, String>> read(String input) {

        try {

            String[] lines = input.split(",");

            HashMap<String, String> result = new HashMap<>();

            int addingLength = (lines.length / 2) * 2;

            for (int i = 0; i < addingLength; i += 2) {

                String key = lines[i].trim();

                String value = lines[i + 1].trim();

                if (result.containsKey(key)) {

                    result.remove(key);
                }
                result.put(key, value);
            }

            return new Result<>(true, result);

        } catch (Exception e) {
        }

        return Result.INVALIDATE;
    }

}
