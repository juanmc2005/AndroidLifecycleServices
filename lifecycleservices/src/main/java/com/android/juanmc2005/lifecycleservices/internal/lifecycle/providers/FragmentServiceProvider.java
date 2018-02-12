package com.android.juanmc2005.lifecycleservices.internal.lifecycle.providers;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.android.juanmc2005.lifecycleservices.internal.injection.ComponentInjector;
import com.android.juanmc2005.lifecycleservices.LifecycleService;
import com.android.juanmc2005.lifecycleservices.internal.injection.LifecycleServiceImpl;
import com.android.juanmc2005.lifecycleservices.LifecycleServices;
import com.android.juanmc2005.lifecycleservices.ServiceProvider;


public final class FragmentServiceProvider extends FragmentManager.FragmentLifecycleCallbacks implements ServiceProvider {

    private final ComponentInjector injector;

    public FragmentServiceProvider(ComponentInjector injector) {
        this.injector = injector;
    }

    @Override
    public void onFragmentDestroyed(FragmentManager fm, Fragment f) {
        LifecycleServices.dispose(f);
    }

    @Override
    public <S> LifecycleService<S> provide(Class<S> clazz) {
        return new LifecycleServiceImpl<>(injector, clazz);
    }
}
