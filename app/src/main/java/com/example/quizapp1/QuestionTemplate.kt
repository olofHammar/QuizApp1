package com.example.quizapp1


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "question")
data class Word(@PrimaryKey(autoGenerate = true) val id: Int,
                @ColumnInfo(name = "question") var question: String,
                @ColumnInfo(name = "image") var image: Int,
                @ColumnInfo(name = "optionone") val optionOne: String,
                @ColumnInfo(name = "optiontwo") val optionTwo: String,
                @ColumnInfo(name = "optionthree") val optionThree: String,
                @ColumnInfo(name = "optionfour") val optionFour: String,
                @ColumnInfo(name = "correctanswer") val correctAnswer: Int)

