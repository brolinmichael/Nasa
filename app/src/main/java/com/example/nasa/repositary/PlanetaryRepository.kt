package com.example.nasa.repositary

import android.app.Application
import android.os.AsyncTask
import android.widget.Toast
import androidx.lifecycle.LiveData
import com.example.nasa.database.PlanetaryDatabase
import com.example.nasa.database.dao.PlanetaryDao
import com.example.nasa.modal.Planetary
import java.lang.Exception

class PlanetaryRepository(application: Application) {
    private val database: PlanetaryDatabase = PlanetaryDatabase.getInstance(application)!!
    private val getAllPlanetary: LiveData<List<Planetary>> = database.planetaryDao().getAllPlanetary()

    fun insert(planatoryList: List<Planetary>) {
        try {
            database.let { InsertAsynTask(it).execute(planatoryList) }
        }catch (e:Exception){
            e.printStackTrace()
        }
    }

    fun getAllPlanetary(): LiveData<List<Planetary>>? {
        return getAllPlanetary
    }

    internal class InsertAsynTask(planetaryDatabase: PlanetaryDatabase) :
        AsyncTask<List<Planetary>?, Void?, Void?>() {
        private val planetaryDao: PlanetaryDao = planetaryDatabase.planetaryDao()
        protected override fun doInBackground(vararg params: List<Planetary>?): Void? {
            params[0]?.let { planetaryDao.insert(it) }
            return null
        }

    }

}