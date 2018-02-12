package com.android.juanmc2005.lifecycleservices.internal.lifecycle.managers;

import android.support.v7.app.AppCompatActivity;

import com.android.juanmc2005.lifecycleservices.internal.lifecycle.providers.FragmentServiceProvider;


public final class FragmentServicesLifecycleManager extends ServicesLifecycleManager<FragmentServiceProvider> {

    @Override
    public void register(AppCompatActivity activity, String name, FragmentServiceProvider serviceProvider) {
        activity.getSupportFragmentManager().registerFragmentLifecycleCallbacks(serviceProvider, true);
        put(name, serviceProvider);
    }

    @Override
    public void unregister(AppCompatActivity activity, String name) {
        final FragmentServiceProvider provider = get(name);
        if (provider == null) return;
        remove(name);
        activity.getSupportFragmentManager().unregisterFragmentLifecycleCallbacks(provider);
    }
}
