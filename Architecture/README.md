# Android Architecture

- UI Controller (Activity/Fragment): Display data and get OS/user events
- ViewModel: Abstract class that holds the app's UI data and survives configuration changes
- UI Controller doesn't decide what to display on screen or process what happens during an input event
- ViewModel never references fragments, activities or views

### Why ViewModel not onSaveInstanceState

ViewModel doesn't have size restrictions.

### Usage

1. Add the lifecycle dependency. Check the latest version [here](https://developer.android.com/jetpack/androidx/releases/lifecycle#declaring_dependencies).
```
def lifecycle_version = "2.0.0"
implementation "androidx.lifecycle:lifecycle-extensions:$lifecycle_version"
```

2. Create a class that extends ViewModel
```kotlin
class GameViewModel: ViewModel()
```

3. Create an instance of the ViewModel in the UI Controller (Activity/Fragment)
```kotlin
lateinit var viewModel: GameViewModel
```

4. Get the viewModel by using ```ViewModelProviders.of```
```kotlin
viewModel = ViewModelProviders.of(this).get(GameViewModel::class.java)
```




### Reference
[Code Sample - Android Architecture Blueprints](https://github.com/googlesamples/android-architecture)


