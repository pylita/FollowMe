package com.example.followme02.model

import kotlinx.serialization.Serializable

@Serializable
data class Achievements(
    val achievementId: Int,
    val title: String,
    val description: String,
    val pointsReward: Int
)
