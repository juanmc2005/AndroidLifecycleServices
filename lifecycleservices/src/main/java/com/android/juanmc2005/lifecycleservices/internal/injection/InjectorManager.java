package com.android.juanmc2005.lifecycleservices.internal.injection;

import android.util.Log;

import java.util.HashMap;
import java.util.Map;

public class InjectorManager {

    private static final String TAG = InjectorManager.class.getSimpleName();

    private Map<String, ComponentInjector> componentInjectors;

    public InjectorManager() {
        componentInjectors = new HashMap<>();
    }

    public ComponentInjector getComponentInjectorWithName(String name) {
        ComponentInjector injector = componentInjectors.get(name);
        if (injector == null) {
            putNewComponentInjectorWithName(name);
        }
        return injector;
    }

    public void disposeComponentInjectorWithName(String tag) {
        if (componentInjectors.containsKey(tag)) {
            componentInjectors.remove(tag);
        } else {
            Log.d(TAG, tag + "'s services are already disposed");
        }
    }

    private void putNewComponentInjectorWithName(String componentName) {
        componentInjectors.put(componentName, new ComponentInjector());
    }
}
