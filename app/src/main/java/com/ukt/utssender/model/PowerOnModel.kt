package com.ukt.utssender.model

import com.google.firebase.firestore.PropertyName

data class PowerOnModel(
    @PropertyName("startTask")
    val startTask: Boolean = false
)
