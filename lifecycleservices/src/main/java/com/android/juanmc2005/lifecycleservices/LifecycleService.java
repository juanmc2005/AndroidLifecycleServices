package com.android.juanmc2005.lifecycleservices;


public interface LifecycleService<S> {
    LifecycleService<S> createIfAbsent(ServiceBuilder<S> builder);
    boolean isPresent();
    S get();
    S getOrCreate(ServiceBuilder<S> builder);
}
