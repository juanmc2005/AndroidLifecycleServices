package com.android.juanmc2005.lifecycleservices;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.zhuinden.servicetree.ServiceTree;

import static com.android.juanmc2005.lifecycleservices.Utils.assertAppCompat;


final class ServiceManager {

    private static final String TAG = "ServiceManager";

    private final ServiceTree tree;
    private final ActivityServicesLifecycleManager activityServicesManager;
    private final FragmentServicesLifecycleManager fragmentServicesManager;

    private boolean initialized = false;

    ServiceManager() {
        tree = new ServiceTree();
        activityServicesManager = new ActivityServicesLifecycleManager();
        fragmentServicesManager = new FragmentServicesLifecycleManager();
    }

    void initialize(String rootTag) {
        if (!isInitialized()) {
            tree.createRootNode(rootTag);
            initialized = true;
        }
    }

    boolean isInitialized() {
        return initialized;
    }

    ServiceProvider getServiceProviderFor(Activity activity) {
        final ServiceTree t = tree;
        final String tag = activity.getClass().getCanonicalName();
        ActivityServiceProvider provider = activityServicesManager.get(tag);
        if (provider == null) {
            provider = new ActivityServiceProvider(
                    t.hasNodeWithKey(tag)
                            ? t.getNode(tag)
                            : t.createChildNode(t.getTreeRoot(), tag));
            activityServicesManager.register(activity.getApplication(), tag, provider);
        }
        return provider;
    }

    ServiceProvider getServiceProviderFor(Fragment fragment) {
        assertAppCompat(fragment.getActivity());
        final ServiceTree t = tree;
        final AppCompatActivity activity = (AppCompatActivity) fragment.getActivity();
        final String activityTag = activity.getClass().getCanonicalName();
        final String fragmentTag = fragment.getClass().getCanonicalName();
        FragmentServiceProvider provider = fragmentServicesManager.get(fragmentTag);
        if (provider == null) {
            provider = new FragmentServiceProvider(
                    t.hasNodeWithKey(fragmentTag)
                            ? t.getNode(fragmentTag)
                            : t.createChildNode(t.getNode(activityTag), fragmentTag));
            fragmentServicesManager.register(
                    activity.getSupportFragmentManager(), fragmentTag, provider);
        }
        return provider;
    }

    ServiceProvider getServiceProviderForApp() {
        return AppServiceProvider.get(tree);
    }

    void dispose(Activity activity) {
        final String tag = activity.getClass().getCanonicalName();
        dispose(tag);
        activityServicesManager.unregister(activity.getApplication(), tag);
    }

    void dispose(Fragment fragment) {
        final String tag = fragment.getClass().getCanonicalName();
        final AppCompatActivity activity = (AppCompatActivity) fragment.getActivity();
        dispose(tag);
        fragmentServicesManager.unregister(activity.getSupportFragmentManager(), tag);
    }

    private void dispose(String tag) {
        ServiceTree t = tree;
        if (t.hasNodeWithKey(tag)) {
            t.removeNodeAndChildren(t.getNode(tag));
        } else {
            Log.d(TAG, tag + "'s services are already disposed");
        }
    }
}
