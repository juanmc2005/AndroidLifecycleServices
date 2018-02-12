package com.android.juanmc2005.lifecycleservices.internal.injection;


public class Namer {

    public String name(Object component) {
        return component.getClass().getCanonicalName();
    }
}
