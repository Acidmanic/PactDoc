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
package com.acidmanic.pactdoc.utility;

import java.io.File;
import java.nio.file.Path;

/**
 *
 * Since most metadata files or directories within projects, have names starting
 * with a '.', this class deletes all the directories and files which their
 * names does not start with a dot. this way all git, svn, vscode and etc, files
 * will remain untouched.
 *
 * @author diego
 */
public class MetaSafeDirectoryCleaner {

    public void delete(Path directory) {

        this.delete(directory.toFile());
    }

    public void delete(String directory) {

        this.delete(new File(directory));
    }

    public void delete(File directory) {

        if (directory.exists() && directory.isDirectory()) {

            File[] files = directory.listFiles();

            for (File file : files) {

                if (!file.getName().startsWith(".")) {

                    recursiveDelete(file);
                }
            }
        }
    }

    private void recursiveDelete(File file) {

        if (file.isDirectory()) {

            File[] childs = file.listFiles();

            for (File child : childs) {

                recursiveDelete(child);
            }
        }
        file.delete();
    }
}
