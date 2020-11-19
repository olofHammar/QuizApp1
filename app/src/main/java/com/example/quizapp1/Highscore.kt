package com.example.quizapp1

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "highscore")
data class Highscore(@PrimaryKey(autoGenerate = false) val id: Int,
                     @ColumnInfo(name = "position") val position: Int,
                     @ColumnInfo(name = "highscorename") var highscoreOneName: String,
                     @ColumnInfo(name = "highscorepoints") var highscoreOnePoints: Int)