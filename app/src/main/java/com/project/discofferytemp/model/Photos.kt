package com.project.discofferytemp.model

import com.google.gson.annotations.SerializedName

class Photos(
    var height: Int = 0,
    var width: Int = 0,
    var html_attributes:List<String> = mutableListOf(""),
    @field:SerializedName("photo_reference")
    var photo_reference:String =""
    )