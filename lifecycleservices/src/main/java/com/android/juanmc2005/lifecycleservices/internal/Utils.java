package com.android.juanmc2005.lifecycleservices.internal;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;


public class Utils {

    public void assertInitialized(ServiceManager services) {
        if (!services.isInitialized()) {
            throw new IllegalStateException("LifecycleServices is not initialized");
        }
    }

    public void assertAppCompat(Activity activity) {
        if (!(activity instanceof AppCompatActivity)) {
            throw new IllegalStateException(activity.getClass().getSimpleName() +
                    " must extend AppCompatActivity to bound a service to a fragment lifecycle");
        }
    }

    public <T> void assertNotNull(T object, Class<T> clazz) {
        if (object == null) {
            throw new NullPointerException(clazz.getSimpleName() + " cannot be null");
        }
    }
}
