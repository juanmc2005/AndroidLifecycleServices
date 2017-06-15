package com.android.juanmc2005.lifecycleservices;

import com.zhuinden.servicetree.ServiceTree;

/**
 * No need for lifecycle management here
 */
final class AppServiceProvider implements ServiceProvider {

    private static AppServiceProvider instance;

    static AppServiceProvider get(ServiceTree tree) {
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
