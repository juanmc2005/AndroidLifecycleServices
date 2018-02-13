package com.android.juanmc2005.lifecycleservices.internal;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UtilsTest {

    private Utils utils;

    @Before
    public void setUp() {
        utils = new Utils();
    }

    @Test(expected = IllegalStateException.class)
    public void assertInitialized_ThrowsException() {
        final ServiceManager serviceManager = mock(ServiceManager.class);
        when(serviceManager.isInitialized()).thenReturn(false);

        utils.assertInitialized(serviceManager);
    }

    @Test
    public void assertInitialized_DoesntThrowException() {
        final ServiceManager serviceManager = mock(ServiceManager.class);
        when(serviceManager.isInitialized()).thenReturn(true);

        utils.assertInitialized(serviceManager);
    }

    @Test(expected = IllegalStateException.class)
    public void assertAppCompat_ThrowsException() {
        utils.assertAppCompat(new Activity());
    }

    @Test
    public void assertAppCompat_DoesntThrowException() {
        utils.assertAppCompat(new AppCompatActivity());
    }

    @Test(expected = NullPointerException.class)
    public void assertNotNull_ThrowsException() {
        utils.assertNotNull(null, Object.class);
    }

    @Test
    public void assertNotNull_DoesntThrowException() {
        utils.assertNotNull(new Object(), Object.class);
    }
}
