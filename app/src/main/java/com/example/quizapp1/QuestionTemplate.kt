package com.example.quizapp1


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "word")
data class Word(@PrimaryKey(autoGenerate = true) val id: Int,
                @ColumnInfo(name = "english") var english: String,
                @ColumnInfo(name = "swedish") var swedish: String)


