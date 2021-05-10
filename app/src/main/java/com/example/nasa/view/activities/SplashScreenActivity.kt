package com.example.nasa.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.nasa.R
import kotlinx.coroutines.*

class SplashScreenActivity : AppCompatActivity() {
    private val activityScope = CoroutineScope(Dispatchers.Main)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_NASA)
        setContentView(R.layout.activity_splash_screen)
        /**
         * 1) ActivityScope one of the coroutine functions is used for performing splash delay operation.
         * 2) Delay has been set to 3 seconds.
         * 3) Calling Intent to Home Activity.
         * 4) Finishing Splash activity.
         * 5) Defining activity transition animations.
         */
        activityScope.launch{ // Step 1
            delay(3000) // Step 2
            startActivity(Intent(this@SplashScreenActivity,
                HomeActivity::class.java)) // Step 3
            finish() // Step 4
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out) // Step 5
        }
    }

    /**
     * 1) Cancelling the activity scope inside onDestroy method which is called when the activity is destroyed
     */
    override fun onDestroy() {
        activityScope.cancel() // Step 1
        super.onDestroy()
    }
}