package com.project.discofferytemp.model

import com.google.gson.annotations.SerializedName

data class Place(
    var html_attributes: List<String> = mutableListOf(""),
    @field:SerializedName("status")
    var status:String ="",
    var next_page_token:String ="",
    @field:SerializedName("results")
    var result:List<Results> = mutableListOf(Results())
    )
