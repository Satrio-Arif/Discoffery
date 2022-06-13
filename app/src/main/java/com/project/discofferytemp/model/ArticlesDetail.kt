package com.project.discofferytemp.model

import com.google.gson.annotations.SerializedName

data class ArticlesDetail(
    @field:SerializedName("title")
    var jenis_kopi:String="",
    @field:SerializedName("origin")
    var origin:String ="",
    @field:SerializedName("type")
    var variety:String="",
    @field:SerializedName("altitude")
    var altitude:String="",
    @field:SerializedName("content")
    var about:String="",


)