package com.guzzoline.app.ui.calculator

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.guzzoline.app.GuzzolineApp
import com.guzzoline.app.data.TripLogEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class CalculatorState(
    val tripName: String = "",
    val origin: String = "",
    val destination: String = "",
    val distance: String = "",
    val mpg: String = "",
    val gasPrice: String = "",
    val calculatedCost: Double? = null,
    val saved: Boolean = false
)

class CalculatorViewModel(application: Application) : AndroidViewModel(application) {

    private val prefs = application.getSharedPreferences("guzzoline_prefs", Context.MODE_PRIVATE)
    private val db = (application as GuzzolineApp).database

    private val _state = MutableStateFlow(CalculatorState())
    val state: StateFlow<CalculatorState> = _state.asStateFlow()

    init {
        val lastMpg = prefs.getFloat("last_mpg", 0f)
        val lastPrice = prefs.getFloat("last_gas_price", 0f)
        _state.value = _state.value.copy(
            mpg = if (lastMpg > 0f) lastMpg.toBigDecimal().stripTrailingZeros().toPlainString() else "",
            gasPrice = if (lastPrice > 0f) lastPrice.toBigDecimal().stripTrailingZeros().toPlainString() else ""
        )
        recalculate()
    }

    fun updateTripName(value: String) {
        _state.value = _state.value.copy(tripName = value, saved = false)
    }

    fun updateOrigin(value: String) {
        _state.value = _state.value.copy(origin = value, saved = false)
    }

    fun updateDestination(value: String) {
        _state.value = _state.value.copy(destination = value, saved = false)
    }

    fun updateDistance(value: String) {
        _state.value = _state.value.copy(distance = value, saved = false)
        recalculate()
    }

    fun updateMpg(value: String) {
        _state.value = _state.value.copy(mpg = value, saved = false)
        recalculate()
        value.toFloatOrNull()?.let {
            prefs.edit().putFloat("last_mpg", it).apply()
        }
    }

    fun updateGasPrice(value: String) {
        _state.value = _state.value.copy(gasPrice = value, saved = false)
        recalculate()
        value.toFloatOrNull()?.let {
            prefs.edit().putFloat("last_gas_price", it).apply()
        }
    }

    fun prefill(name: String, origin: String, destination: String, distance: String) {
        _state.value = _state.value.copy(
            tripName = name,
            origin = origin,
            destination = destination,
            distance = distance,
            saved = false
        )
        recalculate()
    }

    fun saveToLog() {
        val s = _state.value
        val dist = s.distance.toDoubleOrNull() ?: return
        val mpg = s.mpg.toDoubleOrNull() ?: return
        val price = s.gasPrice.toDoubleOrNull() ?: return
        val cost = s.calculatedCost ?: return

        viewModelScope.launch {
            db.tripLogDao().insert(
                TripLogEntity(
                    tripName = s.tripName.ifBlank { "Unnamed Trip" },
                    origin = s.origin,
                    destination = s.destination,
                    distance = dist,
                    mpg = mpg,
                    gasPrice = price,
                    totalCost = cost
                )
            )
            _state.value = _state.value.copy(saved = true)
        }
    }

    private fun recalculate() {
        val dist = _state.value.distance.toDoubleOrNull()
        val mpg = _state.value.mpg.toDoubleOrNull()
        val price = _state.value.gasPrice.toDoubleOrNull()
        _state.value = _state.value.copy(
            calculatedCost = if (dist != null && mpg != null && mpg > 0 && price != null) {
                (dist / mpg) * price
            } else null
        )
    }
}
