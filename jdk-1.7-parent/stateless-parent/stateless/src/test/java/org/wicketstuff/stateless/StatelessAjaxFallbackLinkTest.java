/**
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.wicketstuff.stateless;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.apache.wicket.Page;
import org.apache.wicket.ajax.AjaxEventBehavior;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author jfk
 * 
 */
public class StatelessAjaxFallbackLinkTest {
    protected WicketTester tester;

    @Before
    public void setUp() {
        tester = new WicketTester(new WebApplication() {
            @Override
            public Class<? extends Page> getHomePage() {
                return HomePage.class;
            }
        });

    }

    @After
    public void teardown() {
        final boolean dump = Boolean.getBoolean("dumpHtml");

        if (dump) {
            tester.dumpPage();
        }
    }

    /**
     * Test method for {@link org.wicketstuff.stateless.StatelessAjaxFallbackLink#getStatelessHint()}.
     */
    @SuppressWarnings("unchecked")
    @Test
    public void testGetStatelessHint() {
        tester.startPage(HomePage.class);

        final HomePage page = (HomePage) tester.getLastRenderedPage();
        final StatelessAjaxFallbackLink<Object> l1 = (StatelessAjaxFallbackLink<Object>) page
                .get(2);

        assertTrue(l1.isStateless());

        l1.onClick();

        final List<? extends Behavior> behaviors = l1.getBehaviors();
        final AjaxEventBehavior behavior = (AjaxEventBehavior) behaviors.get(0);

        behavior.onRequest();
    }

    /**
     * Test method for {@link StatelessAjaxFallbackLink#getStatelessHint()}.
     */
    @Test
    public void testHomePage() {
        // start and render the test page
        tester.startPage(HomePage.class);

        // assert rendered page class
        tester.assertRenderedPage(HomePage.class);

        tester.clickLink("more");
    }
}