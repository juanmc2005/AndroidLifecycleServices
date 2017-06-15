package com.android.juanmc2005.lifecycleservices;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.zhuinden.servicetree.ServiceTree;


final class FragmentServiceProvider
        extends FragmentManager.FragmentLifecycleCallbacks
        implements ServiceProvider {

    private final ServiceTree.Node node;

    FragmentServiceProvider(ServiceTree.Node node) {
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
