package com.android.juanmc2005.lifecycleservices.internal.lifecycle.providers;

import com.android.juanmc2005.lifecycleservices.LifecycleService;
import com.android.juanmc2005.lifecycleservices.ServiceProvider;
import com.android.juanmc2005.lifecycleservices.internal.injection.ComponentInjector;
import com.android.juanmc2005.lifecycleservices.internal.injection.LifecycleServiceImpl;

class BaseServiceProvider implements ServiceProvider {

    private final ComponentInjector componentInjector;

    BaseServiceProvider(ComponentInjector componentInjector) {
        this.componentInjector = componentInjector;
    }

    @Override
    public <S> LifecycleService<S> provide(Class<S> clazz) {
        return new LifecycleServiceImpl<>(componentInjector, clazz);
    }

    public ComponentInjector getComponentInjector() {
        return componentInjector;
    }
}
