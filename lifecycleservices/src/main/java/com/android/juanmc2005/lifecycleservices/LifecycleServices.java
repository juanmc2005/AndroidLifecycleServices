package com.android.juanmc2005.lifecycleservices;

import android.app.Activity;
import android.app.Application;
import android.support.v4.app.Fragment;

import static com.android.juanmc2005.lifecycleservices.Utils.assertInitialized;


public final class LifecycleServices {

    private static ServiceManager serviceManager = new ServiceManager();

    private LifecycleServices() {
        throw new IllegalStateException("No instances allowed for class " + getClass().getSimpleName());
    }

    private static void install(Application app) {
        serviceManager.initialize(app.getClass().getCanonicalName());
    }

    public static ServiceProvider of(Application app) {
        install(app);
        return serviceManager.getServiceProviderForApp();
    }

    public static ServiceProvider of(Activity activity) {
        install(activity.getApplication());
        return serviceManager.getServiceProviderFor(activity);
    }

    public static ServiceProvider of(Fragment fragment) {
        install(fragment.getActivity().getApplication());
        return serviceManager.getServiceProviderFor(fragment);
    }

    public static void dispose(Activity activity) {
        assertInitialized(serviceManager);
        serviceManager.dispose(activity);
    }

    public static void dispose(Fragment fragment) {
        assertInitialized(serviceManager);
        serviceManager.dispose(fragment);
    }
}
