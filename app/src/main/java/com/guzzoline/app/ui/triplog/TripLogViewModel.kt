package com.guzzoline.app.ui.triplog

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.guzzoline.app.GuzzolineApp
import com.guzzoline.app.data.TripLogEntity
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class TripLogViewModel(application: Application) : AndroidViewModel(application) {

    private val dao = (application as GuzzolineApp).database.tripLogDao()

    val entries: StateFlow<List<TripLogEntity>> = dao.getAll()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun deleteEntry(id: Int) {
        viewModelScope.launch {
            dao.deleteById(id)
        }
    }

    suspend fun getAllForExport(): List<TripLogEntity> {
        return dao.getAllOnce()
    }
}
