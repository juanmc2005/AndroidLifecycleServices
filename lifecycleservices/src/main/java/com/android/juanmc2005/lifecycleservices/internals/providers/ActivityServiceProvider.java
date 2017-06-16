package com.android.juanmc2005.lifecycleservices.internals.providers;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import com.android.juanmc2005.lifecycleservices.LifecycleService;
import com.android.juanmc2005.lifecycleservices.LifecycleServices;
import com.android.juanmc2005.lifecycleservices.ServiceProvider;
import com.android.juanmc2005.lifecycleservices.internals.services.LifecycleServiceImpl;
import com.zhuinden.servicetree.ServiceTree;


public final class ActivityServiceProvider implements Application.ActivityLifecycleCallbacks, ServiceProvider {

    private final ServiceTree.Node treeNode;

    public ActivityServiceProvider(ServiceTree.Node treeNode) {
        this.treeNode = treeNode;
    }

    @Override
    public <S> LifecycleService<S> provide(Class<S> clazz) {
        return new LifecycleServiceImpl<>(treeNode, clazz);
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
