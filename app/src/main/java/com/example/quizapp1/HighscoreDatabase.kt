package com.example.quizapp1

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Highscore::class), version = 2)
abstract class HighscoreDatabase : RoomDatabase() {

    abstract val highscoreDao : HighscoreDao

    companion object {

        @Volatile
        private var INSTANCE : HighscoreDatabase?  = null

        fun getInstance(context: Context ) : HighscoreDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        HighscoreDatabase::class.java,
                        "highscore_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }

                return instance
            }
        }
    }
}