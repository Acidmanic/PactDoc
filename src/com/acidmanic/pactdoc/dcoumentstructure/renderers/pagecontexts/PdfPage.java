/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.dcoumentstructure.renderers.pagecontexts;

import com.itextpdf.text.Element;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author diego
 */
public class PdfPage {

    private final List<Element> elements;

    public PdfPage() {
        this.elements = new ArrayList<>();
    }

    public List<Element> getElements() {

        ArrayList<Element> copy = new ArrayList<>();

        copy.addAll(this.elements);

        return copy;
    }

    public void add(Element element) {

        this.elements.add(element);
    }

}
