package com.android.juanmc2005.lifecycleservices.internal.lifecycle.providers;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import com.android.juanmc2005.lifecycleservices.internal.injection.ComponentInjector;
import com.android.juanmc2005.lifecycleservices.LifecycleServices;

public class ActivityServiceProvider extends BaseServiceProvider implements Application.ActivityLifecycleCallbacks {

    public ActivityServiceProvider(ComponentInjector componentInjector) {
        super(componentInjector);
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        // Do nothing
    }

    @Override
    public void onActivityStarted(Activity activity) {
        // Do nothing
    }

    @Override
    public void onActivityResumed(Activity activity) {
        // Do nothing
    }

    @Override
    public void onActivityPaused(Activity activity) {
        // Do nothing
    }

    @Override
    public void onActivityStopped(Activity activity) {
        // Do nothing
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
        // Do nothing
    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        if (activity.isFinishing()) LifecycleServices.dispose(activity);
    }
}
