package com.android.juanmc2005.lifecycleservices.internal;


public final class ServiceNotFoundException extends RuntimeException {

    public ServiceNotFoundException(String serviceName) {
        super("Could not find service " + serviceName);
    }
}
