package com.project.discofferytemp.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Articles(
    @field:SerializedName("Id_article")
    var id: Int=0,
    @field:SerializedName("Title")
    var Title:String="",
    @field:SerializedName("Contents")
    var Content:String="",
    @field:SerializedName("Source")
    var Source:String="",
    var img:Int =0
):Parcelable


