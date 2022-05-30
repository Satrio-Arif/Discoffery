package com.project.discofferytemp.model

import com.google.gson.annotations.SerializedName

class Loc(
    @field:SerializedName("lat")
    var lat: Double = 0.0,
    @field:SerializedName("lng")
    var lng: Double = 0.0
)