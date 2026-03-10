package com.example.followme02.ui

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.followme02.model.ExerciseType
import com.example.followme02.model.Workout
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import kotlin.math.max
import kotlin.math.roundToInt

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WorkoutScreen(
    navController: NavController,
    workout: Workout,
    onSave: (Workout) -> Unit,
    onCancel: () -> Unit
) {
    val formatter = remember { DateTimeFormatter.ofPattern("yyyy-MM-dd") }
    val today = remember { LocalDate.now() }

    var selectedType by remember { mutableStateOf(workout.exerciseType) }

    // distance stepper
    var distanceKm by remember { mutableStateOf(workout.distanceKm.coerceAtLeast(0f)) }

    // dato lagres som LocalDate
    val initialDate = remember {
        runCatching { LocalDate.parse(workout.date, formatter) }.getOrElse { today }
    }
    var selectedDate by remember { mutableStateOf(initialDate) }

    // dropdown state
    var typeExpanded by remember { mutableStateOf(false) }

    // date picker dialog state
    var showDateDialog by remember { mutableStateOf(false) }



    Column(
        modifier = Modifier
            //.padding(padding)
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        // 1) Aktivitetstype: knapp åpner meny
        Text("Aktivitetstype", style = MaterialTheme.typography.titleMedium)

        ExposedDropdownMenuBox(
            expanded = typeExpanded,
            onExpandedChange = { typeExpanded = !typeExpanded }
        ) {
            OutlinedTextField(
                value = selectedType.name, // WALK/RUN...
                onValueChange = {},
                readOnly = true,
                trailingIcon = {
                    Icon(Icons.Default.ArrowDropDown, contentDescription = null)
                },
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth(),
                label = { Text("Velg aktivitet") }
            )

            ExposedDropdownMenu(
                expanded = typeExpanded,
                onDismissRequest = { typeExpanded = false }
            ) {
                DropdownMenuItem(
                    text = { Text("Walk") },
                    onClick = { selectedType = ExerciseType.WALK; typeExpanded = false }
                )
                DropdownMenuItem(
                    text = { Text("Run") },
                    onClick = { selectedType = ExerciseType.RUN; typeExpanded = false }
                )
                DropdownMenuItem(
                    text = { Text("Cycle") },
                    onClick = { selectedType = ExerciseType.CYCLE; typeExpanded = false }
                )
                DropdownMenuItem(
                    text = { Text("Ski") },
                    onClick = { selectedType = ExerciseType.SKI; typeExpanded = false }
                )
            }
        }

        // 2) Distanse: stepper med piler opp/ned
        Text("Distanse (km)", style = MaterialTheme.typography.titleMedium)

        DistanceStepper(
            value = distanceKm,
            step = 0.1f,
            minValue = 0f,
            onValueChange = { distanceKm = it }
        )

        // 3) Dato: felt som åpner kalender-dialog
        Text("Dato", style = MaterialTheme.typography.titleMedium)

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { showDateDialog = true }
        ) {
            OutlinedTextField(
                value = selectedDate.format(formatter),
                onValueChange = {},
                enabled = false,   // viktig!
                leadingIcon = {
                    Icon(Icons.Default.DateRange, contentDescription = null)
                },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Velg dato") }
            )
        }

        // DatePickerDialog
        if (showDateDialog) {
            val datePickerState = rememberDatePickerState(
                // start på valgt dato
                initialSelectedDateMillis = selectedDate
                    .atStartOfDay(ZoneId.systemDefault())
                    .toInstant()
                    .toEpochMilli(),
                // stoppe fremtidige datoer
                selectableDates = object : SelectableDates {
                    override fun isSelectableDate(utcTimeMillis: Long): Boolean {
                        val picked = Instant.ofEpochMilli(utcTimeMillis)
                            .atZone(ZoneId.systemDefault())
                            .toLocalDate()
                        return !picked.isAfter(today) // ikke lov etter i dag
                    }
                }
            )

            DatePickerDialog(
                onDismissRequest = { showDateDialog = false },
                confirmButton = {
                    TextButton(onClick = {
                        val millis = datePickerState.selectedDateMillis
                        if (millis != null) {
                            val picked = Instant.ofEpochMilli(millis)
                                .atZone(ZoneId.systemDefault())
                                .toLocalDate()
                            if (!picked.isAfter(today)) {
                                selectedDate = picked
                            }
                        }
                        showDateDialog = false
                    }) { Text("OK") }
                },
                dismissButton = {
                    TextButton(onClick = { showDateDialog = false }) { Text("Avbryt") }
                }
            ) {
                DatePicker(state = datePickerState)
            }
        }

        // Point system
        Text("Points", style = MaterialTheme.typography.titleMedium)
        OutlinedTextField(
            value = "100", // fix here
            onValueChange = {},
            enabled = false,
            readOnly = true,
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Points gained from current session") } // use string resource
        )

        // Buttons
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.padding(top = 8.dp)
        ) {
            Button(onClick = {
                val updated = workout.copy(
                    exerciseType  = selectedType,
                    distanceKm = distanceKm,
                    date = selectedDate.format(formatter)
                )
                onSave(updated)
            }) {
                Text("Lagre")
            }

            OutlinedButton(onClick = { onCancel
                navController.navigate("home")}) {
                Text("Avbryt")
            }
        }
    }
}


@Composable
private fun DistanceStepper(
    value: Float,
    step: Float,
    minValue: Float,
    onValueChange: (Float) -> Unit
) {
    //1 desimal (meter/100m)
    val shown = ((value * 10).roundToInt() / 10f)

    // Tekstfeltet følger value
    var inputText by remember(value) { mutableStateOf(shown.toString()) }

    OutlinedCard(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .padding(horizontal = 12.dp, vertical = 10.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            // Manuell input
            OutlinedTextField(
                value = inputText,
                onValueChange = { newValue ->
                    inputText = newValue
                    val parsed = newValue.replace(',', '.').toFloatOrNull()
                    if (parsed != null) {
                        onValueChange(max(minValue, parsed))
                    }
                },
                label = { Text("km") },
                singleLine = true,
                modifier = Modifier.weight(1f)
            )

            Spacer(Modifier.width(12.dp))

            //Piler for små justeringer
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                IconButton(onClick = { onValueChange(value + step) }) {
                    Icon(Icons.Default.KeyboardArrowUp, contentDescription = "Øk")
                }
                IconButton(onClick = { onValueChange(max(minValue, value - step)) }) {
                    Icon(Icons.Default.KeyboardArrowDown, contentDescription = "Minsk")
                }
            }
        }
    }
}