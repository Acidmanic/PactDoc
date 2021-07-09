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
public interface DictionaryReader {

    Result<HashMap<String, String>> read(String input);

}
