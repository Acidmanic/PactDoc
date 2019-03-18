/*
 * The MIT License
 *
 * Copyright 2019 Mani Moayedi (acidmanic.moayedi@gmail.com).
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.acidmanic.pactdoc.utility.jsonparsing;

import com.acidmanic.pactdoc.utility.jsonparsing.states.InObjectSybolsState;
import com.acidmanic.pactdoc.utility.jsonparsing.states.InRawValueState;
import com.acidmanic.pactdoc.utility.jsonparsing.states.InStrings;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class JsonParserMachine {
    
    private static final String INSTRING_START="<span class=\"json-in-string\">";
    private static final String INVALUE_START="<span class=\"json-in-value\">";
    private static final String END_TAG="</span>";
    

    public JsonParserMachine() {
    }
    
    public String parse(String json){
        char[] chars = json.toCharArray();
        
        JsonStringState state = new InObjectSybolsState();
        
        StringBuilder sb = new StringBuilder();
        
        for(char c:chars){
            JsonStringState res = state.whatsNext(c);
            if(res.getClass()!=state.getClass()){
                onStateUpdate(c, sb, state, res);
                state = res;
            }else{
                sb.append(c);
            }
        }
        return sb.toString();
    }
    
    private void onStateUpdate(char onChar,StringBuilder sb, JsonStringState previouse, JsonStringState next) {
        if (next.getClass()==InStrings.class){
            sb.append(INSTRING_START);
            sb.append(onChar);
        }else if(previouse.getClass()==InStrings.class){
            sb.append(onChar).append(END_TAG);
        }else if(next.getClass()==InRawValueState.class){
            sb.append(INVALUE_START).append(onChar);
        }else if(previouse.getClass()==InRawValueState.class){
            sb.append(END_TAG).append(onChar);
        }else{
            sb.append(onChar);
        }
    }
}
