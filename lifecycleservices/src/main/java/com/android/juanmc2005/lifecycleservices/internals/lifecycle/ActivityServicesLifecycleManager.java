package com.android.juanmc2005.lifecycleservices.internals.lifecycle;

import android.app.Application;

import com.android.juanmc2005.lifecycleservices.internals.providers.ActivityServiceProvider;


public final class ActivityServicesLifecycleManager extends ServicesLifecycleManager<ActivityServiceProvider> {

    public void register(Application app, String tag, ActivityServiceProvider provider) {
        app.registerActivityLifecycleCallbacks(provider);
        put(tag, provider);
    }

    public void unregister(Application app, String tag) {
        final ActivityServiceProvider provider = get(tag);
        if (provider != null) {
            remove(tag);
            app.unregisterActivityLifecycleCallbacks(provider);
        }
    }
}
