package com.example.quizapp1

class QuestionList (private val questions : MutableList<QuestionTemplate>) {

    fun getNewQuestion() : QuestionTemplate? {
        if (questions.size <= 0) {
            return null
            // initialize()
        }
        val rnd = (0 until questions.size).random()
        val question = questions.removeAt(rnd)
        return question
    }
}