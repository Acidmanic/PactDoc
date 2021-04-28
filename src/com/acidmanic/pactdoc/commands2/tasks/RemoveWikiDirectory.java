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
import com.acidmanic.io.file.FileSystemHelper;
import com.acidmanic.lightweight.logger.Logger;
import com.acidmanic.pactdoc.commands2.ParametersContext;
import com.acidmanic.pactdoc.utility.PathHelpers;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author diego
 */
public class RemoveWikiDirectory extends TaskBase {

    private final ParametersContext context;

    public RemoveWikiDirectory(ParametersContext context, Logger logger) {
        super(logger);
        this.context = context;
    }

    @Override
    public Function<Boolean> getTask() {

        return () -> {
            try {

                Path here = Paths.get(".").toAbsolutePath().normalize();

                PathHelpers.PathRelation relation = new PathHelpers().relation(here, this.context.getOutputDirectory().toPath());

                if (relation == PathHelpers.PathRelation.Identical
                        || relation == PathHelpers.PathRelation.ChildOf) {

                    getLogger().error("Not a good idea to purge directory you seat on!");
                    getLogger().log("Current directory: " + here.toString());
                    getLogger().log("Clearing directory: " + this.context.getOutputDirectory().getAbsolutePath());
                    return false;
                }

                new FileSystemHelper().clearDirectory(this.context.getOutputDirectory().getAbsolutePath());

                return true;
            } catch (Exception e) {
            }
            return false;
        };
    }

    @Override
    public String getTitleing() {
        return "Removing directory: " + this.context.getOutputDirectory().getAbsolutePath();
    }

}
