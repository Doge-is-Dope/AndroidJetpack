# Android Architecture: ViewModel & LiveData

- UI Controller (Activity/Fragment): Display data and get OS/user events
- ViewModel: Abstract class that holds the app's UI data and survives configuration changes
- LiveData: A class that wraps around the data and allows to be observed by UI Controllers 
- UI Controller doesn't decide what to display on screen or process what happens during an input event
- ViewModel never references fragments, activities or views


### Android Architecture
- [Benefits of using Android Architecture](https://github.com/chunchiehliang/AndroidJetpack/tree/master/Architecture#benefits-of-using-android-architecture)
- [View Model Usage](https://github.com/chunchiehliang/AndroidJetpack/tree/master/Architecture#viewmodel-usage)
- [Live Data Usage](https://github.com/chunchiehliang/AndroidJetpack/tree/master/Architecture#usage)
- [View Model Encapsulation](https://github.com/chunchiehliang/AndroidJetpack/tree/master/Architecture#viewmodel-encapsulation)
- [Event in View Model: CountDownTimer](https://github.com/chunchiehliang/AndroidJetpack/tree/master/Architecture#event-in-view-model-countdowntimer)
- [ViewModel Factory](https://github.com/chunchiehliang/AndroidJetpack/tree/master/Architecture#viewmodel-factory-pass-information-to-view-model)

Reduce code by data binding

- [Add ViewModel to Data Binding](https://github.com/chunchiehliang/AndroidJetpack/tree/master/Architecture#add-viewmodel-to-data-binding)
- [Add LiveData to Data Binding](https://github.com/chunchiehliang/AndroidJetpack/tree/master/Architecture#add-livedata-to-data-binding)
- [LiveData Map Transformation](https://github.com/chunchiehliang/AndroidJetpack/tree/master/Architecture#livedata-map-transformation)
- [Reference](https://github.com/chunchiehliang/AndroidJetpack/tree/master/Architecture#reference)


### Benefits of using Android Architecture

1. Organized
2. Easier to debug
3. Fewer lifecycle issues
4. Modular
5. Testable

### Why ViewModel not onSaveInstanceState

ViewModel doesn't have size restrictions which means you can store larger data within ViewModel.

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

### Event in View Model: CountDownTimer

A [CountDownTimer](https://developer.android.com/reference/android/os/CountDownTimer) is a timer that can be scheduled by specifying ```millisInFuture``` and ```countDownInterval```. For example, ```CountDownTimer(30000L, 1000L)``` schedules a 30 seconds countdown. The following shows an example of using ```CountDownTimer``` in a View Model.

1. Create a timer field.
```kotlin
private val timer: CountDownTimer
```

2. Create a properly encapsulated LiveData for the current time
```kotlin
private val _currentTime = MutableLiveData<Long>()
val currentTime: LiveData<Long>
    get() = _currentTime
```

3. Initialize the timer object and start the timer
```kotlin
timer = object : CountDownTimer(COUNTDOWN_TIME, ONE_SECOND) {
    override fun onTick(millisUntilFinished: Long) {
        // what should happen each tick of the timer
        _currentTime.value = millisUntilFinished / ONE_SECOND
        }

    override fun onFinish() {
        // what should happen when the timer finishes
        _currentTime.value = DONE
        _eventGameFinish.value = true
    }
}

timer.start()
```

4. Cancel the timer in view model to avoid memory leaks
```kotlin
override fun onCleared() {
    super.onCleared()
    timer.cancel()
}
```

5. Update the UI in UI Controller (and format the string if it's needed)
```kotlin
viewModel.currentTime.observe(this, Observer { newTime ->
    binding.timerText.text = DateUtils.formatElapsedTime(newTime)
})
```


  
### ViewModel Factory: Pass information to view model

A class that knows how to create ViewModels.

1. Create a ViewModel that takes in parameters
```kotlin
class ScoreViewModel(finalScore: Int): ViewModel(){
}
```

2. Make a ViewModel Factory for ViewModel and return an instance of the ViewModel in the overridden ```create``` method
```kotlin
class ScoreViewModelFactory(private val finalScore: Int) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ScoreViewModel::class.java)) {
            return ScoreViewModel(finalScore) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
```

3. Create and construct a ViewModelFactory in UI Controller
```kotlin
private lateinit var viewModel: ScoreViewModel
private lateinit var viewModelFactory: ScoreViewModelFactory

// ...

// Get args using by navArgs property delegate
val scoreFragmentArgs by navArgs<ScoreFragmentArgs>()

// Convert this class to properly observe and use ScoreViewModel
viewModelFactory = ScoreViewModelFactory(scoreFragmentArgs.score)
viewModel = ViewModelProviders.of(this, viewModelFactory).get(ScoreViewModel::class.java)
```


### Add ViewModel to Data Binding

Use ViewModel's functions in a layout file by using data binding 

1. Add a data variable in ```<layout>``` (.xml)
```xml
<layout>
    <data>
        <variable
            name="gameViewModel"
            type="com.example.android.game.GameViewModel" />
    </data>
</layout>
```

2. Use the ViewModel variable to handle clicking in layout file. For example, you can define an ```onclick``` for buttons. 
```xml
<Button
    android:id="@+id/skip_button"
    android:onClick="@{() -> gameViewModel.onSkip()}"
/>
```

3. Pass the ViewModel into the data binding
```kotlin
// in Fragment.onCreate
binding.gameViewModel = viewModel
```

4. Remove the observer in the UI Controller

### Add LiveData to Data Binding
Use LiveData to automatically update layout via data binding.
1. Use the LiveData from ViewModel to set the text attribute in layout

There's no need to use ```value``` for the field. Be careful that the return value has to be String. In this case, string-formatting may apply to the value  
```xml
<TextView
android:text="@{gameViewModel.word}" />
```

or use string-format in string resource. For instance,

```xml
<TextView
android:text="@{@string/quote_format(gameViewModel.word)}" />
```

2. Set ```lifecycleOwner``` to the UI Controller to make the data binding lifecycle aware
```kotlin
binding.lifecycleOwner = this
```

3. Remove the observer in the UI Controller


### LiveData Map Transformation

The best practice to convert an Integer (or other type) to to a String for LiveData. E.g. convert the current time into a formatted String.

1. In ViewModel, create a new LiveData for String type using ```Transformations.map```.

This will store the String version of the variable.
```kotlin
val currentTimeString = Transformations.map(currentTime) {time ->
    DateUtils.formatElapsedTime(time)
}
```

2. In layout file, set the text for the TextView
```xml
<TextView
android:text="@{gameViewModel.currentTimeString}" />
```

3. Remove the observer in the UI Controller
 



### Reference
- [Code Sample - Android Architecture Blueprints](https://github.com/googlesamples/android-architecture)
- [Backing properties Kotlin](https://kotlinlang.org/docs/reference/properties.html#backing-properties)
- [Backing properties Android](https://developer.android.com/kotlin/style-guide#backing-properties)
- [Use LiveData to notify the UI about data changes](https://developer.android.com/topic/libraries/data-binding/architecture#livedata)


