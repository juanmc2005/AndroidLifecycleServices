package com.android.juanmc2005.lifecycleservices.internal;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;


public final class Utils {

    public static void assertInitialized(ServiceManager services) {
        if (!services.isInitialized()) {
            throw new IllegalStateException("LifecycleServices is not initialized");
        }
    }

    public static void assertAppCompat(Activity activity) {
        if (!(activity instanceof AppCompatActivity)) {
            throw new IllegalStateException(activity.getClass().getSimpleName() +
                    " must extend AppCompatActivity to bound a service to a fragment lifecycle");
        }
    }
}
