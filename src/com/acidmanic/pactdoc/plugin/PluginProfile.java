/*
 * The MIT License
 *
 * Copyright 2019 80116.
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
package com.acidmanic.pactdoc.plugin;

import com.acidmanic.pactdoc.PactDoc;
import com.sun.org.apache.bcel.internal.util.ClassPath;
import java.io.File;
import java.io.FileInputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 *
 * @author Mani Moayedi
 */
public class PluginProfile {

    private File pluginsDirectory;
    private List<File> allLibraries;
    private List<Class> allPluggedClasses;
    
    

    public PluginProfile(File pluginsDirectory) {
        this.pluginsDirectory = pluginsDirectory;
    }

    public PluginProfile(String pluginsDirectory) {
        this(new File(pluginsDirectory));
    }

    public final void loadPlugins() {

        loadAllLibraries();

        loadAllClasses();

    }

    private void loadAllClasses() {
        this.allPluggedClasses = new ArrayList<>();

        this.allLibraries.forEach(jar -> loadAllClasses(jar, this.allPluggedClasses));
    }

    private void loadAllLibraries() {

        this.allLibraries = new ArrayList<>();

        loadLibraries(this.pluginsDirectory, this.allLibraries);
    }

    private void loadLibraries(File file, List<File> ret) {

        if (file.isDirectory()) {
            File[] files = file.listFiles();

            for (File f : files) {
                loadLibraries(f, ret);
            }

        } else {
            if (file.getName().toLowerCase().endsWith(".jar")) {
                ret.add(file);
            }
        }
    }

    private void loadAllClasses(File jar, List<Class> allPluggedClasses) {

        List<String> classNames = loadClassNames(jar);

        ClassLoader sysClassloader = ClassLoader.getSystemClassLoader();

        try {
            URLClassLoader loader = new URLClassLoader(
                    new URL[]{this.pluginsDirectory.toURI().toURL()}, sysClassloader);

            for (String className : classNames) {
                try {
                    Class c = loader.loadClass(className);
                } catch (Exception e) {
                }

            }
        } catch (Exception ex) {
        }
    }

    private List<String> loadClassNames(File jar) {
        List<String> classNames = new ArrayList<String>();
        try {
            ZipInputStream zip = new ZipInputStream(new FileInputStream(jar));
            for (ZipEntry entry = zip.getNextEntry(); entry != null; entry = zip.getNextEntry()) {
                if (!entry.isDirectory() && entry.getName().endsWith(".class")) {
                    // This ZipEntry represents a class. Now, what class does it represent?
                    String className = entry.getName().replace('/', '.'); // including ".class"
                    classNames.add(className.substring(0, className.length() - ".class".length()));
                }
            }
        } catch (Exception e) {
        }
        return classNames;
    }

}
