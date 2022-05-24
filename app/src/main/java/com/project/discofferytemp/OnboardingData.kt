package com.project.discofferytemp

import androidx.annotation.DrawableRes

data class OnboardingData(
    @DrawableRes val imageOB : Int,
    @DrawableRes val imageHeader : Int,
    val desc : String
)

