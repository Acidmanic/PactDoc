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
package com.acidmanic.pactdoc.commands;

import acidmanic.commandline.commands.CommandBase;
import com.acidmanic.delegates.arg1.Action;
import com.acidmanic.lightweight.logger.ArchiveLogger;
import com.acidmanic.lightweight.logger.LogTypes;
import com.acidmanic.pactdoc.commands.parametervalidation.ValidationResult;
import com.acidmanic.pactdoc.businessmodels.CommandTask;
import com.acidmanic.pactdoc.utility.Func;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public abstract class PactDocCommandBase extends CommandBase {

    private static final String[] UNDELETABLES = {".git",
        ".gitignore",
        ".gitmodules"};

    private final HashMap<String, Action<String>> mappedLogger = new HashMap<>();

    private List<CommandTask> tasks = new ArrayList<>();

    public PactDocCommandBase() {
        mappedLogger.put(LogTypes.ERROR, message -> error(message));
        mappedLogger.put(LogTypes.INFO, message -> info(message));
        mappedLogger.put(LogTypes.LOG, message -> log(message));
        mappedLogger.put(LogTypes.WARNING, message -> warning(message));
    }

    protected void log(ValidationResult<? extends Object> result) {
        result.getInfos().forEach((String v) -> info(v));
        result.getWarnings().forEach((String v) -> warning(v));
        result.getErrors().forEach((String v) -> error(v));
    }

    protected void log(ArchiveLogger logger) {
        logger.getRecords().forEach(li
                -> mappedLogger.get(li.getLogType()).perform(li.getMessage())
        );
    }

    protected void addTask(String titleing, Func<Boolean> task) {
        this.tasks.add(new CommandTask(task, titleing));
    }

    protected void performTasks() {
        for (CommandTask task : this.tasks) {
            if (task.getTask().perform()) {
                info(task.getTitleing() + " : OK");
            } else {
                error("There was a problem " + task.getTitleing());
                return;
            }
        }
    }

    protected void addRemoveDirectoryTask(String directory) {
        addRemoveDirectoryTask(directory, false);
    }

    protected void addRemoveDirectoryTask(String directory, boolean keepGitFiles) {

        String title = "Removing directory: " + directory;

        Func<Boolean> action = () -> {
            File f = new File(directory);

            if (f.exists() == false) {
                return true;
            }

            if (!f.isDirectory()) {
                error(directory + ", is not a directory!.");
                return false;
            }

            Path current = Paths.get(".").toAbsolutePath().normalize();

            if (current.startsWith(f.toPath().toAbsolutePath().normalize())) {
                error("I Cant Remove Where I've set under!!");
                return false;
            }
            if (keepGitFiles) {
                File[] kids = f.listFiles();
                for (File kid : kids) {
                    if (isDeletable(kid)) {
                        try {
                            delete(kid);
                        } catch (Exception ex) {
                            error("Problem deleting: " + directory + "\n"
                                    + "Error: " + ex.getClass().getSimpleName());
                            return false;
                        }
                    }
                }
            } else {
                try {
                    delete(f);
                } catch (Exception ex) {
                    error("Problem deleting: " + directory + "\n"
                            + "Error: " + ex.getClass().getSimpleName());

                    return false;
                }
            }

            return true;
        };

        addTask(title, action);

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

    private void delete(File f) throws Exception {
        if (f.isDirectory()) {
            File[] files = f.listFiles();
            for (File child : files) {
                delete(child);
            }
        }
        f.delete();
    }
}
