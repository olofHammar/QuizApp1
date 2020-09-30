package com.example.quizapp1
/*
(Ändrade från 'data class' till 'class') Här skapar jag en klass som är mallen för alla frågor.
 */
class Question (
    val id: Int,
    val question: String,
    val image: Int,
    val optionOne: String,
    val optionTwo: String,
    val optionThree: String,
    val optionFour: String,
    val correctAnswer: Int
    )