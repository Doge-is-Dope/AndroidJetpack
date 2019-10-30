# Android Jetpack

Target SDK version: 29 (Q)

![Android Architecture](Android%20Architecture.png)

- [Demo - Now Playing Movies](https://github.com/chunchiehliang/AndroidJetpack/tree/master/Demo2) 
- [Lifecycles](https://github.com/chunchiehliang/AndroidJetpack/tree/master/Lifecycles)
- [Navigation](https://github.com/chunchiehliang/AndroidJetpack/tree/master/Navigation)
- [ViewModel, LiveData & DataBinding](https://github.com/chunchiehliang/AndroidJetpack/tree/master/Architecture)
- [Room](https://github.com/chunchiehliang/AndroidJetpack/tree/master/Room)
- [Paging](https://github.com/chunchiehliang/AndroidJetpack/tree/master/Paging)
- [Notifications](https://github.com/chunchiehliang/AndroidJetpack/tree/master/Notifications)



### Common Mistakes
1. Leaking liveData: In a fragment, a live data should observe ```viewLifecycleOwner``` instead of ```this```
2. Mutable liveData: Changing the value of a ```mutableLiveData``` should take place in **ViewModel**, not **View**s. 

    
### Reference
- [Android - Guide to app architecture](https://developer.android.com/jetpack/docs/guide)

