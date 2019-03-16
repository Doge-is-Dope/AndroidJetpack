# Lifecycles

Manage your activity and fragment lifecycles

It's crucial to program your app **proactively** and **defensively**.
- Proactive: Clean up unused resources to make activity which is on-screen run smoothly.
- Defensive: Be aware of actions from the OS like restarting your app.
  


### Activity Lifecycle States
- Created
- Started
- Resumed
- Destroyed
- Initialized

### Activity Lifecycle Callbacks
- onCreate
- onStart
- onResume
- onDestroy
- onPause
- onStop
- onRestart




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
