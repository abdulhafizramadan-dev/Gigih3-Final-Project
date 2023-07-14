package com.gigih.ahr

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.gigih.ahr.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

   companion object {
       private const val TAG = "MainActivity"
   }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.homeAppbar.setOnDisasterQueryChanged {
            Log.d(TAG, "onCreate: Query = $it")
        }

    }
}