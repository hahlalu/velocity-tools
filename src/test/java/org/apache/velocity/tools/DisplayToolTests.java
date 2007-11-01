package org.apache.velocity.tools.generic;

/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

import org.junit.*;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.velocity.tools.generic.ValueParser;

/**
 * <p>Tests for DisplayTool</p>
 *
 * @author Nathan Bubna
 * @since VelocityTools 2.0
 * @version $Id$
 */
public class DisplayToolTests {

    public @Test void methodAlt_Object() throws Exception
    {
        DisplayTool display = new DisplayTool();
        assertSame(display.alt(null), display.getDefaultAlternate());
        Object notnull = new Object();
        assertSame(display.alt(notnull), notnull);
    }

    public @Test void methodAlt_ObjectObject() throws Exception
    {
        DisplayTool display = new DisplayTool();
        Object notnull = new Object();
        Object result = display.alt(null, notnull);
        assertSame(result, notnull);
        result = display.alt(notnull, "foo");
        assertSame(result, notnull);
    }

    public @Test void methodCapitalize_Object() throws Exception
    {
        DisplayTool display = new DisplayTool();
        assertEquals("FOO", display.capitalize("fOO"));
        assertEquals("Foo", display.capitalize("Foo"));
        assertEquals("Foo", display.capitalize("foo"));
        assertEquals("F", display.capitalize("f"));
        assertEquals("", display.capitalize(""));
        assertEquals(null, display.capitalize(null));
    }

    public @Test void methodCell_Object() throws Exception
    {
        DisplayTool display = new DisplayTool();
        display.setCellLength(4);
        assertEquals(null, display.cell(null));
        assertEquals("foo ", display.cell("foo"));
        assertEquals("f...", display.cell("foobar"));
        assertEquals("foob", display.cell("foob"));
    }

    public @Test void methodCell_ObjectString() throws Exception
    {
        DisplayTool display = new DisplayTool();
        display.setCellLength(5);
        assertEquals("test>", display.cell("testing", ">"));
    }

    public @Test void methodCell_Objectint() throws Exception
    {
        DisplayTool display = new DisplayTool();
        assertEquals("testing", display.cell("testing", 7));
        assertEquals("testing ", display.cell("testing", 8));
        assertEquals("tes...", display.cell("testing", 6));
    }

    public @Test void methodCell_ObjectintString() throws Exception
    {
        DisplayTool display = new DisplayTool();
        assertEquals("f", display.cell("foo", 1, null));
        assertEquals("f", display.cell("foo", 1, "bar"));
        assertEquals("fbar", display.cell("foobar", 4, "bar"));
        assertEquals(null, display.cell("foo", 0, "bar"));
        assertEquals(null, display.cell("foo", -1, "bar"));
    }

    public @Test void methodConfigure_Map() throws Exception
    {
        DisplayTool display = new DisplayTool();
        // change the inspected type to Map
        Map<String,Object> conf = new HashMap<String,Object>();
        conf.put(DisplayTool.LIST_DELIM_KEY, ";");
        conf.put(DisplayTool.LIST_FINAL_DELIM_KEY, " und ");
        conf.put(DisplayTool.TRUNCATE_LENGTH_KEY, "5");
        conf.put(DisplayTool.TRUNCATE_SUFFIX_KEY, ">");
        conf.put(DisplayTool.CELL_LENGTH_KEY, "4");
        conf.put(DisplayTool.CELL_SUFFIX_KEY, "~");
        conf.put(DisplayTool.DEFAULT_ALTERNATE_KEY, "n/a");
        display.configure(conf);
        assertEquals(";", display.getListDelimiter());
        assertEquals(" und ", display.getListFinalDelimiter());
        assertEquals(5, display.getTruncateLength());
        assertEquals(">", display.getTruncateSuffix());
        assertEquals("~", display.getCellSuffix());
        assertEquals(4, display.getCellLength());
        assertEquals("n/a", display.getDefaultAlternate());

        // ensure that configure is locked now
        conf.put(DisplayTool.LIST_DELIM_KEY, " & ");
        display.configure(conf);
        assertEquals(";", display.getListDelimiter());
    }

    public @Test void methodList_Object() throws Exception
    {
        DisplayTool display = new DisplayTool();
        int[] nums = new int[] { 1, 2, 3 };
        assertEquals("1, 2 and 3", display.list(nums));
        display.setListDelimiter(" & ");
        assertEquals("1 & 2 and 3", display.list(nums));
        display.setListFinalDelimiter(" & ");
        assertEquals("1 & 2 & 3", display.list(nums));
    }

    public @Test void methodList_ObjectString() throws Exception
    {
        DisplayTool display = new DisplayTool();
        List<Integer> nums = new ArrayList<Integer>();
        nums.add(1);
        nums.add(2);
        nums.add(3);
        assertEquals(null, display.list(null, null));
        assertEquals("1null2null3", display.list(nums, null));
        assertEquals("1, 2, 3", display.list(nums, ", "));
    }

