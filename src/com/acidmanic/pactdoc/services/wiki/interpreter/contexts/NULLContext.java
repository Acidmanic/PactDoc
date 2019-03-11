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
package com.acidmanic.pactdoc.services.wiki.interpreter.contexts;

import java.util.HashMap;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class NULLContext implements WikiContext{

    NULLContext() {
    }

    @Override
    public WikiContext title(String text) {
        return this;
    }

    @Override
    public WikiContext paragraph(String text) {
        return this;
    }

    @Override
    public WikiContext table(HashMap<String, String> table) {
        return this;
    }

    @Override
    public WikiContext table(String leftHeader, String rightHeader, HashMap<String, String> table) {
        return this;
    }

    @Override
    public WikiContext openBold() {
        return this;
    }

    @Override
    public WikiContext closeBold() {
        return this;
    }

    @Override
    public WikiContext openItalic() {
        return this;
    }

    @Override
    public WikiContext closeItalic() {
        return this;
    }

    @Override
    public WikiContext append(String text) {
        return this;
    }

    @Override
    public WikiContext newLine() {
        return this;
    }

    @Override
    public WikiContext json(String json) {
        return this;
    }

    @Override
    public void output() {
    }

    @Override
    public WikiContext openLink(String[] contentKey) {
        return this;
    }

    @Override
    public WikiContext closeLink() {
        return this;
    }

    @Override
    public WikiContext horizontalLine() {
        return this;
    }

    @Override
    public WikiContext startNewPage(String[] contentKey) {
        return this;
    }
    
}
