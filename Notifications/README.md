# Notifications

- A notification is a pop-up window which displays on top of the screen and may include sound or vibration.
- A notification has a **title text**, a **content text**, and an **icon**.

### NotificationManager
1. Send notifications
2. Update contents
3. Cancel notifications

### Step1. Create a notification

1. Create an extension function of ```NotificationManager``` to send notifications
```kotlin
// NotificationUtils.kt
fun NotificationManager.sendNotification(messageBody: String, applicationContext: Context) {

}
```

2. Get an instance of the notification builder with the **app context** and a **channel ID**.

```kotlin
val builder = NotificationCompat.Builder(
    applicationContext,
    applicationContext.getString(R.string.notification_channel_id))
```

3. Setup the builder with an **icon**, a **title** and a **message**. 
```kotlin
val builder = NotificationCompat.Builder(
    applicationContext,
    applicationContext.getString(R.string.notification_channel_id))
        .setSmallIcon(R.drawable.cooked_egg)
        .setContentTitle(applicationContext.getString(R.string.notification_title))
        .setContentText(messageBody)
```

4. Deliver the notification with a **notification ID**.
```kotlin
notify(NOTIFICATION_ID, builder.build())
```


5. Create an instance of ```NotificationManager``` to use the extension function. 
```kotlin
// ViewModel.kt
val notificationManager = ContextCompat.getSystemService(
                app, 
                NotificationManager::class.java) 
                as NotificationManager
``` 

6. Send message with context using ```sendNotification()```.
```kotlin
notificationManager.sendNotification(app.getString(R.string.timer_running), app)
```

### Step 2. Create notification channels
Notification channels represent the type of the notifications. A user can personalize the notification settings based on their interests.

1. Create a function ```createChannel()```.
```kotlin
// MyFragment.kt
private fun createChannel(channelId: String, channelName: String) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val notificationChannel = NotificationChannel(
                channelId,
                channelName,
                NotificationManager.IMPORTANCE_HIGH
        )

        notificationChannel.enableLights(true)
        notificationChannel.lightColor = Color.RED
        notificationChannel.enableVibration(true)
        notificationChannel.description = getString(R.string.breakfast_notification_channel_description)

        val notificationManager = requireActivity().getSystemService(
            NotificationManager::class.java
        )
        notificationManager?.createNotificationChannel(notificationChannel)
    }
}
```
2. Call ```createChannel()``` with two parameters: **channel ID** and **channel name**.
```kotlin
createChannel(
    getString(R.string.notification_channel_id),
    getString(R.string.notification_channel_name)
)
```

The **channel ID** must be the same in notification builder (Step1.2)
```kotlin
val builder = NotificationCompat.Builder(
    applicationContext,
    applicationContext.getString(R.string.notification_channel_id))
```

### Step3. Setup notifications 