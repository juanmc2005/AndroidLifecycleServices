package com.android.juanmc2005.lifecycleservices.internal.injection;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class ComponentInjectorTest {

    private ComponentInjector componentInjector;

    @Before
    public void setUp() {
        componentInjector = new ComponentInjector();
    }

    @Test
    public void getService_ReturnsNullIfNoServiceFound() {
        final String name = "some random name";
        assertFalse(componentInjector.hasService(name));
        assertNull(componentInjector.getService(name));
    }

    @Test
    public void putService_SavesServiceInternally() {
        final Object service = new Object();
        final String name = "object";

        componentInjector.putService(name, service);

        assertTrue(componentInjector.hasService(name));
        assertSame(service, componentInjector.getService(name));
    }
}
