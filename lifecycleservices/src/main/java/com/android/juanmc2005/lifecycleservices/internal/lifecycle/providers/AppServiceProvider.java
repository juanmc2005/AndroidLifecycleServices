package com.android.juanmc2005.lifecycleservices.internal.lifecycle.providers;

import com.android.juanmc2005.lifecycleservices.internal.injection.ComponentInjector;
import com.android.juanmc2005.lifecycleservices.LifecycleService;
import com.android.juanmc2005.lifecycleservices.internal.injection.LifecycleServiceImpl;
import com.android.juanmc2005.lifecycleservices.ServiceProvider;

/**
 * No need for lifecycle management here
 */
public final class AppServiceProvider extends BaseServiceProvider {

    private static AppServiceProvider instance;

    public static AppServiceProvider get(ComponentInjector injector) {
        if (instance == null) instance = new AppServiceProvider(injector);
        return instance;
    }

    private AppServiceProvider(ComponentInjector componentInjector) {
        super(componentInjector);
    }
}
