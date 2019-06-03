# Android Architecture: ViewModel & LiveData

- UI Controller (Activity/Fragment): Display data and get OS/user events
- ViewModel: Abstract class that holds the app's UI data and survives configuration changes
- UI Controller doesn't decide what to display on screen or process what happens during an input event
- ViewModel never references fragments, activities or views

### Why ViewModel not onSaveInstanceState

ViewModel doesn't have size restrictions which means you can store larger data within ViewModel.

### Outline
- [View Model Usage](https://github.com/chunchiehliang/AndroidJetpack/tree/master/Architecture#viewmodel-usage)
- [Live Data Usage](https://github.com/chunchiehliang/AndroidJetpack/tree/master/Architecture#usage)
- [View Model Encapsulation](https://github.com/chunchiehliang/AndroidJetpack/tree/master/Architecture#viewmodel-encapsulation)
- []
- [Reference](https://github.com/chunchiehliang/AndroidJetpack/tree/master/Architecture#reference)

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

- Live Data is an observable data holder class that is lifecycle-aware. 
- Live Data allows us to easily communicate from the View Model to the UIController
- Live Data keeps track of data ```state```. An example of ```state```: The button is red or the current score is 20.
- An event happens once and it's done until it's triggered again. An example of ```event```: Navigating to another screen, a buuton is pressed, a notification.

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

### ViewModel Encapsulation
It's important not to expose the fields in the ViewModel but it's necessary to be accessible in the UIController.
The following is a no-no in a good architecture

```kotlin
class GameFragment: Fragment() {
    viewModel.score.value = 1000
}
```

### How to encapsulate variables as LiveData in ViewModel
1.  Make internal and external versions of the variables


The internal version has an underline in front of the name
```kotlin
// internal
private val _score = MutableLiveData<Int>()
// external
val score: LiveData<Int>
```

2. Make a backing property for the external version that returns the internal MutableLiveData as a LiveData
```kotlin
val score: LiveData<Int>
    get() = _score
```
3. In the view model, use the internal, mutable version of the variables



### Reference
[Code Sample - Android Architecture Blueprints](https://github.com/googlesamples/android-architecture)
[Backing properties Kotlin](https://kotlinlang.org/docs/reference/properties.html#backing-properties)
[Backing properties Android](https://developer.android.com/kotlin/style-guide#backing-properties)


