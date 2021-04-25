/*
 * The MIT License
 *
 * Copyright 2021 diego.
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
package com.acidmanic.pactdoc.dcoumentstructure.renderers;

import com.acidmanic.pactdoc.dcoumentstructure.PageStore;
import java.util.HashMap;

/**
 *
 * 
 * @author diego
 * @param <Toutput> type of representing output data
 */
public interface PageContext<Toutput> {

    PageContext title(String text);

    PageContext subTitle(String text);

    PageContext paragraph(String text);

    PageContext table(HashMap<String, String> table);

    PageContext table(String leftHeader, String rightHeader,
            HashMap<String, String> table);

    PageContext openBold();

    PageContext closeBold();

    PageContext openItalic();

    PageContext closeItalic();

    PageContext append(String text);

    PageContext newLine();

    PageContext openLink(String reference);

    PageContext closeLink();

    PageContext json(String json);

    Toutput output();

    PageContext horizontalLine();

    PageContext badge(String text);

}