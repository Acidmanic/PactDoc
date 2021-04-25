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
package com.acidmanic.pactdoc.dcoumentstructure.pagestores;

import com.acidmanic.document.structure.Key;
import com.acidmanic.io.file.FileIOHelper;
import com.acidmanic.pactdoc.dcoumentstructure.PageStore;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author diego
 */
public class FilesystemPageStore implements PageStore<String> {

    private final String fileExtension;
    private final Path pagesDirectory;
    private final boolean flatOrder;
    private final String flatOrderSegmentDelimiter;
    private final boolean relativizeLinks;
    private final boolean extensionsAppearInLinks;
    private final String rootFilename;

    public FilesystemPageStore() {

        this.fileExtension = "";
        this.pagesDirectory = new File(".").toPath().toAbsolutePath().normalize();
        this.flatOrder = false;
        this.flatOrderSegmentDelimiter = "-";
        this.relativizeLinks = true;
        this.extensionsAppearInLinks = true;
        this.rootFilename = "Index";
    }

    public FilesystemPageStore(String fileExtension, Path pagesDirectory, boolean flatOrder, String flatOrderSegmentDelimiter, boolean relativizeLinks, boolean extensionsAppearInLinks, String rootFilename) {
        this.fileExtension = fileExtension;
        this.pagesDirectory = pagesDirectory;
        this.flatOrder = flatOrder;
        this.flatOrderSegmentDelimiter = flatOrderSegmentDelimiter;
        this.relativizeLinks = relativizeLinks;
        this.extensionsAppearInLinks = extensionsAppearInLinks;
        this.rootFilename = rootFilename;
    }

    private Path getRootlessExtensionlessPathFor(Key key) {

        key = fileSystemFriendly(key);

        if (this.flatOrder) {

            String fileName = key.jointSegments(this.flatOrderSegmentDelimiter);

            return Paths.get(fileName);

        } else {
            String fileName;
            // if pointing to root
            if (key.segmentsCount() == 0) {

                fileName = this.rootFilename + this.fileExtension;

                return Paths.get(fileName);

            } else {
                int directories = key.segmentsCount() - 1;

                Path path = Paths.get(key.segment(0));

                for (int i = 1; i < directories; i++) {

                    String segmentName = key.segment(i);

                    path = path.resolve(segmentName);
                }

                fileName = key.segment(directories);

                path = path.resolve(fileName);

                return path;
            }
        }
    }

    private File rebaseOnFileSystem(Path path) {

        path = this.pagesDirectory.resolve(path);

        path = path.toAbsolutePath().normalize();

        String filename = path.getFileName().toString() + this.fileExtension;

        Path directory = path.getParent();

        path = directory.resolve(filename);

        return path.toFile();

    }

    private Key getLinkRelativeKey(Key referrer, Key target) {

        Key key;

        if (this.relativizeLinks) {

            if (target.startsWith(referrer)) {

                key = target.subKey(referrer.segmentsCount(), target.segmentsCount());

            } else if (referrer.startsWith(target)) {

                int backwards = referrer.segmentsCount() - target.segmentsCount();

                key = target.subKey(0, 0);

                for (int i = 0; i < backwards; i++) {

                    key.append("..");
                }
            } else {
                key = target;
            }
        } else {

            key = target;
        }
        return key;
    }

    @Override
    public void save(Key key, String pageContent) {

        Path path = getRootlessExtensionlessPathFor(key);

        File pageFile = rebaseOnFileSystem(path);

        File parentDirectory = pageFile.toPath().toAbsolutePath().normalize()
                .getParent().toFile();

        parentDirectory.mkdirs();

        new FileIOHelper().tryWriteAll(pageFile, pageContent);
    }

    private String linkExtention() {
        return this.extensionsAppearInLinks ? this.fileExtension : "";
    }

    @Override
    public String translate(Key referrer, Key target) {

        Key key = getLinkRelativeKey(referrer, target);

        Path path = getRootlessExtensionlessPathFor(key);

        return path.toString() + linkExtention();
    }

    private Key fileSystemFriendly(Key key) {

        Key cleaned = key.subKey(0, 0);

        for (int i = 0; i < key.segmentsCount(); i++) {

            String segment = key.segment(i);

            segment = fileSystemFriendly(segment);

            cleaned.append(segment);
        }
        return cleaned;
    }

    private String fileSystemFriendly(String segment) {
        String[] findsRegEx = {"<", ">", ":", "\"", "/", "\\\\", "\\|", "\\?", "\\*"};
        String[] replaces = {"-", "-", ";", "'", "_", "_", ",", "!", "."};

        segment = trimIllegals(segment, findsRegEx);

        for (int i = 0; i < findsRegEx.length; i++) {
            segment = segment.replaceAll(findsRegEx[i], replaces[i]);
        }
        return segment;
    }

    private String trimIllegals(String segment, String[] findsRegEx) {

        String illegalRegex = "(";
        String sep = "";
        for (String ill : findsRegEx) {
            illegalRegex += sep + ill;
            sep = "|";
        }
        illegalRegex += ")";

        if (segment.length() == 0) {

            return segment;
        }
        String checkEdge = segment.substring(0, 1);

        while (checkEdge.matches(illegalRegex)) {
            segment = segment.substring(1, segment.length());

            if (segment.length() > 0) {
                checkEdge = segment.substring(0, 1);

            } else {
                checkEdge = "";
            }
        }
        if (segment.length() == 0) {

            return segment;
        }
        checkEdge = segment.substring(segment.length() - 1, segment.length());

        while (checkEdge.matches(illegalRegex)) {

            segment = segment.substring(0, segment.length() - 1);

            if (segment.length() > 0) {

                checkEdge = segment.substring(segment.length() - 1, segment.length());
            } else {
                checkEdge = "";
            }
        }
        return segment;
    }

}
