package com.example.quizapp1

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface QuestionDao {

    @Insert
    fun insert(questionTemplate: QuestionTemplate)

    @Delete
    fun delete(questionTemplate: QuestionTemplate)

    @Query("SELECT * FROM question")
    fun getAll() : List<QuestionTemplate>

    @Query("DELETE FROM question")
    fun delete()

}