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
package com.acidmanic.pactdoc.dcoumentstructure.renderers.expressions;

import com.acidmanic.document.structure.Key;
import com.acidmanic.pactdoc.dcoumentstructure.renderers.PageContext;

/**
 *
 * @author diego
 */
public class NavigationExpression implements PageExpression {

    public interface Translator {

        String translate(Key key);
    }

    private final Key currentKey;
    private final Translator keyTranslator;

    public NavigationExpression(Key currentKey, Translator keyTranslator) {
        this.currentKey = currentKey;
        this.keyTranslator = keyTranslator;
    }

    @Override
    public PageContext render(PageContext context) {

        context.newLine();

        for (int i = 0; i < currentKey.segmentsCount() - 1; i++) {

            Key k = currentKey.subKey(0, i);

            String text = currentKey.segment(i);

            String reference = this.keyTranslator.translate(k);

            context.openLink(reference).append(text).closeLink()
                    .append(" - ").closeLink();
        }

        if (currentKey.segmentsCount() > 0) {

            String text = currentKey.segment(currentKey.segmentsCount() - 1);

            context.openBold().append(text).closeBold();
        }

        context.newLine();

        return context;
    }

}
