package com.android.juanmc2005.lifecycleservices.internals.services;


final class ServiceNotFoundException extends RuntimeException {

    ServiceNotFoundException(String serviceName) {
        super("Could not find service " + serviceName);
    }
}
