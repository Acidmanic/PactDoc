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
package com.acidmanic.pactdoc.dcoumentstructure.renderers;

import com.acidmanic.document.structure.DocumentAdapter;
import com.acidmanic.document.structure.Key;
import com.acidmanic.pact.models.Pact;
import com.acidmanic.pactdoc.contractverification.ConventionTitle;
import com.acidmanic.pactdoc.contractverification.ConventionType;
import com.acidmanic.pactdoc.dcoumentstructure.models.ConventionEntry;
import com.acidmanic.pactdoc.wiki.WikiRenderingContext;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author diego
 */
public class ConventionsPageRenderer extends PageRendererBase<ConventionEntry> {

    private static final HashMap<Integer, String> conventionMarks = new HashMap<>();

    static {

        conventionMarks.put(ConventionType.Must.ordinal(), "💥");
        conventionMarks.put(ConventionType.Warnable.ordinal(), "⚠️");
        conventionMarks.put(ConventionType.Info.ordinal(), "ℹ️");
    }

    public ConventionsPageRenderer() {
        super("Conventions");
    }

    @Override
    protected void renderContent(Key key,
            ConventionEntry node,
            Pact root,
            List<Key> childs,
            PageContext pageContext,
            WikiRenderingContext renderingContext,
            DocumentAdapter adapter) {

        pageContext.newLine().openList();

        pageContext.paragraph("Whenever your pact files get verified, "
                + "all contracts will be checked against all following "
                + "conventions. as an api-consumer (client side developer)"
                + ", please consider these conventions to prevent "
                + "incompatibility issues for your code.").newLine().newLine();

        for (ConventionTitle title : node.getConventionTitles()) {

            String emoji = conventionMarks.get(title.getType().ordinal());

            String text = title.getText();

            if (title.getType() == ConventionType.Warnable) {

                while (text.endsWith(".")) {

                    text = text.substring(0, text.length() - 1);
                }
                text += ", will cause warnings to be thrown.";
            }

            pageContext.openListItem()
                    .append(" ").append(emoji).append(" ")
                    .append(text)
                    .closeListItem();

        }

        pageContext.newLine().newLine().newLine().closeList();
    }

    @Override
    public Class renderingType() {
        return ConventionEntry.class;
    }

}
