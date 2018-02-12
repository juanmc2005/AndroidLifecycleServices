package com.android.juanmc2005.lifecycleservices.internal.injection;

import android.support.annotation.Nullable;

import java.util.HashMap;
import java.util.Map;

public class ComponentInjector {

    private Map<String, Object> services;

    ComponentInjector() {
        this.services = new HashMap<>();
    }

    @Nullable
    @SuppressWarnings("unchecked")
    <S> S getService(String name) {
        return (S) services.get(name);
    }

    <S> void putService(String name, S service) {
        services.put(name, service);
    }

    boolean hasService(String name) {
        return services.containsKey(name);
    }
}
