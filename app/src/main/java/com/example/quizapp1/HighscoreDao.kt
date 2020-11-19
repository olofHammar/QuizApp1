package com.example.quizapp1

import androidx.room.*
import java.text.FieldPosition

@Dao
interface HighscoreDao {

    @Insert
    fun insert(highscore: Highscore)

    @Delete
    fun delete(highscore: Highscore)

    @Query("UPDATE highscore SET highscorename= :playername, highscorepoints =:playerpoints WHERE position= :position")
    fun update(playername: String?, playerpoints: Int, position: Int)

    @Query("SELECT * FROM highscore")
    fun getAll() : List<Highscore>

    @Query("DELETE FROM highscore")
    fun delete()
}