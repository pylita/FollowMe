package com.example.followme02.screen.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.DirectionsRun
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.followme02.R
import com.example.followme02.ui.theme.PrimaryBlue
import com.example.followme02.ui.theme.PrimaryGreen
import com.example.followme02.ui.theme.SoftOrange
import com.example.followme02.ui.theme.SoftPurple
import com.example.followme02.ui.theme.BackgroundTop
import com.example.followme02.ui.theme.BackgroundMiddle
import com.example.followme02.ui.theme.BackgroundBottom

@Composable
fun ProfileScreen(
    uiState: ProfileUiState,
    modifier: Modifier = Modifier
) {
    PrimaryGreen
    PrimaryBlue
    SoftPurple
    SoftOrange

    val backgroundGradient = Brush.verticalGradient(
        colors = listOf(
            BackgroundTop,
            BackgroundMiddle,
            BackgroundBottom
        )
    )

    val progress = if (uiState.goalTargetKm > 0f) {
        uiState.goalCurrentKm / uiState.goalTargetKm
    } else {
        0f
    }

    Surface(
        modifier = modifier.fillMaxSize(),
        color = Color.Transparent
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(backgroundGradient)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp)
            ) {
                Text(
                    text = stringResource(R.string.profile_title),
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color(0xFF111827)
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = stringResource(R.string.profile_subtitle),
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color(0xFF6B7280)
                )

                Spacer(modifier = Modifier.height(20.dp))

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(
                            width = 1.dp,
                            color = Color.White.copy(alpha = 0.45f),
                            shape = RoundedCornerShape(32.dp)
                        ),
                    shape = RoundedCornerShape(32.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xF8FFFFFF)
                    ),
                    elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(22.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            ProfileAvatar(
                                username = uiState.username,
                                avatarUrl = uiState.avatarUrl
                            )

                            Spacer(modifier = Modifier.width(16.dp))

                            Column(
                                modifier = Modifier.weight(1f)
                            ) {
                                Text(
                                    text = uiState.username,
                                    style = MaterialTheme.typography.headlineSmall,
                                    fontWeight = FontWeight.ExtraBold,
                                    color = Color(0xFF111827)
                                )

                                Spacer(modifier = Modifier.height(4.dp))

                                Text(
                                    text = uiState.email,
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = Color(0xFF6B7280)
                                )

                                Spacer(modifier = Modifier.height(6.dp))

                                Text(
                                    text = stringResource(R.string.profile_motivation),
                                    style = MaterialTheme.typography.bodySmall,
                                    color = Color(0xFF4B5563)
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(20.dp))

                        Row(
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            StatBadge(
                                title = stringResource(R.string.profile_level),
                                value = uiState.currentLevel.toString(),
                                backgroundColor = SoftOrange,
                                icon = {
                                    Icon(
                                        imageVector = Icons.Default.EmojiEvents,
                                        contentDescription = stringResource(R.string.profile_level_icon_content_description),
                                        tint = Color(0xFFFF8A00)
                                    )
                                },
                                modifier = Modifier.weight(1f)
                            )

                            StatBadge(
                                title = stringResource(R.string.profile_points),
                                value = uiState.totalPoints.toString(),
                                backgroundColor = SoftPurple,
                                icon = {
                                    Icon(
                                        imageVector = Icons.Default.Star,
                                        contentDescription = stringResource(R.string.profile_points_icon_content_description),
                                        tint = Color(0xFF7C3AED)
                                    )
                                },
                                modifier = Modifier.weight(1f)
                            )
                        }

                        Spacer(modifier = Modifier.height(18.dp))

                        Row(
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            Button(
                                onClick = { },
                                modifier = Modifier
                                    .weight(1f)
                                    .height(54.dp),
                                shape = RoundedCornerShape(20.dp),
                                contentPadding = PaddingValues(0.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color.Transparent,
                                    contentColor = Color.White
                                )
                            ) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .background(
                                            brush = Brush.horizontalGradient(
                                                colors = listOf(PrimaryGreen, PrimaryBlue)
                                            ),
                                            shape = RoundedCornerShape(20.dp)
                                        ),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.Edit,
                                            contentDescription = stringResource(R.string.profile_edit_icon_content_description)
                                        )
                                        Spacer(modifier = Modifier.width(8.dp))
                                        Text(
                                            text = stringResource(R.string.profile_edit_button),
                                            fontWeight = FontWeight.SemiBold
                                        )
                                    }
                                }
                            }

                            OutlinedButton(
                                onClick = { },
                                modifier = Modifier
                                    .weight(1f)
                                    .height(54.dp),
                                shape = RoundedCornerShape(20.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.EmojiEvents,
                                    contentDescription = stringResource(R.string.profile_achievements_icon_content_description)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = stringResource(R.string.profile_achievements_button),
                                    maxLines = 1
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(18.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    SmallStatCard(
                        title = stringResource(R.string.profile_total_km),
                        value = stringResource(
                            R.string.profile_total_km_value,
                            uiState.totalAccumulatedKm.toString()
                        ),
                        icon = Icons.AutoMirrored.Filled.DirectionsRun,
                        iconTint = PrimaryGreen,
                        modifier = Modifier.weight(1f)
                    )

                    SmallStatCard(
                        title = stringResource(R.string.profile_workouts),
                        value = uiState.workouts.toString(),
                        icon = Icons.Default.EmojiEvents,
                        iconTint = Color(0xFFFF8A00),
                        modifier = Modifier.weight(1f)
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    SmallStatCard(
                        title = stringResource(R.string.profile_streak),
                        value = stringResource(
                            R.string.profile_streak_days,
                            uiState.streakDays
                        ),
                        icon = Icons.Default.LocalFireDepartment,
                        iconTint = Color(0xFFFF5A36),
                        modifier = Modifier.weight(1f)
                    )

                    SmallStatCard(
                        title = stringResource(R.string.profile_team),
                        value = uiState.teamName,
                        icon = Icons.Default.Groups,
                        iconTint = Color(0xFF7C3AED),
                        modifier = Modifier.weight(1f)
                    )
                }

                Spacer(modifier = Modifier.height(18.dp))

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(28.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFEFF8F3)),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.Top
                        ) {
                            Column {
                                Text(
                                    text = stringResource(R.string.profile_current_journey_goal),
                                    style = MaterialTheme.typography.titleLarge,
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFF111827)
                                )

                                Spacer(modifier = Modifier.height(4.dp))

                                Text(
                                    text = stringResource(R.string.profile_goal_route),
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = Color(0xFF6B7280)
                                )
                            }

                            Text(
                                text = stringResource(
                                    R.string.profile_goal_progress,
                                    uiState.goalCurrentKm.toInt(),
                                    uiState.goalTargetKm.toInt()
                                ),
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                color = PrimaryGreen
                            )
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        LinearProgressIndicator(
                            progress = { progress },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(10.dp)
                                .clip(RoundedCornerShape(50)),
                            color = PrimaryGreen,
                            trackColor = Color(0xFFCDEBDD)
                        )

                        Spacer(modifier = Modifier.height(14.dp))

                        Text(
                            text = stringResource(
                                R.string.profile_goal_remaining,
                                (uiState.goalTargetKm - uiState.goalCurrentKm).toInt()
                            ),
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color(0xFF374151)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(18.dp))

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(28.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xF2FFFFFF)),
                    elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp),
                        verticalArrangement = Arrangement.spacedBy(14.dp)
                    ) {
                        Text(
                            text = stringResource(R.string.profile_details_title),
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF111827)
                        )

                        ProfileDetailRow(
                            label = stringResource(R.string.profile_favorite_activity),
                            value = uiState.favoriteActivity,
                            icon = Icons.AutoMirrored.Filled.DirectionsRun,
                            iconTint = PrimaryGreen
                        )

                        ProfileDetailRow(
                            label = stringResource(R.string.profile_team),
                            value = uiState.teamName,
                            icon = Icons.Default.Groups,
                            iconTint = Color(0xFF7C3AED)
                        )

                        ProfileDetailRow(
                            label = stringResource(R.string.profile_location),
                            value = uiState.location,
                            icon = Icons.Default.Place,
                            iconTint = Color(0xFF3B82F6)
                        )

                        ProfileDetailRow(
                            label = stringResource(R.string.profile_member_since),
                            value = uiState.memberSince,
                            icon = Icons.Default.Star,
                            iconTint = Color(0xFFFF8A00)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}