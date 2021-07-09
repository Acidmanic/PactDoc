/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.utility;

import com.acidmanic.pactdoc.utility.dictionaryreaders.ComaSeparatedKeyValuesDictionaryReader;
import com.acidmanic.pactdoc.utility.dictionaryreaders.ComaSeparatedStreamDictionaryReader;
import com.acidmanic.pactdoc.utility.dictionaryreaders.CsvFileDictionaryReader;
import com.acidmanic.pactdoc.utility.dictionaryreaders.DictionaryReader;
import com.acidmanic.pactdoc.utility.dictionaryreaders.JsonDictionaryReader;
import com.acidmanic.pactdoc.utility.dictionaryreaders.JsonFileDictionaryReader;
import com.acidmanic.pactdoc.utility.dictionaryreaders.PropertyFileDictionaryReader;
import com.acidmanic.utilities.Result;
import java.util.HashMap;

/**
 * This class will load a HashMap of string-string from given input string. If
 * the string is an existing .json/.property/.csv file, it will load the file as
 * a HashMap. otherwise it will try reading it as json, comma separated
 * name-values delimited by a :, comma separated values without new-line.
 *
 * @author diego
 */
public class InputDictionaryReader {

    private final DictionaryReader[] readers = new DictionaryReader[]{
        new ComaSeparatedKeyValuesDictionaryReader(),
        new ComaSeparatedStreamDictionaryReader(),
        new CsvFileDictionaryReader(),
        new JsonDictionaryReader(),
        new JsonFileDictionaryReader(),
        new PropertyFileDictionaryReader()
    };

    public HashMap<String, String> read(String input) {

        for (DictionaryReader reader : this.readers) {

            Result<HashMap<String, String>> readResult = reader.read(input);

            if (readResult.isValid()) {

                return readResult.get();
            }
        }
        return new HashMap<>();
    }
}
