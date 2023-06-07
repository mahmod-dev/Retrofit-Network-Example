package com.mahmouddev.retrofit_network_example

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.mahmouddev.retrofit_network_example.data.points.PointResponse
import com.mahmouddev.retrofit_network_example.ui.ViewEvent
import com.mahmouddev.retrofit_network_example.ui.points.PointViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    val TAG ="MainActivity"
    private val homeViewModel: PointViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getPointsObserver()

        homeViewModel.getPoints(0.0,0.0)
    }

    private fun getPointsObserver() {
        homeViewModel.homeEvent.observe(this) {
            it.getContentIfNotHandled()?.let { event ->
                when (event) {
                    is ViewEvent.Loading -> {
                    }
                    is ViewEvent.Success<PointResponse?> -> {
                        Log.e(TAG, "getPointsObserver Success: ${event.data}" )

                    }
                    is ViewEvent.Failure -> {
                        Log.e(TAG, "getPointsObserver Error: ${event.reason}", )
                    }
                }
            }
        }
    }
}