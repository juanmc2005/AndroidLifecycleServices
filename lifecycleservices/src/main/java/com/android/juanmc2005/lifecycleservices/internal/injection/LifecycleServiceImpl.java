package com.android.juanmc2005.lifecycleservices.internal.injection;

import com.android.juanmc2005.lifecycleservices.LifecycleService;
import com.android.juanmc2005.lifecycleservices.ServiceBuilder;
import com.android.juanmc2005.lifecycleservices.internal.ServiceNotFoundException;

public final class LifecycleServiceImpl<S> implements LifecycleService<S> {

    private final ComponentInjector injector;
    private final String serviceTag;

    public LifecycleServiceImpl(ComponentInjector injector, Class<S> serviceClass) {
        this.injector = injector;
        this.serviceTag = serviceClass.getCanonicalName();
    }

    @Override
    public LifecycleService<S> createIfAbsent(ServiceBuilder<S> builder) {
        if (!isPresent()) injector.putService(serviceTag, builder.build());
        return this;
    }

    @Override
    public boolean isPresent() {
        return injector.hasService(serviceTag);
    }

    @Override
    public S get() {
        final String tag = serviceTag;
        S service = injector.getService(tag);
        if (service == null) throw new ServiceNotFoundException(tag);
        return service;
    }

    @Override
    public S getOrCreate(ServiceBuilder<S> builder) {
        return createIfAbsent(builder).get();
    }
}
