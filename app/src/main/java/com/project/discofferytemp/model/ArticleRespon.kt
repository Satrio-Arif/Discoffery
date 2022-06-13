package com.project.discofferytemp.model

import com.google.gson.annotations.SerializedName

data class ArticleRespon(
    @field:SerializedName("message")
    var message:String="",
    @field:SerializedName("data")
    var data:ArrayList<ArticlesDetail> = arrayListOf(ArticlesDetail())

)