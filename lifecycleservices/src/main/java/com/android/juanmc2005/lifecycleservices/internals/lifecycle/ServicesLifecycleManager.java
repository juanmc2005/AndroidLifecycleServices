package com.android.juanmc2005.lifecycleservices.internals.lifecycle;

import android.support.annotation.Nullable;

import com.android.juanmc2005.lifecycleservices.ServiceProvider;

import java.util.HashMap;
import java.util.Map;


public abstract class ServicesLifecycleManager<SP extends ServiceProvider> {

    private final Map<String, SP> registeredProviders;

    ServicesLifecycleManager() {
        this.registeredProviders = new HashMap<>();
    }

    @Nullable
    public SP get(String tag) {
        final Map<String, SP> providersMap = registeredProviders;
        if (providersMap.containsKey(tag)) {
            return providersMap.get(tag);
        } else {
            return null;
        }
    }

    void put(String tag, SP provider) {
        registeredProviders.put(tag, provider);
    }

    void remove(String tag) {
        registeredProviders.remove(tag);
    }
}
