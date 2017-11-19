# Android Lifecycle Services

[![Release](https://jitpack.io/v/juanmc2005/android-lifecycle-services.svg)](https://jitpack.io/#juanmc2005/android-lifecycle-services)

This is a tiny lifecycle aware dependency provider for Android. It's inspired in the [ViewModelProviders](https://developer.android.com/reference/android/arch/lifecycle/ViewModelProviders.html) from [Android Architecture Components](https://developer.android.com/topic/libraries/architecture/index.html) and it relies on [Service Tree](https://github.com/Zhuinden/service-tree) to retain instances organized according to the structure and active components of your application.

## Why should you use it?

- **No leaks:** it will never hold on to any reference of your activities or fragments
- **Low memory footprint:** it's smart about instance creation
- **Plug and play:** it initializes itself upon first use
- **Lifecycle aware:** every resource is automatically created and disposed for you as your activities and fragments get created and destroyed
- **No imposition of inheritance:** no need to extend from an application, activity, fragment, or resource class, just use it with the objects you have without restrictions
- **Team player:** it plays well with [Dagger](https://github.com/google/dagger). Plus, it works within the scope of activities and fragments, so the rest of your app doesn't have to change at all
- **Light as air:** only 26kb and 102 methods as of June 15 2017

## Show me the code!

```java
@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    // App singleton example. This instance will always be the same.
    SomeService service = LifecycleServices.of(getApplication())
            .provide(SomeService.class)
            .getOrCreate(SomeService::new);

    /*
     * Activity singleton example.
     * This instance will always be the same throughout this activity's lifecycle
     */
    MainViewModel viewModel = LifecycleServices.of(this)
            .provide(MainViewModel.class)
            .getOrCreate(() -> new MainViewModel("some custom string"));
}
```

**Some things to note**

- If the screen rotates, all bound instances remain, but if the activity is destroyed, then all the activity's services are disposed
- `LifecycleServices` is used to retrieve a `ServiceProvider` instance associated to your Application, Activity or Fragment
- Given a class, `ServiceProvider` provides an instance of a `LifecycleService`, which is an abstraction that manages the lifespan of the required object. `ServiceProviders` are managed instances too, so don't worry about keeping references to them, they don't get recreated for the same Application, Activity or Fragment
- Finally, a `LifecycleService` will be able to get a managed instance for you, or it will create one using a `ServiceBuilder` that receives as a parameter. If you have more complex dependencies, you should use a well known DI library and just request the injector to be managed (An example using Dagger is shown below)
- Although `LifecycleServices` provides a method to manually dispose the instances, it's highly recommended not to use it, as the library will do that for you

Please note that the intention here is not to reinvent the wheel, so ***this does NOT replace dependency injection***.

### Dagger example

```java
@Inject MainViewModel viewModel;
@Inject SomeService service;

@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    // Manage the component and use it to inject your dependencies with your custom scopes
    LifecycleServices.of(MainActivity.this)
            .provide(ActivityComponent.class)
            .getOrCreate(DaggerActivityComponent.builder()
                    .appComponent(MyApplication.getComponent(this))::build)
            .inject(this);
            
    // Use your instances...
}
```

In this example, the application class handles the app component (it could have also used Lifecycle Services). We request a managed instance of an activity component and we use that to inject the dependencies. Note that this can be abstracted in a method of a parent class or in an injector helper object.

Please bear in mind that efforts are still being made to make this integration even easier.

## Installation

Add jitpack as a repository in your top-level `build.gradle` file

```gradle
allprojects {
    repositories {
        jcenter()
        maven { url "https://jitpack.io" }
    }
}
```

Add the Android Lifecycle Services dependency to your module-level `build.gradle` file

```gradle
dependencies {
    ...
    compile 'com.github.juanmc2005:android-lifecycle-services:x.y.z'
    ...
}
```

## License

```
Copyright 2017 Juan Manuel Coria

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
