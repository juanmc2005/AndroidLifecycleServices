package com.android.juanmc2005.lifecycleservices.internal.lifecycle.managers;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.android.juanmc2005.lifecycleservices.internal.Utils;
import com.android.juanmc2005.lifecycleservices.internal.lifecycle.providers.FragmentServiceProvider;

public class FragmentServicesLifecycleManager extends ServicesLifecycleManager<FragmentServiceProvider> {

    private final Utils utils;

    public FragmentServicesLifecycleManager(Utils utils) {
        this.utils = utils;
    }

    @SuppressWarnings("ConstantConditions")
    public void register(Fragment fragment, String name, FragmentServiceProvider serviceProvider) {
        final AppCompatActivity activity = (AppCompatActivity) fragment.getActivity();
        utils.assertNotNull(activity, AppCompatActivity.class);
        activity.getSupportFragmentManager().registerFragmentLifecycleCallbacks(serviceProvider, true);
        put(name, serviceProvider);
    }

    @SuppressWarnings("ConstantConditions")
    public void unregister(Fragment fragment, String name) {
        final AppCompatActivity activity = (AppCompatActivity) fragment.getActivity();
        utils.assertNotNull(activity, AppCompatActivity.class);
        final FragmentServiceProvider provider = get(name);
        if (provider == null) return;
        remove(name);
        activity.getSupportFragmentManager().unregisterFragmentLifecycleCallbacks(provider);
    }
}
