package com.android.juanmc2005.lifecycleservicessample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.android.juanmc2005.lifecycleservices.LifecycleServices;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // App singleton example
        SomeService service = LifecycleServices.of(getApplication())
                .provide(SomeService.class)
                .getOrCreate(SomeService::new);

        // Activity bound instance example
        MainViewModel viewModel = LifecycleServices.of(this)
                .provide(MainViewModel.class)
                .getOrCreate(() -> new MainViewModel("some custom string"));
    }
}
