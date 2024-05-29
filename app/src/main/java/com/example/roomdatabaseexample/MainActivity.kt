package com.example.roomdatabaseexample

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private var backPressedOnce = false
    private val backPressHandler = Handler( Looper.getMainLooper())
    private val backPressRunnable = Runnable { backPressedOnce = false }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    override fun onBackPressed() {
        if (backPressedOnce) {
            super.onBackPressed()
            return
        }
        this.backPressedOnce = true
        Toast.makeText(this, "Ilovadan chiqish uchun yana bir marta bosing", Toast.LENGTH_SHORT).show()

        backPressHandler.postDelayed(backPressRunnable, 2000) // 2 soniya
    }

    override fun onDestroy() {
        super.onDestroy()
        backPressHandler.removeCallbacks(backPressRunnable)
    }
}