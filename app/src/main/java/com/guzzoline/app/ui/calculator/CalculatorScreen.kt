package com.guzzoline.app.ui.calculator

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import java.util.Locale

@Composable
fun CalculatorScreen(
    prefilledName: String = "",
    prefilledOrigin: String = "",
    prefilledDestination: String = "",
    prefilledDistance: String = "",
    onLoadSavedTrip: () -> Unit = {},
    viewModel: CalculatorViewModel = viewModel()
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(prefilledName, prefilledOrigin, prefilledDestination, prefilledDistance) {
        if (prefilledName.isNotBlank() || prefilledDistance.isNotBlank()) {
            viewModel.prefill(prefilledName, prefilledOrigin, prefilledDestination, prefilledDistance)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = "Fuel the Rig",
            style = MaterialTheme.typography.headlineLarge
        )

        OutlinedTextField(
            value = state.tripName,
            onValueChange = viewModel::updateTripName,
            label = { Text("Trip Name") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            OutlinedTextField(
                value = state.origin,
                onValueChange = viewModel::updateOrigin,
                label = { Text("Origin") },
                modifier = Modifier.weight(1f),
                singleLine = true
            )
            OutlinedTextField(
                value = state.destination,
                onValueChange = viewModel::updateDestination,
                label = { Text("Destination") },
                modifier = Modifier.weight(1f),
                singleLine = true
            )
        }

        OutlinedTextField(
            value = state.distance,
            onValueChange = viewModel::updateDistance,
            label = { Text("Distance (miles)") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
        )

        OutlinedTextField(
            value = state.mpg,
            onValueChange = viewModel::updateMpg,
            label = { Text("Fuel Efficiency (MPG)") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
        )

        OutlinedTextField(
            value = state.gasPrice,
            onValueChange = viewModel::updateGasPrice,
            label = { Text("Gas Price ($/gal)") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
        )

        Spacer(modifier = Modifier.height(4.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Guzzolene Cost",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
                Text(
                    text = if (state.calculatedCost != null) {
                        String.format(Locale.US, "$%.2f", state.calculatedCost)
                    } else {
                        "---"
                    },
                    style = MaterialTheme.typography.headlineLarge,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
                if (state.calculatedCost != null) {
                    val gallons = state.distance.toDouble() / state.mpg.toDouble()
                    Text(
                        text = String.format(Locale.US, "%.1f gallons of guzzolene", gallons),
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(4.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(
                onClick = viewModel::saveToLog,
                modifier = Modifier.weight(1f),
                enabled = state.calculatedCost != null && !state.saved
            ) {
                Icon(Icons.Default.Save, contentDescription = null)
                Text(
                    text = if (state.saved) " Witnessed!" else " Log Trip",
                    modifier = Modifier.padding(start = 4.dp)
                )
            }
            OutlinedButton(
                onClick = onLoadSavedTrip,
                modifier = Modifier.weight(1f)
            ) {
                Icon(Icons.Default.Bookmark, contentDescription = null)
                Text(
                    text = " Known Routes",
                    modifier = Modifier.padding(start = 4.dp)
                )
            }
        }
    }
}
