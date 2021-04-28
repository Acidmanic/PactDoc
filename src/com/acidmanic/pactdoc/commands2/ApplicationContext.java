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
package com.acidmanic.pactdoc.commands2;

import com.acidmanic.commandline.commands.context.ExecutionContext;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author diego
 */
public class ApplicationContext implements ExecutionContext {

    private boolean successful;
    private final List<String> failureMessages;

    public ApplicationContext() {
        this.successful = true;
        this.failureMessages = new ArrayList<>();
    }

    public boolean isSuccessful() {
        return successful;
    }

    public void fail() {
        this.successful = false;
    }

    public void fail(String message) {
        this.successful = false;
        this.failureMessages.add(message);
    }

    public List<String> getFailureMessages() {
        return failureMessages;
    }

    public void setSuccess(boolean success) {
        this.successful = success;
    }
}
