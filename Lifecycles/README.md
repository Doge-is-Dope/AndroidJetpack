# Lifecycles

Manage your activity and fragment lifecycles

It's crucial to program your app **proactively** and **defensively**.
- Proactive: Clean up unused resources to make activity which is on-screen run smoothly.
- Defensive: Be aware of actions from the OS like restarting your app.
  


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
