# Android Architecture: Room

- [Create an Entity]()
- [Data Access Object (DAO)]()
- [Create a Room database]()
- [Add a ViewModel]()
- [Multithreading & Coroutines]()


### Create an Entity

Room uses annotations to create an entity. The following is an example.

```kotlin
@Entity(tableName = "daily_sleep_quality_table")
data class SleepNight(
    @PrimaryKey(autoGenerate = true)
    var nightId: Long = 0L,

    @ColumnInfo(name = "start_time_milli")
    val startTimeMilli: Long = System.currentTimeMillis(),

    @ColumnInfo(name = "end_time_milli")
    var endTimeMilli: Long = startTimeMilli,
    
    @ColumnInfo(name = "quality_rating")
    var sleepQuality: Int = -1
)
```

### Data Access Object (DAO)

DAO is an custom interface used to  map SQL queries with annotations for accessing the database. By using annotations, Room creates the necessary code. 

DAO annotations: ```@Insert```, ```@Delete```, ```@Update``` & ```@Query```

```kotlin
@Dao
interface SleepDatabaseDao {
    @Insert
    fun insert(night: SleepNight)

    @Update
    fun update(night: SleepNight)

    @Query("SELECT * FROM daily_sleep_quality_table WHERE nightId = :key")
    fun get(key: Long): SleepNight?

    @Query("DELETE FROM daily_sleep_quality_table")
    fun clear()

    @Query("SELECT * FROM daily_sleep_quality_table ORDER BY nightId DESC")
    fun getAllNights(): LiveData<List<SleepNight>>

    @Query("SELECT * FROM daily_sleep_quality_table  ORDER BY nightId LIMIT 1")
    fun getTonight(): SleepNight?
}
```

### Create a Room database

1. Extend RoomDatabase and create Database

Create an abstract class that extends ```RoomDatabase``` with the annotation.

```kotlin
@Database(entities = [SleepNight::class], version = 1, exportSchema = false)
abstract class SleepDatabase : RoomDatabase() {}
```
Annotation description:
- ```entities```: Add all the entities in the table here as a list.
- ```version```: Assign the version of the database. The version should be updated whenever the schema is changes
- ```exportSchema```: The value is ```true``` by default. It saves the schema of the database to a folder and provides a version history of the database. This is helpful when a database is changed often.
  

2. Associate with DAO

Declare an abstract value that returns the DAO

```kotlin
abstract val sleepDatabaseDao: SleepDatabaseDao
```

3. Define a ```companion object```

The companion object allows the client to access the methods for creating or getting the database without instantiating the class.

```kotlin
companion object {
 }
``` 

4. In ```companion object```, declare a private nullable variable INSTANCE for the database

INSTANCE will keep a reference to the database once it has been initiated and it will avoid repeatedly opening connections to the database.

```@Volatile``` makes sure the value of INSTANCEwill be up-to-date and the same to all execution threads. The value annotates ```@Volatile``` will never be cached, and all changes will be done on the main memory.   
```kotlin
companion object {
    @Volatile
    private var INSTANCE: SleepDatabase? = null
}
```

5. In ```companion object```, define the getInstance() method with a Context parameter
```kotlin
fun getInstance(context: Context): SleepDatabase {}
```

6. Inside ```getInstance()``` add a ```synchronized{}``` block

Multiple threads can ask for the database instance at the same time. Having ```synchronized``` can allow only one thread of execution at the time can enter the block of the code and eventually "makes sure the database get initialized once".

```kotlin
synchronized(this) { }
```

7. Inside ```synchronized```, invoke ```databaseBuilder``` to instantiate the database.
```kotlin
synchronized(this) {
    var instance = INSTANCE

    if (instance == null) {
        instance = Room.databaseBuilder(
            context.applicationContext,
            SleepDatabase::class.java,
            "sleep_history_database")
    }

    return instance
}
```

8. Add the required migration strategy to the builder

If the database's schema (e.g. change of column name)is changed, it's necessary to move the data from the previous schema to the new schema.

```kotlin
.fallbackToDestructiveMigration()
```

9. Call ```build()```
```kotlin
instance = Room.databaseBuilder(
    context.applicationContext,
    SleepDatabase::class.java,
    "sleep_history_database"
    ).fallbackToDestructiveMigration().build()
```

10. Assign ```INSTANCE = instance``` inside the if statement

```kotlin
@Database(entities = [SleepNight::class], version = 1, exportSchema = false)
abstract class SleepDatabase : RoomDatabase() {
    abstract val sleepDatabaseDao: SleepDatabaseDao

    companion object {
        @Volatile
        private var INSTANCE: SleepDatabase? = null

        fun getInstance(context: Context): SleepDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        SleepDatabase::class.java,
                        "sleep_history_database"
                    ).fallbackToDestructiveMigration().build()

                    INSTANCE = instance
                }
                
                return instance
            }
        }
    }
}
```

### Add a ViewModel

When using Room, a custom ViewModel usually extends ```AndroidViewModel()```. 

The class extends ```AndroidViewModel()``` takes application context as parameter

1. Create ViewModel & ViewModelFactory

```kotlin
class SleepTrackerViewModel(val database: SleepDatabaseDao, 
application: Application) : AndroidViewModel(application) {
}
```

The ViewModelFactory is boilerplate which can be used in the future.

```kotlin
class SleepTrackerViewModelFactory(private val dataSource: SleepDatabaseDao, private val application: Application) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SleepTrackerViewModel::class.java)) {
            return SleepTrackerViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
```
2. In the UI Controller, get a reference to the application context

```requireNotNull()``` throws an IllegalArgumentException if the value is null.

```kotlin
val application = requireNotNull(this.activity).application
```

3. Get a reference to the DAO of the database
```kotlin
val dataSource = SleepDatabase.getInstance(application).sleepDatabaseDao
```

4. Create an instance of the viewModelFactory

```kotlin
val viewModelFactory = SleepTrackerViewModelFactory(dataSource, application)
```

5. Get a reference to the ViewModel

```kotlin
val sleepTrackerViewModel = ViewModelProviders
                        .of(this, viewModelFactory)
                        .get(SleepTrackerViewModel::class.java)
```

##### Data Binding for ViewModel

6. Set the lifecycle owner for the binding

```kotlin
binding.setLifecycleOwner(this)
```

7. In the layout file, set the ```<variable>``` tag as a reference to the ViewModel for DataBinding

```xml
<data>
    <variable
        name="sleepTrackerViewModel"
        type="com.chunchiehliang.kotlin.room.sleeptracker.SleepTrackerViewModel" />
</data>
```

### Multithreading & Coroutines



### Reference

-[Room migrations](https://medium.com/androiddevelopers/testing-room-migrations-be93cdb0d975)



