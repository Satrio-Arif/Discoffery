package com.project.discofferytemp.model

import com.google.gson.annotations.SerializedName

data class Results(
    @field:SerializedName("geometry")
    var geometri: Geo = Geo(),
    @field:SerializedName("photos")
    var photos: List<Photos> = mutableListOf(Photos()),
    @field:SerializedName("place_id")
    var place_id:String = "",
    @field:SerializedName("rating")
    var rating:Float =0.0F,
    var type:List<String> = mutableListOf(""),
    @field:SerializedName("vicinity")
    var vincity:String ="",
    @field:SerializedName("name")
    var name:String ="",
    var url:String="",
)
