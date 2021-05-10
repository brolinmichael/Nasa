package com.example.nasa.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.nasa.R
import com.example.nasa.modal.Planetary

class DetailsActivity : AppCompatActivity() {
    private var tvTitle:TextView? = null
    private var tvDate:TextView? = null
    private var ivImage : ImageView? = null
    private var ivBack : ImageView? = null
    private var tvDescription:TextView? = null

    /**
     * 1) Initialising the variables
     * 2) Getting the value passed through intent from previous activity
     * 3) Setting the values received from intent to views
     * 4) Setting back pressed for  white arrow in header click action
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        tvTitle = findViewById(R.id.tvTitle) // Step 1
        tvDescription = findViewById(R.id.tvDetails)
        ivImage = findViewById(R.id.ivImage)
        tvDate = findViewById(R.id.tvDate)
        ivBack = findViewById(R.id.ivBack)

        val planetary = intent.getSerializableExtra("data") as Planetary // Step 2

        tvTitle?.text = planetary.name // Step 3
        tvDate?.text = planetary.date
        tvDescription?.text = planetary.details
        Glide.with(this)
            .load(planetary.hdUrl)
            .placeholder(R.drawable.placeholder)
            .dontAnimate()
            .into(ivImage!!)

        ivBack?.setOnClickListener { // Step 4
            onBackPressed()
        }
    }
}