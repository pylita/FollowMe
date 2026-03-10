package com.example.followme02

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.followme02.screen.distance.DistanceLogicScreen
import com.example.followme02.ui.theme.FollowMe02Theme


class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

           FollowMe02Theme{
               FollowMeApp()
           }


        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FollowMe02Theme {

    }
}