    public @Test void methodList_ObjectStringString() throws Exception
    {
        DisplayTool display = new DisplayTool();
        int[] nums = new int[] { 1, 2, 3 };
        assertEquals(null, display.list(null, null, null));
        assertEquals("1null2null3", display.list(nums, null, null));
        assertEquals("1 & 2null3", display.list(nums, " & ", null));
        assertEquals("1null2 & 3", display.list(nums, null, " & "));
        assertEquals("123", display.list(nums, "", ""));
        assertEquals("1; 2 und 3", display.list(nums, "; ", " und "));
    }

    public @Test void methodSetCellLength_int() throws Exception
    {
        DisplayTool display = new DisplayTool();
        display.setCellLength(10);
        assertEquals(10, display.getCellLength());
    }

    public @Test void methodSetCellSuffix_String() throws Exception
    {
        DisplayTool display = new DisplayTool();
        display.setCellSuffix("foo");
        assertEquals("foo", display.getCellSuffix());
    }

    public @Test void methodSetDefaultAlternate_String() throws Exception
    {
        DisplayTool display = new DisplayTool();
        display.setDefaultAlternate("foo");
        assertEquals("foo", display.getDefaultAlternate());
    }

    public @Test void methodSetListDelimiter_String() throws Exception
    {
        DisplayTool display = new DisplayTool();
        display.setListDelimiter("foo");
        assertEquals("foo", display.getListDelimiter());
    }

    public @Test void methodSetListFinalDelimiter_String() throws Exception
    {
        DisplayTool display = new DisplayTool();
        display.setListFinalDelimiter("foo");
        assertEquals("foo", display.getListFinalDelimiter());
    }

    public @Test void methodSetTruncateLength_int() throws Exception
    {
        DisplayTool display = new DisplayTool();
        display.setTruncateLength(5);
        assertEquals(5, display.getTruncateLength());
    }

    public @Test void methodSetTruncateSuffix_String() throws Exception
    {
        DisplayTool display = new DisplayTool();
        display.setTruncateSuffix("foo");
        assertEquals("foo", display.getTruncateSuffix());
    }

    public @Test void methodSpace_int() throws Exception
    {
        DisplayTool display = new DisplayTool();
        assertEquals(null, display.space(-1));
        assertEquals("", display.space(0));
        assertEquals(" ", display.space(1));
        assertEquals("     ", display.space(5));
    }

    public @Test void methodTruncate_Object() throws Exception
    {
        DisplayTool display = new DisplayTool();
        display.setTruncateLength(4);
        assertEquals(null, display.truncate(null));
        assertEquals("f...", display.truncate("foobar"));
        assertEquals("foob", display.truncate("foob"));
        assertEquals("foo", display.truncate("foo"));
    }

    public @Test void methodTruncate_ObjectString() throws Exception
    {
        DisplayTool display = new DisplayTool();
        display.setTruncateLength(4);
        assertEquals(null, display.truncate(null, ">"));
        assertEquals("foo>", display.truncate("foobar", ">"));
        assertEquals("foob", display.truncate("foobar", null));
        assertEquals("foob", display.truncate("foobar", "woogie"));
        assertEquals("foo", display.truncate("foo", ">"));
    }

    public @Test void methodTruncate_Objectint() throws Exception
    {
        DisplayTool display = new DisplayTool();
        assertEquals(null, display.truncate(null, 1));
        assertEquals(null, display.truncate("foobar", -1));
        assertEquals(null, display.truncate("foobar", 0));
        assertEquals("f", display.truncate("foobar", 1));
        assertEquals("fo", display.truncate("foobar", 2));
        assertEquals("foo", display.truncate("foobar", 3));
        assertEquals("f...", display.truncate("foobar", 4));
        assertEquals("fo...", display.truncate("foobar", 5));
        assertEquals("foobar", display.truncate("foobar", 6));
        assertEquals("foobar", display.truncate("foobar", 7));
    }

    public @Test void methodTruncate_ObjectintString() throws Exception
    {
        DisplayTool display = new DisplayTool();
        assertEquals(null, display.truncate(null, 0, null));
        assertEquals(null, display.truncate("foo", 0, null));
        assertEquals("f", display.truncate("foo", 1, null));
        assertEquals("foob", display.truncate("foobar", 4, null));
        assertEquals("foo>", display.truncate("foobar", 4, ">"));
    }

    public @Test void methodUncapitalize_Object() throws Exception
    {
        DisplayTool display = new DisplayTool();
        assertEquals(null, display.uncapitalize(null));
        assertEquals("", display.uncapitalize(""));
        assertEquals("test", display.uncapitalize("test"));
        assertEquals("test", display.uncapitalize("Test"));
        assertEquals("tEST", display.uncapitalize("TEST"));
    }


}
        