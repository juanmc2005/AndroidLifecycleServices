package com.android.juanmc2005.lifecycleservices.internals.providers;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.android.juanmc2005.lifecycleservices.LifecycleService;
import com.android.juanmc2005.lifecycleservices.LifecycleServices;
import com.android.juanmc2005.lifecycleservices.ServiceProvider;
import com.android.juanmc2005.lifecycleservices.internals.services.LifecycleServiceImpl;
import com.zhuinden.servicetree.ServiceTree;


public final class FragmentServiceProvider
        extends FragmentManager.FragmentLifecycleCallbacks
        implements ServiceProvider {

    private final ServiceTree.Node node;

    public FragmentServiceProvider(ServiceTree.Node node) {
        this.node = node;
    }

    @Override
    public void onFragmentDestroyed(FragmentManager fm, Fragment f) {
        LifecycleServices.dispose(f);
    }

    @Override
    public <S> LifecycleService<S> provide(Class<S> clazz) {
        return new LifecycleServiceImpl<>(node, clazz);
    }
}
