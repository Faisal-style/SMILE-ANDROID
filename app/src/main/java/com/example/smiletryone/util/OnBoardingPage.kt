package com.example.smiletryone.util

import android.media.Image
import androidx.annotation.DrawableRes
import com.example.smiletryone.R

sealed class OnBoardingPage(

    val title: String, @DrawableRes val image: Int, val description: String
) {
    object First : OnBoardingPage(
        title = "Welcome To Smile",
        image = R.drawable.first_onboard,
        description = "Smile is a application that can be your trusted place to talk too. If you have something to tell, we promise we can hear you."
    )

    object Second : OnBoardingPage(
        title = "Welcome To Smile",
        image = R.drawable.second_onboard,
        description = "Every single people have their own problem, but you don’t have to carry it alone. Sharing with others can make you feel better."
    )
}
