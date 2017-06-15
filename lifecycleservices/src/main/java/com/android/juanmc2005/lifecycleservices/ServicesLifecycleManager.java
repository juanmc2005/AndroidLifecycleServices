package com.android.juanmc2005.lifecycleservices;

import android.support.annotation.Nullable;

import java.util.HashMap;
import java.util.Map;


abstract class ServicesLifecycleManager<SP extends ServiceProvider> {

    private final Map<String, SP> registeredProviders;

    ServicesLifecycleManager() {
        this.registeredProviders = new HashMap<>();
    }

    @Nullable
    SP get(String tag) {
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
