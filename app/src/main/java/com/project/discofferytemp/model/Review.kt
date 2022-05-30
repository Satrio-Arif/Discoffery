package com.project.discofferytemp.model

data class Review(
    var author_name:String="",
    var author_url:String="",
    var language:String ="",
    var profile_url:String ="",
    var rating:Int =0,
    var relative_time_description:String ="",
    var text:String ="",
    var time:Int =0
)
