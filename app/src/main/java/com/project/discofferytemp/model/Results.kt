package com.project.discofferytemp.model

import com.google.gson.annotations.SerializedName

data class Results(
    @field:SerializedName("geometry")
    var geometri: Geo = Geo(),
    @field:SerializedName("photos")
    var photos: List<Photos> = mutableListOf(Photos()),
    var id:String ="",
    var place_id:String = "",
    var price_level:Int =0,
    var rating:Double =0.0,
    var reference:String ="",
    var scope:String ="",
    var type:List<String> = mutableListOf(""),
    var vincity:String ="",
    var opening_hours:OpeningHours =OpeningHours(),
    var name:String ="",
    var icon:String ="",
    var addres_component:List<AddressComponent> = mutableListOf(AddressComponent()),
    var adr_address:String ="",
    var formatted_address:String="",
    var formatted_phone_number:String="",
    var url:String="",
    var review:List<Review> = mutableListOf(Review()),
    var utc_offset:Int=0,
    var website:String =""

)
