# Lifecycles

Manage your activity and fragment lifecycles

It's crucial to program your app **proactively** and **defensively**.
- Proactive: Clean up unused resources to make activity which is on-screen run smoothly.
- Defensive: Be aware of actions from the OS like restarting your app.

There're mainly 2 lifecycle-aware situations: 1. Configuration change 2. Process shutdown state loss
1. Configuration change: Rotation, device language change, physical keyboard
2. Process shutdown: The application process in the background is killed by the OS

The following will demonstrate the usage of **Lifecycle Library** and **onSaveInstanceState**.
 
  
### Activity Lifecycle States
- Resumed: Visible & has Focus
- Started: Visible
- Created: Not Visible
- Initialized | Destroyed 


### Activity Lifecycle Callbacks
- onCreate: The activity is created but not visible yet. Only called once.
- onRestart: This is being called when the activity has already been created
- onStart: The activity is visible but doesn't have focus yet
- onResume: The activity is visible and gains focus
- onPause: The activity is visible and loses focus
- onStop: The activity doesn't have focus (data is permanently saved)
- onDestroy: The activity is shutdown and can be garbage collected

### Mirroring
The Activity Lifecycle Callbacks mirror one to another.

- onCreate - onDestroy
- onStart - onStop
- onResume - onPause




#### Case 1: Start an Activity
```onCreate``` -> ```onStart``` -> ```onResume``` 

#### Case 2.1: Press "Back" button
```onPause``` -> ```onStop``` -> ```onDestroy```

#### Case 2.2: Open the Activity again
```onCreate``` -> ```onStart``` -> ```onResume``` 

#### Case 3.1: Press "Home" button
```onPause``` -> ```onStop```

#### Case 3.2: Reopen the Activity from "Recent apps"
```onRestart``` -> ```onStart``` -> ```onResume``` 


#### Case 4.1: Open up a dialog
```onPause```

#### Case 4.2: Close the dialog by clicking outside of it
```onResume```

#### Case 5: Configuration change: Rotation, device language change, physical keyboard
```onPause``` -> ```onStop``` -> ```onDestroy``` -> ```onCreate``` -> ```onStart``` -> ```onResume``` 






### Timber (logging library)
- Generate tags automatically
- Avoid logs in released app apks
- Easy integration with crash reporting

#### Setup
1. Add Timber to build.gradle. Check the latest version [here](https://github.com/JakeWharton/timber#download). 

```gradle
implementation 'com.jakewharton.timber:timber:4.7.1'
```

2. Make Application class
```kotlin
class MyApplication : Application() {
  override fun onCreate() {
    super.onCreate()
  }
}
```
3. Add Application to Manifest
```xml
<application
  android:name=".MyApplication">
   
</application>
```
4. Initialize Timber in the Application class
```kotlin
override fun onCreate() {
  super.onCreate()
  Timber.plant(Timber.DebugTree())
}
```
5. Use Timber logging statement
```kotlin
Timber.i("logging message")
```

### LifeCycle Library
Instead of handling individual component's lifecycle, LifeCycle Library provides callback method by using LifecycleOwner and LifecycleObserver.

- LifecycleOwner Interface: a class that has a lifecycle. e.g. Activity and Fragment
- LifecycleObserver Interface: Observer of a lifecycleOwner. e.g. Activity and Fragment

#### Usage

1. Extend the Observer
```kotlin
class DessertTimer : LifecycleObserver
```

2. Get the object from the LifeCycleOwner by the constructor and setup the observation
```kotlin
class DessertTimer(lifecycle : Lifecycle) : LifecycleObserver {

    init {
        lifecycle.addObserver(this)
    }
}
```

3. Create an object of the observer by passing the owner's lifecycle
```kotlin
dessertTimer = DessertTimer(this.lifecycle)
```

4. Use lifecycle annotation in observer. For example, 
```kotlin
@OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
fun dummyMethod() {
    Timber.i("I was called")
}
```

### onSaveInstanceState & onRestoreInstanceState

- In Android Pie (API 28) and beyond, ```onSaveInstanceState``` occurs after **onStop**
- It stores information as a bundle
- The super method in ```onSaveInstanceState``` saves some important data, such as EditTexts
- ```onRestoreInstanceState``` is called after **onStart** while the savedBundle can also be used in **onCreate**

For example:

```onPause``` -> ```onStop``` -> ```onSaveInstanceState``` -> ```onDestroy``` -> 
```onCreate``` -> ```onStart``` -> ```onRestoreInstanceState``` -> ```onResume``` 


#### Usage
1. Override onSaveInstanceState and save the data
```kotlin
override fun onSaveInstanceState(outState: Bundle) {
super.onSaveInstanceState(outState)
    outState.putInt(KEY_REVENUE, revenue)
    outState.putInt(KEY_DESSERT_SOLD, dessertsSold)
    outState.putInt(KEY_TIMER_SECONDS, dessertTimer.secondsCount)
}
```

2. Retrieve the data in ```onCreate``` or ```onRestoreInstanceState```

```kotlin
if (savedInstanceState != null) {
    revenue = savedInstanceState.getInt(KEY_REVENUE, 0)
    dessertsSold = savedInstanceState.getInt(KEY_DESSERT_SOLD, 0)
    dessertTimer.secondsCount = savedInstanceState.getInt(KEY_TIMER_SECONDS, 0)
    showCurrentDessert()
}
```

