# Android Lifecycle Services

_No releases yet :(_

This is a tiny lifecycle aware dependency provider for Android. It's inspired in the [ViewModelProviders](https://developer.android.com/reference/android/arch/lifecycle/ViewModelProviders.html) from [Android Architecture Components](https://developer.android.com/topic/libraries/architecture/index.html) and it relies on [Service Tree](https://github.com/Zhuinden/service-tree) to retain instances organized according to the structure and active components of your application.

## Why should you use it?

- **No leaks:** it will never hold on to any reference of your activities or fragments
- **Low memory footprint:** it doesn't create unnecessary instances
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

- If the screen rotates, all bound instances remain, but if the activity is destroyed, then all services are disposed
- `LifecycleServices` is used to retrieve `ServiceProvider` instances given an instance of the component which is to be served
- Given a class, `ServiceProvider` provides an instance of a `LifecycleService`, which is just an abstraction that manages the creation or retrieval of the desired object. `ServiceProviders` are managed instances too, so don't worry about keeping references to them, they don't get recreated for the same Application, Activity or Fragment
- Finally, a `LifecycleService` will be able to get a managed instance for you, or it will create it using a `ServiceBuilder` that receives as a parameter. If you have more complex dependencies, you should use a well known DI library and request just the injector to be managed (An example using Dagger is shown below)
- Although `LifecycleServices` provides a method to manually dispose the instances, it's highly recommended not to use it, as the library will do that for you

Please note that the intention here is not to reinvent the wheel, so ***this does NOT replace dependency injection***.

### Dagger example

_Soon :)_

## Installation

_Soon :)_

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
