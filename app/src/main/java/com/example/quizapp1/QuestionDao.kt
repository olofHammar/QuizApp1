package com.example.quizapp1

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface QuestionDao {

    @Insert
    fun insert(word: Word)

    @Delete
    fun delete(word: Word)

    @Query("SELECT * FROM question")
    fun getAll() : List<Word>

}