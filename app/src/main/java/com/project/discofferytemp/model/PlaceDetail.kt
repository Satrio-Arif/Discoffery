package com.project.discofferytemp.model

import com.google.gson.annotations.SerializedName

data class PlaceDetail(
    @field:SerializedName("status")
    var status: String = "",
    @field:SerializedName("result")
    var result: Results = Results(),
    var html_attributes: List<String> = mutableListOf("")

)