package com.project.discofferytemp.model

import android.location.Location
import com.google.gson.annotations.SerializedName

class Geo(
    @field:SerializedName("location")
    var location:Loc =Loc(),
    var viewport:Viewport = Viewport(),

)