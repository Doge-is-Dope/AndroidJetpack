# Android Architecture: ViewModel & LiveData

- UI Controller (Activity/Fragment): Display data and get OS/user events
- ViewModel: Abstract class that holds the app's UI data and survives configuration changes
- UI Controller doesn't decide what to display on screen or process what happens during an input event
- ViewModel never references fragments, activities or views

### Why ViewModel not onSaveInstanceState

ViewModel doesn't have size restrictions.

### ViewModel Usage

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

### LiveData

LiveData is an observable data holder class that is lifecycle-aware

### Usage

1. Add the lifecycle dependency. Check the latest version [here](https://developer.android.com/jetpack/androidx/releases/lifecycle#declaring_dependencies).
```
def lifecycle_version = "2.0.0"
implementation "androidx.lifecycle:lifecycle-extensions:$lifecycle_version"
```

2. Wrap the variable with LiveData
```kotlin
val score = MutableLiveData<Int>()
```

3. Change references and add the required 

3. Setup the observation relationship. ```observe(UIController, Observer { ... })```
```kotlin
viewModel.score.observe(this, Observer { newScore -> updateScoreText(newScore) }) 
```

### Reference
[Code Sample - Android Architecture Blueprints](https://github.com/googlesamples/android-architecture)


