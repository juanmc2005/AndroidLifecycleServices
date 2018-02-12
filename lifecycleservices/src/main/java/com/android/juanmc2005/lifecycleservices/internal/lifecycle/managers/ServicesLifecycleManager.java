package com.android.juanmc2005.lifecycleservices.internal.lifecycle.managers;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

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
        return registeredProviders.get(tag);
    }

    public boolean isRegistered(String tag) {
        return registeredProviders.containsKey(tag);
    }

    void put(String tag, SP provider) {
        registeredProviders.put(tag, provider);
    }

    void remove(String tag) {
        registeredProviders.remove(tag);
    }
}
