package com.android.juanmc2005.lifecycleservices.internal;

import android.app.Application;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.android.juanmc2005.lifecycleservices.ServiceProvider;
import com.android.juanmc2005.lifecycleservices.internal.injection.InjectorManager;
import com.android.juanmc2005.lifecycleservices.internal.injection.Namer;
import com.android.juanmc2005.lifecycleservices.internal.lifecycle.managers.ActivityServicesLifecycleManager;
import com.android.juanmc2005.lifecycleservices.internal.lifecycle.providers.ActivityServiceProvider;
import com.android.juanmc2005.lifecycleservices.internal.lifecycle.managers.FragmentServicesLifecycleManager;
import com.android.juanmc2005.lifecycleservices.internal.lifecycle.providers.AppServiceProvider;
import com.android.juanmc2005.lifecycleservices.internal.lifecycle.providers.FragmentServiceProvider;

import static com.android.juanmc2005.lifecycleservices.internal.Utils.assertAppCompat;

public final class ServiceManager {

    private final InjectorManager injectorManager;
    private final ActivityServicesLifecycleManager activityServicesManager;
    private final FragmentServicesLifecycleManager fragmentServicesManager;
    private final Namer namer;

    private boolean initialized = false;

    public ServiceManager() {
        injectorManager = new InjectorManager();
        activityServicesManager = new ActivityServicesLifecycleManager();
        fragmentServicesManager = new FragmentServicesLifecycleManager();
        namer = new Namer();
    }

    public void initialize(Application app) {
        if (!isInitialized()) {
            injectorManager.getComponentInjectorWithName(namer.name(app));
            initialized = true;
        }
    }

    boolean isInitialized() {
        return initialized;
    }

    public ServiceProvider getServiceProviderFor(AppCompatActivity activity) {
        final String name = namer.name(activity);
        if (activityServicesManager.isRegistered(name)) {
            return activityServicesManager.get(name);
        } else {
            ActivityServiceProvider provider =
                    new ActivityServiceProvider(injectorManager.getComponentInjectorWithName(name));
            activityServicesManager.register(activity, name, provider);
            return provider;
        }
    }

    public ServiceProvider getServiceProviderFor(Fragment fragment) {
        assertAppCompat(fragment.getActivity());
        final String name = namer.name(fragment);
        if (fragmentServicesManager.isRegistered(name)) {
            return fragmentServicesManager.get(name);
        } else {
            FragmentServiceProvider provider =
                    new FragmentServiceProvider(injectorManager.getComponentInjectorWithName(name));
            fragmentServicesManager.register((AppCompatActivity) fragment.getActivity(), name, provider);
            return provider;
        }
    }

    public ServiceProvider getServiceProviderFor(Application app) {
        return AppServiceProvider.get(injectorManager.getComponentInjectorWithName(namer.name(app)));
    }

    public void dispose(AppCompatActivity activity) {
        final String name = namer.name(activity);
        injectorManager.disposeComponentInjectorWithName(name);
        activityServicesManager.unregister(activity, name);
    }

    public void dispose(Fragment fragment) {
        final String name = namer.name(fragment);
        injectorManager.disposeComponentInjectorWithName(name);
        fragmentServicesManager.unregister((AppCompatActivity) fragment.getActivity(), name);
    }
}
