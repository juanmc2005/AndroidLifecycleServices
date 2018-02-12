package com.android.juanmc2005.lifecycleservices;

import android.app.Activity;
import android.app.Application;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.android.juanmc2005.lifecycleservices.internal.ServiceManager;
import com.android.juanmc2005.lifecycleservices.internal.injection.InjectorManager;
import com.android.juanmc2005.lifecycleservices.internal.injection.Namer;
import com.android.juanmc2005.lifecycleservices.internal.lifecycle.managers.ActivityServicesLifecycleManager;
import com.android.juanmc2005.lifecycleservices.internal.lifecycle.managers.FragmentServicesLifecycleManager;

import static com.android.juanmc2005.lifecycleservices.internal.Utils.assertAppCompat;
import static com.android.juanmc2005.lifecycleservices.internal.Utils.assertInitialized;


public final class LifecycleServices {

    private static ServiceManager serviceManager = new ServiceManager(
            new InjectorManager(),
            new ActivityServicesLifecycleManager(),
            new FragmentServicesLifecycleManager(),
            new Namer()
    );

    private LifecycleServices() {
        throw new IllegalStateException("No instances allowed for class " + getClass().getSimpleName());
    }

    private static void install(Application app) {
        serviceManager.initialize(app);
    }

    public static ServiceProvider of(Application app) {
        install(app);
        return serviceManager.getServiceProviderFor(app);
    }

    public static ServiceProvider of(AppCompatActivity activity) {
        install(activity.getApplication());
        return serviceManager.getServiceProviderFor(activity);
    }

    public static ServiceProvider of(Fragment fragment) {
        install(fragment.getActivity().getApplication());
        return serviceManager.getServiceProviderFor(fragment);
    }

    public static void dispose(Activity activity) {
        assertAppCompat(activity);
        assertInitialized(serviceManager);
        serviceManager.dispose((AppCompatActivity) activity);
    }

    public static void dispose(Fragment fragment) {
        assertInitialized(serviceManager);
        serviceManager.dispose(fragment);
    }
}
