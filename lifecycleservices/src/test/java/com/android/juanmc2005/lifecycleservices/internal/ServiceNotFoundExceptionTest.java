package com.android.juanmc2005.lifecycleservices.internal;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class ServiceNotFoundExceptionTest {

    private static final String SERVICE_NAME = "service";

    private ServiceNotFoundException serviceNotFoundException;

    @Before
    public void setUp() {
        serviceNotFoundException = new ServiceNotFoundException(SERVICE_NAME);
    }

    @Test
    public void message_IsCorrect() {
        assertEquals("Could not find service " + SERVICE_NAME, serviceNotFoundException.getMessage());
    }
}
