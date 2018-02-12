package com.android.juanmc2005.lifecycleservices.internal.lifecycle.providers;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.android.juanmc2005.lifecycleservices.internal.injection.ComponentInjector;
import com.android.juanmc2005.lifecycleservices.LifecycleService;
import com.android.juanmc2005.lifecycleservices.LifecycleServices;
import com.android.juanmc2005.lifecycleservices.ServiceProvider;

public class FragmentServiceProvider extends FragmentManager.FragmentLifecycleCallbacks implements ServiceProvider {

    private final BaseServiceProvider baseServiceProvider;

    public FragmentServiceProvider(ComponentInjector componentInjector) {
        this.baseServiceProvider = new BaseServiceProvider(componentInjector);
    }

    @Override
    public void onFragmentDestroyed(FragmentManager fm, Fragment f) {
        LifecycleServices.dispose(f);
    }

    @Override
    public <S> LifecycleService<S> provide(Class<S> clazz) {
        return baseServiceProvider.provide(clazz);
    }

    public ComponentInjector getComponentInjector() {
        return baseServiceProvider.getComponentInjector();
    }
}
