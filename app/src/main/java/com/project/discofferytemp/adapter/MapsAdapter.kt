package com.project.discofferytemp.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.project.discofferytemp.ViewPlace
import com.project.discofferytemp.databinding.ItemMapsBinding
import com.project.discofferytemp.helper.GoogleMaps
import com.project.discofferytemp.model.Results

class MapsAdapter(var context: Context) :RecyclerView.Adapter<MapsAdapter.MapsHolder>() {
    private var dataMap =ArrayList<Results>()
    fun setdata(paramData:ArrayList<Results>){
        this.dataMap.clear()
        this.dataMap.addAll(paramData)
    }
    class MapsHolder(var data:ItemMapsBinding):RecyclerView.ViewHolder(data.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MapsHolder {
        val view = ItemMapsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MapsHolder(view)
    }

    override fun onBindViewHolder(holder: MapsHolder, position: Int) {
        holder.data.namaToko.text = dataMap[position].name
        holder.data.ratingToko.rating = dataMap[position].rating
        holder.data.alamatToko.text =dataMap[position].vincity
        Glide.with(holder.itemView.context)
            .load(GoogleMaps.getPhotoOfPlace(dataMap[position].photos[0].photo_reference,1000))// url gambar
            .into(holder.data.photoToko)


         holder.itemView.setOnClickListener {
             //val map =Intent(Intent.ACTION_VIEW, Uri.parse(dataMap[position].url))
             val move =Intent(context,ViewPlace::class.java)
             move.putExtra(KEY,dataMap[position].place_id)
             context.startActivity(move)
       }
    }

    override fun getItemCount(): Int {
        return dataMap.size
    }

    companion object{
        const val KEY ="id"
    }

}

