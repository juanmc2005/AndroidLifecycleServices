package com.android.juanmc2005.lifecycleservices;

import android.app.Application;


final class ActivityServicesLifecycleManager extends ServicesLifecycleManager<ActivityServiceProvider> {

    void register(Application app, String tag, ActivityServiceProvider provider) {
        app.registerActivityLifecycleCallbacks(provider);
        put(tag, provider);
    }

    void unregister(Application app, String tag) {
        final ActivityServiceProvider provider = get(tag);
        if (provider != null) {
            remove(tag);
            app.unregisterActivityLifecycleCallbacks(provider);
        }
    }
}
