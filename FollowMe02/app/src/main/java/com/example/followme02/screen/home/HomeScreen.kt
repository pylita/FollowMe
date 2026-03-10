package com.example.followme02.screen.home


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.*
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun HomeScreen(navController: NavController) {

    Scaffold(

        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate("workout")
                },
                modifier = Modifier
                    .size(80.dp)
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add workout")
            }
        }

    ) { padding ->


        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {

            HeaderButtons()

            HeaderSection()

            Spacer(modifier = Modifier.height(20.dp))

            JourneyCard()

            Spacer(modifier = Modifier.height(20.dp))

            MapJourneyCard()

        }


    }
}


@Composable
fun HeaderButtons(){

}
@Composable
fun HeaderSection() {

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Column (
            modifier = Modifier
                .weight(1f)
        ){

            Text(
                text = "Welcome back, Alex! 👋",
                fontSize = 24.sp
            )

            Text(
                text = "Keep moving toward your destination!",
                fontSize = 14.sp,
                color = Color.Gray
            )
        }

        Row (
            modifier = Modifier
                .weight(1f)
        ) {
            StatCard("Level", "12", Color(0xFFFF9800)) // Sprint 2 - Click here to show Level details

            Spacer(modifier = Modifier.width(8.dp))

            StatCard("Points", "3450", Color(0xFF7E57C2)) // Sprint 2 - Click here to show Point details
        }
    }
}

@Composable
fun StatCard(title: String, value: String, color: Color) {

    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier.size(width = 90.dp, height = 70.dp)
    ) {

        Column(
            modifier = Modifier
                .background(
                    Brush.horizontalGradient(
                        listOf(color, color.copy(alpha = 0.7f))
                    )
                )
                .fillMaxSize()
                .padding(8.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(title, color = Color.White, fontSize = 12.sp)

            Text(value, color = Color.White, fontSize = 20.sp)
        }
    }
}

@Composable
fun JourneyCard() {

    Card(
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier.fillMaxWidth()
    ) {

        Column(
            modifier = Modifier
                .background(Color(0xFFE8F5E9))
                .padding(16.dp)
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Text(
                    text = "Your Virtual Journey",
                    fontSize = 18.sp
                )

                Text(
                    text = "287 / 500 km",
                    color = Color(0xFF2E7D32)
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            LinearProgressIndicator(
                progress = 0.57f,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp),
                color = Color(0xFF2E7D32)
            )
        }
    }
}

@Composable
fun MapJourneyCard() {

    Card(
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
    ) {

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {

            Text(
                text = "Map Route Placeholder", // Fix here
                color = Color.Gray
            )

            Icon(
                Icons.Default.LocationOn,
                contentDescription = null,
                tint = Color.Green,
                modifier = Modifier.size(50.dp)
            )
        }
    }
}