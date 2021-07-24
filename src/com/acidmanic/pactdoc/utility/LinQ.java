/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.utility;

import com.acidmanic.delegates.arg1.Function;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author diego
 */
public class LinQ {

    public static <TObject, TProperty> List<TProperty> select(Collection<TObject> collection, Function<TProperty, TObject> selector) {

        List<TProperty> result = new ArrayList<>();

        for (TObject item : collection) {

            TProperty value = selector.perform(item);

            result.add(value);
        }

        return result;
    }

    public static <T> T first(Collection<T> collection, Function<Boolean, T> expression) {

        for (T item : collection) {

            if (expression.perform(item)) {

                return item;
            }
        }
        return null;
    }
}
