/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.utility;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author diego
 */
public class StringUtils {

    public static List<String> split(String string, String regEx, int limit) {

        return split(string, regEx, limit, false);
    }

    public static List<String> split(String string, String regEx) {

        return split(string, regEx, 0, false);
    }

    public static List<String> split(String string, String regEx, boolean removeEmpties) {

        return split(string, regEx, 0, removeEmpties);
    }

    public static List<String> split(String string, String regEx, int limit, boolean removeEmpties) {

        String[] result = string.split(regEx, limit);

        List<String> splited = new ArrayList<>();

        for (String value : result) {

            if ((!removeEmpties) || (value != null && value.length() > 0)) {

                splited.add(value);
            }
        }
        return splited;
    }
}
