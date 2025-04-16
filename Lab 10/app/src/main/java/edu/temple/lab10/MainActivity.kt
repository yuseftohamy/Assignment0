package edu.temple.lab10

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

  private val PREFS_NAME = "CountdownPrefs"
  private val KEY_REMAINING_SECONDS = "remaining_seconds"
  private val KEY_WAS_PAUSED = "was_paused"
  private val DEFAULT_SECONDS = 100

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    val inputSeconds = findViewById<EditText>(R.id.inputSeconds)
    val startButton = findViewById<Button>(R.id.startButton)
    val pauseButton = findViewById<Button>(R.id.pauseButton)
    val prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    // Check if a valid paused countdown should be resumed
    val wasPaused = prefs.getBoolean(KEY_WAS_PAUSED, false)
    val savedSeconds = prefs.getInt(KEY_REMAINING_SECONDS, -1)

    if (wasPaused && savedSeconds > 0) {
      inputSeconds.setText(savedSeconds.toString())
      Toast.makeText(this, "Paused countdown found: $savedSeconds seconds", Toast.LENGTH_SHORT).show()
    } else {
      inputSeconds.setText(DEFAULT_SECONDS.toString())
    }

    startButton.setOnClickListener {
      val wasPausedStart = prefs.getBoolean(KEY_WAS_PAUSED, false)
      val savedStart = prefs.getInt(KEY_REMAINING_SECONDS, -1)
      val inputText = inputSeconds.text.toString()
      val inputValue = inputText.toIntOrNull()

      val secondsToStart = when {
        wasPausedStart && savedStart > 0 -> savedStart
        inputValue != null && inputValue > 0 -> inputValue
        else -> DEFAULT_SECONDS
      }

      if (secondsToStart > 0) {
        val intent = Intent(this, CountdownService::class.java).apply {
          putExtra("seconds", secondsToStart)
        }
        startService(intent)

        // Clear paused flags after starting
        prefs.edit()
          .remove(KEY_REMAINING_SECONDS)
          .putBoolean(KEY_WAS_PAUSED, false)
          .apply()

        Toast.makeText(this, "Countdown started from $secondsToStart seconds", Toast.LENGTH_SHORT).show()
      } else {
        Toast.makeText(this, "Please enter a valid number", Toast.LENGTH_SHORT).show()
      }
    }

    pauseButton.setOnClickListener {
      val remaining = CountdownService.currentRemainingSeconds
      if (remaining > 0) {
        prefs.edit()
          .putInt(KEY_REMAINING_SECONDS, remaining)
          .putBoolean(KEY_WAS_PAUSED, true)
          .apply()

        stopService(Intent(this, CountdownService::class.java))
        Toast.makeText(this, "Countdown paused at $remaining seconds", Toast.LENGTH_SHORT).show()
      } else {
        Toast.makeText(this, "No active countdown to pause", Toast.LENGTH_SHORT).show()
      }
    }
  }
}
