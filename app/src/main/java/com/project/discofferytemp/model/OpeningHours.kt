package com.project.discofferytemp.model

class OpeningHours(
    var open_now:Boolean =false,
    var weekday_text:List<String> = mutableListOf(""),
    var peroids:List<Period> = mutableListOf(Period())
)