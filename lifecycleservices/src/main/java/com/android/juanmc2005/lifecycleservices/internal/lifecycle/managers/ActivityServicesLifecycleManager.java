package com.android.juanmc2005.lifecycleservices.internal.lifecycle.managers;

import android.support.v7.app.AppCompatActivity;

import com.android.juanmc2005.lifecycleservices.internal.lifecycle.providers.ActivityServiceProvider;


public final class ActivityServicesLifecycleManager extends ServicesLifecycleManager<ActivityServiceProvider> {

    @Override
    public void register(AppCompatActivity activity, String name, ActivityServiceProvider serviceProvider) {
        activity.getApplication().registerActivityLifecycleCallbacks(serviceProvider);
        put(name, serviceProvider);
    }

    @Override
    public void unregister(AppCompatActivity activity, String name) {
        final ActivityServiceProvider provider = get(name);
        if (provider == null) return;
        remove(name);
        activity.getApplication().unregisterActivityLifecycleCallbacks(provider);
    }
}
