package com.android.juanmc2005.lifecycleservices.internal.lifecycle.providers;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import com.android.juanmc2005.lifecycleservices.internal.injection.ComponentInjector;
import com.android.juanmc2005.lifecycleservices.LifecycleService;
import com.android.juanmc2005.lifecycleservices.internal.injection.LifecycleServiceImpl;
import com.android.juanmc2005.lifecycleservices.LifecycleServices;
import com.android.juanmc2005.lifecycleservices.ServiceProvider;

public final class ActivityServiceProvider implements Application.ActivityLifecycleCallbacks, ServiceProvider {

    private final ComponentInjector injector;

    public ActivityServiceProvider(ComponentInjector injector) {
        this.injector = injector;
    }

    @Override
    public <S> LifecycleService<S> provide(Class<S> clazz) {
        return new LifecycleServiceImpl<>(injector, clazz);
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
