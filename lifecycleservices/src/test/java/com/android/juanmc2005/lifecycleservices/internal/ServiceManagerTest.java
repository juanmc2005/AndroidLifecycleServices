package com.android.juanmc2005.lifecycleservices.internal;

import android.app.Application;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.android.juanmc2005.lifecycleservices.ServiceProvider;
import com.android.juanmc2005.lifecycleservices.internal.injection.ComponentInjector;
import com.android.juanmc2005.lifecycleservices.internal.injection.InjectorManager;
import com.android.juanmc2005.lifecycleservices.internal.injection.Namer;
import com.android.juanmc2005.lifecycleservices.internal.lifecycle.managers.ActivityServicesLifecycleManager;
import com.android.juanmc2005.lifecycleservices.internal.lifecycle.managers.FragmentServicesLifecycleManager;
import com.android.juanmc2005.lifecycleservices.internal.lifecycle.providers.ActivityServiceProvider;
import com.android.juanmc2005.lifecycleservices.internal.lifecycle.providers.AppServiceProvider;
import com.android.juanmc2005.lifecycleservices.internal.lifecycle.providers.FragmentServiceProvider;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ServiceManagerTest {

    @Mock private InjectorManager injectorManager;
    @Mock private ActivityServicesLifecycleManager activityServicesLifecycleManager;
    @Mock private FragmentServicesLifecycleManager fragmentServicesLifecycleManager;
    @Mock private Namer namer;
    @Mock private Utils utils;
    @Mock private ComponentInjector componentInjector;

    private ServiceManager serviceManager;

    @Before
    public void setUp() {
        serviceManager = new ServiceManager(
                injectorManager, activityServicesLifecycleManager, fragmentServicesLifecycleManager, namer, utils);
    }

    @Test
    public void initialize_LoadsAppComponentInjector() {
        assertFalse(serviceManager.isInitialized());

        final String appName = "app";
        final Application app = mock(Application.class);
        when(namer.name(app)).thenReturn(appName);

        serviceManager.initialize(app);

        verify(injectorManager).getComponentInjectorWithName(appName);
        assertTrue(serviceManager.isInitialized());
    }

    @Test
    public void initialize_DoesntInitializeTwice() {
        final String appName = "app";
        final Application app = mock(Application.class);
        when(namer.name(app)).thenReturn(appName);

        serviceManager.initialize(app);
        serviceManager.initialize(app);

        verify(injectorManager).getComponentInjectorWithName(appName);
        assertTrue(serviceManager.isInitialized());
    }

    @Test
    public void getServiceProviderForApplication_ReturnsAppServiceProviderSingleton() {
        final String appName = "app";
        final Application app = mock(Application.class);
        when(namer.name(app)).thenReturn(appName);
        when(injectorManager.getComponentInjectorWithName(appName)).thenReturn(componentInjector);

        final ServiceProvider provider1 = serviceManager.getServiceProviderFor(app);
        final ServiceProvider provider2 = serviceManager.getServiceProviderFor(app);

        assertSame(AppServiceProvider.get(componentInjector), provider1);
        assertSame(provider1, provider2);
    }

    @Test
    public void getServiceProviderForActivity_ReturnsNewProvider() {
        final AppCompatActivity activity = new AppCompatActivity();
        final String activityName = "activity";
        when(namer.name(activity)).thenReturn(activityName);
        when(activityServicesLifecycleManager.isRegistered(activityName)).thenReturn(false);
        when(injectorManager.getComponentInjectorWithName(activityName)).thenReturn(componentInjector);

        final ActivityServiceProvider provider = (ActivityServiceProvider)
                serviceManager.getServiceProviderFor(activity);

        assertEquals(componentInjector, provider.getComponentInjector());
        verify(activityServicesLifecycleManager).register(activity, activityName, provider);
    }

    @Test
    public void getServiceProviderForActivity_ReturnsCachedProvider() {
        final AppCompatActivity activity = new AppCompatActivity();
        final String activityName = "activity";
        final ActivityServiceProvider expectedProvider = mock(ActivityServiceProvider.class);
        when(namer.name(activity)).thenReturn(activityName);
        when(activityServicesLifecycleManager.isRegistered(activityName)).thenReturn(true);
        when(activityServicesLifecycleManager.get(activityName)).thenReturn(expectedProvider);

        final ActivityServiceProvider provider = (ActivityServiceProvider)
                serviceManager.getServiceProviderFor(activity);

        assertSame(expectedProvider, provider);
        verify(activityServicesLifecycleManager, never()).register(activity, activityName, provider);
    }

    @Test
    public void getServiceProviderForFragment_ReturnsNewProvider() {
        final Fragment fragment = new Fragment();
        final String fragmentName = "fragment";
        when(namer.name(fragment)).thenReturn(fragmentName);
        when(fragmentServicesLifecycleManager.isRegistered(fragmentName)).thenReturn(false);
        when(injectorManager.getComponentInjectorWithName(fragmentName)).thenReturn(componentInjector);

        final FragmentServiceProvider provider = (FragmentServiceProvider)
                serviceManager.getServiceProviderFor(fragment);

        assertEquals(componentInjector, provider.getComponentInjector());
        verify(fragmentServicesLifecycleManager).register(fragment, fragmentName, provider);
    }

    @Test
    public void getServiceProviderForFragment_ReturnsCachedProvider() {
        final Fragment fragment = new Fragment();
        final String fragmentName = "fragment";
        final FragmentServiceProvider expectedProvider = mock(FragmentServiceProvider.class);
        when(namer.name(fragment)).thenReturn(fragmentName);
        when(fragmentServicesLifecycleManager.isRegistered(fragmentName)).thenReturn(true);
        when(fragmentServicesLifecycleManager.get(fragmentName)).thenReturn(expectedProvider);

        final FragmentServiceProvider provider = (FragmentServiceProvider)
                serviceManager.getServiceProviderFor(fragment);

        assertSame(expectedProvider, provider);
        verify(fragmentServicesLifecycleManager, never()).register(fragment, fragmentName, provider);
    }

    @Test
    public void disposeActivity_RemovesInjectorAndUnregistersFromLifecycle() {
        final AppCompatActivity activity = new AppCompatActivity();
        final String activityName = "activity";
        when(namer.name(activity)).thenReturn(activityName);

        serviceManager.dispose(activity);

        verify(injectorManager).disposeComponentInjectorWithName(activityName);
        verify(activityServicesLifecycleManager).unregister(activity, activityName);
    }

    @Test
    public void disposeFragment_RemovesInjectorAndUnregistersFromLifecycle() {
        final Fragment fragment = new Fragment();
        final String fragmentName = "fragment";
        when(namer.name(fragment)).thenReturn(fragmentName);

        serviceManager.dispose(fragment);

        verify(injectorManager).disposeComponentInjectorWithName(fragmentName);
        verify(fragmentServicesLifecycleManager).unregister(fragment, fragmentName);
    }
}
