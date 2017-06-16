package com.android.juanmc2005.lifecycleservices.internals.lifecycle;

import android.support.v4.app.FragmentManager;

import com.android.juanmc2005.lifecycleservices.internals.providers.FragmentServiceProvider;


public final class FragmentServicesLifecycleManager extends ServicesLifecycleManager<FragmentServiceProvider> {

    public void register(FragmentManager fragmentManager, String tag, FragmentServiceProvider provider) {
        fragmentManager.registerFragmentLifecycleCallbacks(provider, true);
        put(tag, provider);
    }

    public void unregister(FragmentManager fragmentManager, String tag) {
        final FragmentServiceProvider provider = get(tag);
        if (provider != null) {
            remove(tag);
            fragmentManager.unregisterFragmentLifecycleCallbacks(provider);
        }
    }
}
