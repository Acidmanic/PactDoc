/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.dcoumentstructure.renderers;

import com.acidmanic.document.render.RenderingState;
import com.acidmanic.document.structure.DocumentAdapter;
import com.acidmanic.document.structure.Key;
import com.acidmanic.pact.models.Pact;
import com.acidmanic.pactdoc.wiki.WikiRenderingContext;
import java.util.List;

/**
 *
 * @author diego
 */
public class PactRenderingState<TNode> {

    private Key key;
    private TNode node;
    private Pact root;
    private List<Key> children;
    private WikiRenderingContext context;
    private DocumentAdapter adapter;
    private PageContext pageContext;

    public PactRenderingState() {

    }

    public PactRenderingState(Key key, TNode node, Pact root, List<Key> children, WikiRenderingContext context, DocumentAdapter adapter, PageContext pageContext) {
        this.key = key;
        this.node = node;
        this.root = root;
        this.children = children;
        this.context = context;
        this.adapter = adapter;
        this.pageContext = pageContext;
    }

    public static PactRenderingState Build(RenderingState<WikiRenderingContext> state, PageContext pageContext) {

        PactRenderingState pactState = new PactRenderingState();

        pactState.adapter = state.getAdapter();
        pactState.children = state.getChildren();
        pactState.context = state.getContext();
        pactState.key = state.getKey();
        pactState.node = state.getNode();
        pactState.root = (Pact) state.getRoot();
        pactState.pageContext = pageContext;

        return pactState;
    }

    public Key getKey() {
        return key;
    }

    public void setKey(Key key) {
        this.key = key;
    }

    public TNode getNode() {
        return node;
    }
    
    public void setNode(TNode node) {
        this.node = node;
    }

    public Pact getRoot() {
        return root;
    }

    public void setRoot(Pact root) {
        this.root = root;
    }

    public List<Key> getChildren() {
        return children;
    }

    public void setChildren(List<Key> children) {
        this.children = children;
    }

    public WikiRenderingContext getContext() {
        return context;
    }

    public void setContext(WikiRenderingContext context) {
        this.context = context;
    }

    public DocumentAdapter getAdapter() {
        return adapter;
    }

    public void setAdapter(DocumentAdapter adapter) {
        this.adapter = adapter;
    }

    public PageContext getPageContext() {
        return pageContext;
    }

    public void setPageContext(PageContext pageContext) {
        this.pageContext = pageContext;
    }

}
