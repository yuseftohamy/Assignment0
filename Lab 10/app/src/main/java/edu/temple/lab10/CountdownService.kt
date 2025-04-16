package edu.temple.lab10

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.util.Log
import kotlinx.coroutines.*

class CountdownService : Service() {

  companion object {
    var currentRemainingSeconds: Int = 0
  }

  private val serviceJob = SupervisorJob()
  private val serviceScope = CoroutineScope(Dispatchers.Default + serviceJob)

  override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
    val seconds = intent?.getIntExtra("seconds", 0) ?: 0
    Log.d("CountdownService", "Starting countdown: $seconds")

    if (seconds > 0) {
      serviceScope.launch {
        for (i in seconds downTo 1) {
          currentRemainingSeconds = i
          Log.d("CountdownService", "Countdown: $i")
          delay(1000L)
        }
        Log.d("CountdownService", "Countdown complete!")
        currentRemainingSeconds = 0
      }
    } else {
      Log.d("CountdownService", "Invalid countdown value: $seconds")
    }

    return START_NOT_STICKY
  }

  override fun onDestroy() {
    super.onDestroy()
    serviceJob.cancel()
  }

  override fun onBind(intent: Intent?): IBinder? = null
}
