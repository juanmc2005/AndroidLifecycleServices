package com.android.juanmc2005.lifecycleservices;


public interface ServiceProvider {
    <S> LifecycleService<S> provide(Class<S> clazz);
}
