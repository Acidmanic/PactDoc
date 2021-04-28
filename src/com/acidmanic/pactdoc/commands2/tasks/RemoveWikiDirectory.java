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
package com.acidmanic.pactdoc.commands2.tasks;

import com.acidmanic.delegates.Function;
import com.acidmanic.lightweight.logger.Logger;
import com.acidmanic.pactdoc.commands2.ParametersContext;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author diego
 */
public class RemoveWikiDirectory extends TaskBase {

    private final boolean keepGitFiles;
    private final ParametersContext context;

    private static final String[] UNDELETABLES = {".git",
        ".gitignore",
        ".gitmodules"};

    public RemoveWikiDirectory(boolean keepGitFiles, ParametersContext context, Logger logger) {
        super(logger);
        this.keepGitFiles = keepGitFiles;
        this.context = context;
    }

    private boolean isDeletable(File kid) {
        String kidName = kid.getName().toLowerCase();
        for (String name : UNDELETABLES) {
            if (name.compareTo(kidName) == 0) {
                return false;
            }
        }
        return true;
    }

    @Override
    public Function<Boolean> getTask() {

        return () -> {
            File f = this.context.getOutputDirectory();

            if (f.exists() == false) {
                return true;
            }

            if (!f.isDirectory()) {
                getLogger().error(f + ", is not a directory!.");
                return false;
            }

            Path current = Paths.get(".").toAbsolutePath().normalize();

            if (current.startsWith(f.toPath().toAbsolutePath().normalize())) {
                getLogger().error("I Cant Remove Where I've set under!!");
                return false;
            }
            if (keepGitFiles) {
                File[] kids = f.listFiles();
                for (File kid : kids) {
                    if (isDeletable(kid)) {
                        try {
                            delete(kid);
                        } catch (Exception ex) {
                            getLogger().error("Problem deleting: " + f + "\n"
                                    + "Error: " + ex.getClass().getSimpleName());
                            return false;
                        }
                    }
                }
            } else {
                try {
                    delete(f);
                } catch (Exception ex) {
                    getLogger().error("Problem deleting: " + f + "\n"
                            + "Error: " + ex.getClass().getSimpleName());

                    return false;
                }
            }

            return true;
        };
    }

    private void delete(File f) throws Exception {
        if (f.isDirectory()) {
            File[] files = f.listFiles();
            for (File child : files) {
                delete(child);
            }
        }
        f.delete();
    }

    @Override
    public String getTitleing() {
        return "Removing directory: " + this.context.getOutputDirectory().getAbsolutePath();
    }

}
