package com.project.discofferytemp.model

data class PlaceDetail(
    var status: String = "",
    var result: Results = Results(),
    var html_attributes: List<String> = mutableListOf("")

)