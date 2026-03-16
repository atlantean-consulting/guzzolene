package com.guzzoline.app.ui.savedtrips

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.guzzoline.app.GuzzolineApp
import com.guzzoline.app.data.SavedTripEntity
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class SavedTripsViewModel(application: Application) : AndroidViewModel(application) {

    private val dao = (application as GuzzolineApp).database.savedTripDao()

    val trips: StateFlow<List<SavedTripEntity>> = dao.getAll()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun addTrip(name: String, origin: String, destination: String, distance: Double) {
        viewModelScope.launch {
            dao.insert(
                SavedTripEntity(
                    name = name,
                    origin = origin,
                    destination = destination,
                    distance = distance
                )
            )
        }
    }

    fun deleteTrip(id: Int) {
        viewModelScope.launch {
            dao.deleteById(id)
        }
    }
}
