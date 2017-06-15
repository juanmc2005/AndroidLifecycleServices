package com.android.juanmc2005.lifecycleservices;

import android.support.v4.app.FragmentManager;


final class FragmentServicesLifecycleManager extends ServicesLifecycleManager<FragmentServiceProvider> {

    void register(FragmentManager fragmentManager, String tag, FragmentServiceProvider provider) {
        fragmentManager.registerFragmentLifecycleCallbacks(provider, true);
        put(tag, provider);
    }

    void unregister(FragmentManager fragmentManager, String tag) {
        final FragmentServiceProvider provider = get(tag);
        if (provider != null) {
            remove(tag);
            fragmentManager.unregisterFragmentLifecycleCallbacks(provider);
        }
    }
}
