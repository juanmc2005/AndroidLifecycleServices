package com.android.juanmc2005.lifecycleservices.internal;

import com.android.juanmc2005.lifecycleservices.internal.injection.InjectorManager;
import com.android.juanmc2005.lifecycleservices.internal.injection.Namer;
import com.android.juanmc2005.lifecycleservices.internal.lifecycle.managers.ActivityServicesLifecycleManager;
import com.android.juanmc2005.lifecycleservices.internal.lifecycle.managers.FragmentServicesLifecycleManager;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.robolectric.annotation.Config.NONE;

@Config(manifest=NONE)
@RunWith(RobolectricTestRunner.class)
public class ServiceManagerTest {

    @Rule public MockitoRule rule = MockitoJUnit.rule();

    @Mock private InjectorManager injectorManager;
    @Mock private ActivityServicesLifecycleManager activityServicesLifecycleManager;
    @Mock private FragmentServicesLifecycleManager fragmentServicesLifecycleManager;
    @Mock private Namer namer;

    private ServiceManager serviceManager;

    @Before
    public void setUp() {
        serviceManager = new ServiceManager(
                injectorManager, activityServicesLifecycleManager, fragmentServicesLifecycleManager, namer);
    }

    @Test
    public void initialize_LoadsAppComponentInjector() {
        assertFalse(serviceManager.isInitialized());

        final String appName = "app";
        when(namer.name(RuntimeEnvironment.application)).thenReturn(appName);

        serviceManager.initialize(RuntimeEnvironment.application);

        verify(injectorManager).getComponentInjectorWithName(appName);
        assertTrue(serviceManager.isInitialized());
    }

    @Test
    public void initialize_DoesntInitializeTwice() {
        final String appName = "app";
        when(namer.name(RuntimeEnvironment.application)).thenReturn(appName);

        serviceManager.initialize(RuntimeEnvironment.application);
        serviceManager.initialize(RuntimeEnvironment.application);

        verify(injectorManager).getComponentInjectorWithName(appName);
        assertTrue(serviceManager.isInitialized());
    }
}
