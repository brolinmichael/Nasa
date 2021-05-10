package com.example.nasa.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.nasa.modal.Planetary
import com.example.nasa.repositary.PlanetaryRepository

 class PlanetaryViewModel(application: Application) : ViewModel() {
    private val planetaryRepository: PlanetaryRepository = PlanetaryRepository(application)
    private var getAllPlanetary: LiveData<List<Planetary>> = planetaryRepository.getAllPlanetary()!!


    fun insert(list: List<Planetary>) {
        planetaryRepository.insert(list)
    }

    fun getAllPlanetary(): LiveData<List<Planetary>> {
        return getAllPlanetary
    }
}