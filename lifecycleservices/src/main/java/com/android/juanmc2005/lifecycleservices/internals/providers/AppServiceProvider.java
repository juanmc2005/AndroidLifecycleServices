package com.android.juanmc2005.lifecycleservices.internals.providers;

import com.android.juanmc2005.lifecycleservices.LifecycleService;
import com.android.juanmc2005.lifecycleservices.ServiceProvider;
import com.android.juanmc2005.lifecycleservices.internals.services.LifecycleServiceImpl;
import com.zhuinden.servicetree.ServiceTree;

/**
 * No need for lifecycle management here
 */
public final class AppServiceProvider implements ServiceProvider {

    private static AppServiceProvider instance;

    public static AppServiceProvider get(ServiceTree tree) {
        if (instance == null) instance = new AppServiceProvider(tree);
        return instance;
    }

    private final ServiceTree.Node root;

    private AppServiceProvider(ServiceTree tree) {
        this.root = tree.getTreeRoot();
    }

    @Override
    public <S> LifecycleService<S> provide(Class<S> clazz) {
        return new LifecycleServiceImpl<>(root, clazz);
    }
}
