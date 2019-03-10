/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acidmanic.pactdoc.services.contractindexing;

import com.acidmanic.pactdoc.services.contractindexing.ContentKeyHelper;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Mani Moayedi (acidmanic.moayedi@gmail.com)
 */
public class ContentKeyHelperTest {
    
    public ContentKeyHelperTest() {
    }

    @Test
    public void testAppend() {
    }

    @Test
    public void testBackOnce() {
    }

    @Test
    public void testSubKey() {
    }

    @Test
    public void testGetTitleFor() {
    }

    @Test
    public void testGetPath() {
    }

    @Test
    public void testGetKey() {
    }

    @Test
    public void testRelativizeSamePaths() {
        
        String [] path = {"First","Second"};
        
        String[] actual;
        
        actual = ContentKeyHelper.relateTo(path, path);
        
        String[] expected ={};
        
        Assert.assertArrayEquals(expected, actual);
    }

    @Test
    public void testRelativizeSmallerPathOnLargerPathNonCommon() {
        
        String [] base = {"a","b","c"};
        
        String [] path = {"d","e"};
        
        String[] expected ={"..","..","..","d","e"};
        
        
        String[] actual;
        
        actual = ContentKeyHelper.relateTo(path, base);
        
        Assert.assertArrayEquals(expected, actual);
        
    }
    
    @Test
    public void testRelativizeSameSizeWithCommons() {
        
        String [] base = {"a","b","c"};
        
        String [] path = {"a","b","d"};
        
        String[] expected ={"..","d"};
        
        
        String[] actual;
        
        actual = ContentKeyHelper.relateTo(path, base);
        
        Assert.assertArrayEquals(expected, actual);
        
    }
    
    @Test
    public void testRelativizeLongerOnSmallerNoCommons() {
        
        String [] base = {"a","b"};
        
        String [] path = {"c","d","e"};
        
        String[] expected ={"..","..","c","d","e"};
        
        
        String[] actual;
        
        actual = ContentKeyHelper.relateTo(path, base);
        
        Assert.assertArrayEquals(expected, actual);
        
    }
    
    @Test
    public void testRelativizeLongerOnSmallerWithCommons() {
        
        String [] base = {"a","b"};
        
        String [] path = {"a","c","d"};
        
        String[] expected ={"..","c","d"};
        
        
        String[] actual;
        
        actual = ContentKeyHelper.relateTo(path, base);
        
        Assert.assertArrayEquals(expected, actual);
        
    }
    
    
    @Test
    public void testRelativizeIncluding() {
        
        String [] base = {"a","b"};
        
        String [] path = {"a","b","c"};
        
        String[] expected ={"c"};
        
        
        String[] actual;
        
        actual = ContentKeyHelper.relateTo(path, base);
        
        Assert.assertArrayEquals(expected, actual);
        
    }
    
}
