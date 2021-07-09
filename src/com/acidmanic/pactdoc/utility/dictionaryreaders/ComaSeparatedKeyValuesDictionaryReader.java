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
public class ComaSeparatedKeyValuesDictionaryReader implements DictionaryReader {

    @Override
    public Result<HashMap<String, String>> read(String input) {

        try {

            String[] lines = input.split(",");

            HashMap<String, String> result = new HashMap<>();

            for (String line : lines) {

                int st = line.indexOf(":");

                if (st > -1) {

                    String key = line.substring(0, st).trim();

                    String value = line.substring(st + 1, line.length()).trim();

                    if (result.containsKey(key)) {

                        result.remove(key);
                    }
                    result.put(key, value);
                }
            }
            return new Result<>(true, result);

        } catch (Exception e) {
        }

        return Result.INVALIDATE;
    }

}
