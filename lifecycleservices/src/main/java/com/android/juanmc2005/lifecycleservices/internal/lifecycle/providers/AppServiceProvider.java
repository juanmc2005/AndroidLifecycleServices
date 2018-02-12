package com.android.juanmc2005.lifecycleservices.internal.lifecycle.providers;

import com.android.juanmc2005.lifecycleservices.internal.injection.ComponentInjector;
import com.android.juanmc2005.lifecycleservices.LifecycleService;
import com.android.juanmc2005.lifecycleservices.internal.injection.LifecycleServiceImpl;
import com.android.juanmc2005.lifecycleservices.ServiceProvider;

/**
 * No need for lifecycle management here
 */
public final class AppServiceProvider implements ServiceProvider {

    private static AppServiceProvider instance;

    public static AppServiceProvider get(ComponentInjector injector) {
        if (instance == null) instance = new AppServiceProvider(injector);
        return instance;
    }

    private final ComponentInjector injector;

    private AppServiceProvider(ComponentInjector injector) {
        this.injector = injector;
    }

    @Override
    public <S> LifecycleService<S> provide(Class<S> clazz) {
        return new LifecycleServiceImpl<>(injector, clazz);
    }
}
