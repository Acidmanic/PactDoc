/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.utility.dictionaryreaders;

import com.acidmanic.utilities.Result;
import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author diego
 */
public class PropertyFileDictionaryReader implements DictionaryReader {

    @Override
    public Result<HashMap<String, String>> read(String input) {

        try {

            File file = new File(input);

            if (file.exists()) {

                try {

                    List<String> lines = Files.readAllLines(file.toPath());

                    HashMap<String, String> result = parseProperties(lines);

                    return new Result<>(true, result);

                } catch (Exception e) {
                }
            }

        } catch (Exception e) {
        }

        return Result.INVALIDATE;
    }

    public HashMap<String, String> parseProperties(List<String> lines) {

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
        return result;
    }

    public HashMap<String, String> parseProperties(String[] lines) {

        List<String> linesList = new ArrayList<>();

        for (String line : lines) {

            linesList.add(line);
        }

        return parseProperties(linesList);
    }

    public HashMap<String, String> parseProperties(String content) {

        String[] lines = content.split("\\n");

        return parseProperties(lines);

    }

}
