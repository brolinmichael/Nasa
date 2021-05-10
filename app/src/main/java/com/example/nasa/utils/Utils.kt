package com.example.nasa.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkInfo
import android.net.ParseException
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat.getSystemService
import java.text.SimpleDateFormat
import java.util.*

object Utils {

    /**
     * This method is used to check internet connectivity
     * 1) Initialises connectivity manager
     * 2) Check active network info using connectivity manager
     * 3) True/False based on the result
     */
     fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) // Step 1
        return if (connectivityManager is ConnectivityManager) {
            val networkInfo: NetworkInfo? = connectivityManager.activeNetworkInfo // Step 2
            networkInfo?.isConnected ?: false // Step 3
        } else false
    }

    /**
     * This method is used to format date from one format to another
     * 1) Getting date as string and also getting the input and output date format
     * 2) Initialising the input/output format using Simple Date format
     * 3) Formatting date
     */
    fun formatDates(date: String?, parseInput: String?, parseOutput: String?): String? {
        var date = date // Step 1
        val input = SimpleDateFormat(parseInput) // Step 2
        val output = SimpleDateFormat(parseOutput)
        date = try {
            val oneWayTripDate: Date = input.parse(date) // Step 3
            output.format(oneWayTripDate)
        } catch (e: ParseException) {
            e.printStackTrace()
            return ""
        }
        return date
    }
}