package edu.temple.lab10

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    val inputSeconds = findViewById<EditText>(R.id.inputSeconds)
    val startButton = findViewById<Button>(R.id.startButton)

    startButton.setOnClickListener {
      val secondsText = inputSeconds.text.toString()
      val seconds = secondsText.toIntOrNull()

      if (seconds != null && seconds > 0) {
        val intent = Intent(this, CountdownService::class.java).apply {
          putExtra("seconds", seconds)
        }
        startService(intent)

        Toast.makeText(this, "Countdown started for $seconds seconds", Toast.LENGTH_SHORT).show()
      } else {
        Toast.makeText(this, "Please enter a valid number", Toast.LENGTH_SHORT).show()
      }
    }
  }
}
