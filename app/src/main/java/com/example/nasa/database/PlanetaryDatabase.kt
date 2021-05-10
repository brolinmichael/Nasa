package com.example.nasa.database

import android.content.Context
import android.os.AsyncTask
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.nasa.database.dao.PlanetaryDao
import com.example.nasa.modal.Planetary

@Database(entities = [Planetary::class], version = 2)
abstract class PlanetaryDatabase : RoomDatabase() {
    abstract fun planetaryDao(): PlanetaryDao

    companion object {
        private const val DATABASE_NAME = "PlanetaryDatabase"
        @Volatile
        private var INSTANCE: PlanetaryDatabase? = null
        fun getInstance(context: Context?): PlanetaryDatabase? {
            if (INSTANCE == null) {
                synchronized(PlanetaryDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context!!, PlanetaryDatabase::class.java,
                            DATABASE_NAME
                        )
                            .addCallback(callback)
                            .fallbackToDestructiveMigration()
                            .build()
                    }
                }
            }
            return INSTANCE
        }

        var callback: Callback = object : Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                PopulateAsynTask(INSTANCE)
            }
        }
    }

    internal class PopulateAsynTask(planetaryDatabase: PlanetaryDatabase?) :
        AsyncTask<Void?, Void?, Void?>() {
        private var planetaryDao: PlanetaryDao = planetaryDatabase?.planetaryDao()!!
        override fun doInBackground(vararg params: Void?): Void? {
            planetaryDao.deleteAll()
            return null
        }

    }
}
