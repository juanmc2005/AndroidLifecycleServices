package com.android.juanmc2005.lifecycleservices.internal.injection;

import android.support.annotation.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        else if (!(obj instanceof ComponentInjector)) return false;
        ComponentInjector that = (ComponentInjector) obj;
        return Objects.equals(services, that.services);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(services);
    }
}
