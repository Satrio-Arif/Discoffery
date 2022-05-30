package com.project.discofferytemp.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.project.discofferytemp.ViewPlace
import com.project.discofferytemp.databinding.ItemMapsBinding
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
        holder.data.ratingToko.rating = dataMap[position].rating.toFloat()
        Glide.with(holder.itemView.context)
            .load(getPhotoOfPlace(dataMap[position].photos[0].photo_reference,1000))// url gambar
            .into(holder.data.photoToko)


         holder.itemView.setOnClickListener {
//           val intent = Intent(context, ViewPlace::class.java)
//           intent.putExtra("KEY",dataMap[position].place_id)
//           context.startActivity(intent)
             Toast.makeText(context,dataMap[position].rating.toString(),Toast.LENGTH_SHORT).show()
       }
    }

    override fun getItemCount(): Int {
        return dataMap.size
    }
    private fun getPhotoOfPlace(photoReference: String, i: Int): String {
        val url = StringBuilder("https://maps.googleapis.com/maps/api/place/photo")
        url.append("?maxwidth=$i")
        url.append("&photoreference=$photoReference ")
        url.append("&key=AIzaSyBEnAC2oDIfNxqS9vtgB6Bx0-XEM9GY2bY")
        Log.d("place", "getPlaceDetail: "+url.toString())
        return  url.toString()
    }


}

