package com.android.juanmc2005.lifecycleservices;


final class ServiceNotFoundException extends RuntimeException {

    ServiceNotFoundException(String serviceName) {
        super("Could not find service " + serviceName);
    }
}
