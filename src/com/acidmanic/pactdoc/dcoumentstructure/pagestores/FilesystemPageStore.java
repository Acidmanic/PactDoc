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
import com.acidmanic.io.file.FileSystemHelper;
import com.acidmanic.pactdoc.dcoumentstructure.PageStore;
import com.acidmanic.pactdoc.utility.MetaSafeDirectoryCleaner;
import com.acidmanic.pactdoc.utility.PathHelpers;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author diego
 */
public class FilesystemPageStore implements PageStore<String> {

    private final String fileExtension;
    private final Path outputDirectory;
    private final boolean flatOrder;
    private final String flatOrderSegmentDelimiter;
    private final boolean relativizeLinks;
    private final boolean extensionsAppearInLinks;
    private final String rootFilename;
    private final Path subDirectory;

    public FilesystemPageStore() {

        this.fileExtension = "";
        this.outputDirectory = new File(".").toPath().toAbsolutePath().normalize();
        this.flatOrder = false;
        this.flatOrderSegmentDelimiter = "-";
        this.relativizeLinks = true;
        this.extensionsAppearInLinks = true;
        this.rootFilename = "Index";
        this.subDirectory = Paths.get("");
    }

    public FilesystemPageStore(String fileExtension, Path outputDirectory, boolean flatOrder, String flatOrderSegmentDelimiter, boolean relativizeLinks, boolean extensionsAppearInLinks, String rootFilename, Path subDirectory) {
        this.fileExtension = fileExtension;
        this.outputDirectory = outputDirectory;
        this.flatOrder = flatOrder;
        this.flatOrderSegmentDelimiter = flatOrderSegmentDelimiter;
        this.relativizeLinks = relativizeLinks;
        this.extensionsAppearInLinks = extensionsAppearInLinks;
        this.rootFilename = rootFilename;
        this.subDirectory = subDirectory;
    }

    private Path getFileAddressFor(Key key, String extension) {

        if (key.pointsToRoot()) {

            return this.subDirectory.resolve(this.rootFilename + extension);
        }
        key = fileSystemFriendly(key);

        if (this.flatOrder) {

            String fileName = key.jointSegments(this.flatOrderSegmentDelimiter)
                    + extension;

            return this.subDirectory.resolve(fileName);
        }

        Path path = this.subDirectory;

        for (int i = 0; i < key.segmentsCount() - 1; i++) {

            String segmentValue = key.segment(i);

            path = path.resolve(segmentValue);
        }
        path = path.resolve(key.leafValue() + extension);

        return path;
    }

    @Override
    public void save(Key key, String pageContent) {

        Path pagePath = getFileAddressFor(key, this.fileExtension);

        File pageFile = this.outputDirectory.resolve(pagePath).toFile();

        File parentDirectory = pageFile.toPath().toAbsolutePath().normalize()
                .getParent().toFile();

        parentDirectory.mkdirs();

        new FileIOHelper().tryWriteAll(pageFile, pageContent);
    }

    @Override
    public String translate(Key referrer, Key target) {

        String extention = this.extensionsAppearInLinks ? this.fileExtension : "";

        Path tarPath = getFileAddressFor(target, extention);

        if (!this.relativizeLinks) {

            return tarPath.toString();
        }
        Path refPath = getFileAddressFor(referrer, extention);

        PathHelpers.PathRelation relation = new PathHelpers().relation(tarPath, refPath);

        if (relation == PathHelpers.PathRelation.Sibling || relation == PathHelpers.PathRelation.Identical) {

            return tarPath.getFileName().toString();
        }
        if (relation == PathHelpers.PathRelation.ChildOf || relation == PathHelpers.PathRelation.ParentOf) {

            return refPath.relativize(tarPath).toString();
        }
        // if diverged or not related
        // This might not work properly
        return "/" + tarPath.toString();
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

    @Override
    public void initialize() {

        new MetaSafeDirectoryCleaner().delete(this.subDirectory);
    }

}
