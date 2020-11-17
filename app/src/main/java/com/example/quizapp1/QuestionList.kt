package com.example.quizapp1


class QuestionList(private val questions : MutableList<QuestionTemplate>) {


    fun addQuestion(questionTemplate: QuestionTemplate) {
        questions.add(questionTemplate)
    }


    fun getNewQueston() : QuestionTemplate? {
        if (questions.size <= 0) {
            return null
            // initialize()
        }
        val rnd = (0 until questions.size).random()
        val word = questions.removeAt(rnd)
        return word
    }

}