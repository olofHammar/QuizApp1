package com.example.quizapp1

import androidx.room.*

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