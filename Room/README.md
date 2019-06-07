# Android Architecture: Room

### Creating an Entity

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

