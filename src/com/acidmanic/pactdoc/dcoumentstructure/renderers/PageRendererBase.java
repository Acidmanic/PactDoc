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

import com.acidmanic.document.structure.Key;
import com.acidmanic.pactdoc.dcoumentstructure.renderers.expressions.NavigationExpression;
import com.acidmanic.pactdoc.mark.Mark;
import com.acidmanic.pactdoc.mark.MarkPosition;
import java.util.List;

/**
 *
 * @author diego
 * @param <T> Type of object which this page will present
 */
public abstract class PageRendererBase<T> extends WikiRendererBase {

    protected final String pageSubtitle;

    public PageRendererBase(String pageSubtitle) {
        this.pageSubtitle = pageSubtitle;
    }

    @Override
    protected void performRender(PactRenderingState state) {

        
        renderMarksByPosition(state,MarkPosition.Top);
        
        state.getPageContext().openTitle()
                .append("Api Documentation")
                .closeTitle().horizontalLine();

        
        renderMetadata(state);

        Key key = state.getKey();

        new NavigationExpression(key, k -> getPageStore().translate(key, k))
                .render(state.getPageContext())
                .horizontalLine()
                .newLine()
                .openSubtitle()
                .append(this.pageSubtitle)
                .closeSubtitle()
                .newLine().newLine();

        renderMarksByPosition(state,MarkPosition.Leading);
        
        renderContent((PactRenderingState<T>) state);

        renderMarksByPosition(state,MarkPosition.Trailing);
        
        state.getPageContext()
                .newLine().newLine().newLine().newLine()
                .horizontalLine();

        renderMarksByPosition(state,MarkPosition.Bottom);
        
    }

    protected void renderMetadata(PactRenderingState state) {
    }

    protected abstract void renderContent(PactRenderingState<T> state);
    
    
    private void renderMarksByPosition(PactRenderingState state,MarkPosition position){
        
        List<Mark> marks = state.getContext().getMarks();
        
        PageContext page = state.getPageContext();
        
        if(marks!=null){
            
            for(Mark mark : marks){
            
                if(!mark.isNullMark()){
                    if(mark.getPosition()==position){
                        
                        renderMark(page,mark);
                    }
                }
            }
        }
        
    }

    private void renderMark(PageContext page, Mark mark) {
        
        if(mark.getPosition()==MarkPosition.Trailing){
            page.newLine();
        }
        switch (mark.getType()) {
            
            case Bold:
                page.openBold().append(mark.getText()).closeBold();
                break;
            case Heading1:
                page.openTitle().append(mark.getText()).closeTitle();
                break;
            case Heading2:
                page.openSubtitle().append(mark.getText()).closeSubtitle();
                break;
            case Text:
                page.append(mark.getText());
                break;
                case Image:
                page.newLine().image(mark.getUrl()).newLine();
                break;
                case Link:
                page.openLink(mark.getUrl()).append(mark.getText()).closeLink();
                break;
        }
        if(mark.getPosition()==MarkPosition.Top){
            page.newLine();
        }
    }
}
