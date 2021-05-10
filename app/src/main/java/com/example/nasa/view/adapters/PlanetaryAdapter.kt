package com.example.nasa.view.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.nasa.R
import com.example.nasa.modal.Planetary
import com.example.nasa.utils.Utils
import com.example.nasa.view.activities.DetailsActivity
import de.hdodenhof.circleimageview.CircleImageView

class PlanetaryAdapter(private val context: Context,private var planetaryList: List<Planetary>):RecyclerView.Adapter<PlanetaryAdapter.MyViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.planatary_list, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return planetaryList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val planetary = planetaryList[position]

        holder.tvTitle.text = planetary.name
        holder.tvDate.text = "Date : "+Utils.formatDates(planetary.date,"yyyy-MM-dd","MMM dd, yyyy")

        Glide.with(context)
            .load(planetary.url)
            .placeholder(R.drawable.placeholder)
            .dontAnimate()
            .into(holder.ivImageView)

        holder.itemView.setOnClickListener {
            context.startActivity(Intent(context,
                DetailsActivity::class.java).putExtra("data",planetary))
        }

    }

    fun getPlanetary(planetaryList: List<Planetary>) {
        this.planetaryList = planetaryList
    }


    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTitle : TextView = itemView.findViewById(R.id.tvTitle)
        val ivImageView : CircleImageView = itemView.findViewById(R.id.ivImageView)
        val tvDate : TextView = itemView.findViewById(R.id.tvDate)
    }

}