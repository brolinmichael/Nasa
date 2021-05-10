package com.example.nasa.view.activities

import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nasa.view.adapters.PlanetaryAdapter
import com.example.nasa.R
import com.example.nasa.modal.Planetary
import com.example.nasa.network.Retrofit
import com.example.nasa.repositary.PlanetaryRepository
import com.example.nasa.utils.Utils
import com.example.nasa.viewmodel.MyViewModelFactory
import com.example.nasa.viewmodel.PlanetaryViewModel
import com.facebook.shimmer.ShimmerFrameLayout
import com.google.android.material.snackbar.Snackbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeActivity : AppCompatActivity() {
    private var rvPlanetary : RecyclerView? = null
    private var planetaryList : List<Planetary> = ArrayList()
    private var planetaryAdapter : PlanetaryAdapter? = null
    private var shimmer : ShimmerFrameLayout? = null
    private var doubleBackToExitPressedOnce = false
    private lateinit var planetaryViewModel : PlanetaryViewModel
    private var planetaryRepository: PlanetaryRepository? = null

    /**
     * 1) Initialising the views
     * 2) Initialising the repository
     * 3) Initialising the view model
     * 4) Initialising the adapter
     * 5) Checking network connectivity
     * 6) Making the API call to server to fetch planetary lists
     * 7) Creating an observer instance of Live data in view model
     * 8) Updating list in adapter when a change occurs in observer
     * 9) Setting recyclerview adapter
     */
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        rvPlanetary = findViewById(R.id.rvPlanetary) // Step 1
        shimmer = findViewById(R.id.shimmer_view_container)
        rvPlanetary?.layoutManager = LinearLayoutManager(this)

        planetaryRepository = PlanetaryRepository(application) // Step 2
        planetaryViewModel = ViewModelProvider(this,MyViewModelFactory(application)).get(PlanetaryViewModel::class.java) // Step 3
        planetaryAdapter = PlanetaryAdapter(
            this,
            planetaryList
        ) // Step 4

        if (Utils.isNetworkAvailable(this)){ // Step 5
            rvPlanetary?.visibility = View.GONE
            shimmer?.visibility = View.VISIBLE
            shimmer?.startShimmer()
            getPlanetaryList() // Step 6
        } else{
            val snackbar = Snackbar.make(findViewById(R.id.coordinatorLayout), "No Internet connection", Snackbar.LENGTH_LONG)
            snackbar.show()
        }


        planetaryViewModel?.getAllPlanetary()?.observe(this, // Step 7
            Observer<List<Planetary>> {
                planetaryAdapter?.getPlanetary(it) // Step 8
                rvPlanetary?.adapter = planetaryAdapter // Step 9
            })

    }

    /**
     * This method is used to fetch Planetary list from API
     * 1) Initialising retrofit
     * 2) Making api call to the server
     * 3) Handing response inside onResponse callback
     * 4) Hiding the visibility of shimmer
     * 5) Inserting data to db
     * 6) Handling response failure condition
     */
    private fun getPlanetaryList() {
        val retrofit = Retrofit() // Step 1
        val call: Call<List<Planetary>> = retrofit.api.getAllPlanetary() // Step 2
        call.enqueue(object : Callback<List<Planetary>> {
            override fun onResponse(call: Call<List<Planetary>>, response: Response<List<Planetary>>) {
                if (response.isSuccessful) { // Step 3
                    shimmer?.stopShimmer()
                    shimmer?.visibility = View.GONE
                    rvPlanetary?.visibility = View.VISIBLE
                    planetaryRepository?.insert(response.body()!!) // Step 4
                }
            }

            override fun onFailure(call: Call<List<Planetary>>, t: Throwable) { // Step 5
                shimmer?.stopShimmer()
                shimmer?.visibility = View.GONE
                rvPlanetary?.visibility = View.VISIBLE
                Toast.makeText(this@HomeActivity, "something went wrong...", Toast.LENGTH_SHORT).show()
            }
        })
    }

    /**
     * Overriding back press method to perform doube back press to exit
     */
    override fun onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed()
            return
        }
        this.doubleBackToExitPressedOnce = true
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show()
        Handler().postDelayed(Runnable { doubleBackToExitPressedOnce = false }, 2000)
    }
}