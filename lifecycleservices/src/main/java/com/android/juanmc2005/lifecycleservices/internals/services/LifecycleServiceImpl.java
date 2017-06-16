package com.android.juanmc2005.lifecycleservices.internals.services;

import android.support.annotation.Nullable;

import com.android.juanmc2005.lifecycleservices.LifecycleService;
import com.android.juanmc2005.lifecycleservices.ServiceBuilder;
import com.zhuinden.servicetree.ServiceTree;


public final class LifecycleServiceImpl<S> implements LifecycleService<S> {

    private final ServiceTree.Node treeNode;
    private final String serviceTag;

    public LifecycleServiceImpl(ServiceTree.Node treeNode, Class<S> serviceClass) {
        this.treeNode = treeNode;
        this.serviceTag = serviceClass.getCanonicalName();
    }

    @Override
    public LifecycleService<S> createIfAbsent(ServiceBuilder<S> builder) {
        if (!isPresent()) treeNode.bindService(serviceTag, builder.build());
        return this;
    }

    @Override
    public boolean isPresent() {
        return treeNode.hasBoundService(serviceTag);
    }

    @Override
    public S get() {
        final String tag = serviceTag;
        S service = getOrNull(tag);
        if (service == null) throw new ServiceNotFoundException(tag);
        return service;
    }

    @Override
    public S getOrCreate(ServiceBuilder<S> builder) {
        return createIfAbsent(builder).get();
    }

    @Nullable
    private S getOrNull(String tag) {
        if (isPresent()) return treeNode.getService(tag);
        else return null;
    }
}
