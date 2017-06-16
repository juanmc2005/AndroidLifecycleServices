package com.android.juanmc2005.lifecycleservices;


import com.android.juanmc2005.lifecycleservices.LifecycleService;

public interface ServiceProvider {
    <S> LifecycleService<S> provide(Class<S> clazz);
}
