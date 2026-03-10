package com.example.followme02

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.followme.typetraining.TypeTrainingScreen
import com.example.followme02.model.ExerciseType
import com.example.followme02.model.Workout
import com.example.followme02.navigation.HeaderNavigation
import com.example.followme02.screen.achievements.AchievementsScreen
import com.example.followme02.screen.distance.DistanceLogicScreen
import com.example.followme02.screen.home.HomeScreen
import com.example.followme02.screen.profile.ProfileScreen
import com.example.followme02.screen.social.SocialScreen
import com.example.followme02.ui.WorkoutScreen


@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun FollowMeApp() {

    val navController = rememberNavController()

    Scaffold(
        topBar = {
            Column {

                CenterAlignedTopAppBar(
                    title = { Text("FollowMe") }
                )

                HeaderNavigation(navController)

            }
        }
    ) { padding ->

        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier
                .padding(padding)
        ) {
            composable("home") {
                HomeScreen(navController)
            }

            composable("workout") {
                WorkoutScreen(navController, workout = Workout(
                    id = 0,
                    exerciseType = ExerciseType.RUN,
                    distanceKm = 0f,
                    date = ""
                ), {}, {})
            }

            composable("social") {
                SocialScreen()
            }

            composable("achievements") {
                AchievementsScreen()
            }

            composable("profile") {
                ProfileScreen()
            }
        }
    }

}