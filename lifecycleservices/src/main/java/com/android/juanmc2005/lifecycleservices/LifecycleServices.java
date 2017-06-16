package com.android.juanmc2005.lifecycleservices;

import android.app.Activity;
import android.app.Application;
import android.support.v4.app.Fragment;

import com.android.juanmc2005.lifecycleservices.internals.ServiceManager;

import static com.android.juanmc2005.lifecycleservices.internals.Utils.assertInitialized;


public final class LifecycleServices {

    public static Builder builder() {
        return new Builder();
    }

    private final ServiceManager serviceManager;

    private LifecycleServices(ServiceManager serviceManager) {
        this.serviceManager = serviceManager;
    }

    private void install(Application app) {
        serviceManager.initialize(app.getClass().getCanonicalName());
    }

    public ServiceProvider of(Application app) {
        install(app);
        return serviceManager.getServiceProviderForApp();
    }

    public ServiceProvider of(Activity activity) {
        install(activity.getApplication());
        return serviceManager.getServiceProviderFor(activity);
    }

    public ServiceProvider of(Fragment fragment) {
        install(fragment.getActivity().getApplication());
        return serviceManager.getServiceProviderFor(fragment);
    }

    public void dispose(Activity activity) {
        assertInitialized(serviceManager);
        serviceManager.dispose(activity);
    }

    public void dispose(Fragment fragment) {
        assertInitialized(serviceManager);
        serviceManager.dispose(fragment);
    }

    private static class Builder {

        private ServiceManager manager;

        public Builder serviceManager(ServiceManager manager) {
            this.manager = manager;
            return this;
        }

        public LifecycleServices create() {
            return new LifecycleServices(manager == null ? ServiceManager.create() : manager);
        }
    }
}
