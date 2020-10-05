package com.example.quizapp1

/*
Här har jag mina singletons. Det tre översta variablerna använder jag enbart som "tags" för att skicka variabler mellan de olika aktiviteterna.
Jag har även alla frågor till quizet här. Jag har gjort en funktion som returnerar en lista med frågor. Detta fungerar som en "static list".
 */
object Constants {

    const val USER_NAME: String = "user name"
    const val TOTAL_QUESTIONS: String = "total questions"
    const val CORRECT_ANSWERS: String = "correct answers"

    fun getQuestion (): ArrayList<Question> {
        val questionsList = ArrayList<Question>()

        val q1 = Question (
            1,
            "Vilket land tillhör denna flagga?",
             R.drawable.argentina,
            "Argentina",
            "Honduras",
            "Uruguay",
            "El Salvador",
            1)
        questionsList.add(q1)

        val q2 = Question (
            2,
            "Vilket land tillhör denna flagga?",
            R.drawable.aland,
            "Norge",
            "Island",
            "Åland",
            "Färöarna",
            3)
        questionsList.add(q2)

        val q3 = Question (
            3,
            "Vilket land tillhör denna flagga?",
        R.drawable.antarktis,
        "Grönland",
        "Antarktis",
        "Atlantis",
        "Belgien",
        2)
        questionsList.add(q3)

        val q4 = Question (
            4,
            "Vilket land tillhör denna flagga?",
            R.drawable.australien,
            "Storbritannien",
            "Nya Zeeland",
            "Singapore",
            "Australien",
            4)
        questionsList.add(q4)

        val q5 = Question (
            5,
            "Vilket land tillhör denna flagga?",
            R.drawable.albanien,
            "Andorra",
            "Armenien",
            "Albanien",
            "Georgien",
            3)
        questionsList.add(q5)

        val q6 = Question (
            6,
            "Vilket land tillhör denna flagga?",
            R.drawable.angola,
            "Angola",
            "Kongo",
            "Tanzania",
            "Uganda",
            1)
        questionsList.add(q6)

        val q7 = Question (
            7,
            "Vilket land tillhör denna flagga?",
            R.drawable.azerbaijan,
            "Uzbekistan",
            "Kazakstan",
            "Algeriet",
            "Azerbaijan",
            4)
        questionsList.add(q7)

        val q8 = Question (
            8,
            "Vilket land tillhör denna flagga?",
            R.drawable.osterriket,
            "Polen",
            "Österriket",
            "Danmark",
            "Lettland",
            2)
        questionsList.add(q8)

        val q9 = Question (
            9,
            "Vilket land tillhör denna flagga?",
            R.drawable.usa,
            "Puerto Rico",
            "Texas",
            "USA",
            "Kanada",
            3)
        questionsList.add(q9)

        val q10 = Question (
            10,
            "Vilket land tillhör denna flagga?",
            R.drawable.bangladesh,
            "Japan",
            "Niger",
            "Grönland",
            "Bangladesh",
            4)
        questionsList.add(q10)

        val q11 = Question (
            11,
            "Vilket land tillhör denna flagga?",
            R.drawable.belgien,
            "Tyskland",
            "Brunei",
            "Belgien",
            "Uganda",
            3)
        questionsList.add(q11)

        val q12 = Question (
            12,
            "Vilket land tillhör denna flagga?",
            R.drawable.bolivia,
            "Bolivia",
            "Senegal",
            "Etiopien",
            "Litauen",
            1)
        questionsList.add(q12)

        val q13 = Question (
            13,
            "Vilket land tillhör denna flagga?",
            R.drawable.bosniaherzegovina,
            "Jugoslavien",
            "Serbien",
            "Bosnien & Herzegovina",
            "Kroatien",
            3)
        questionsList.add(q13)

        val q14 = Question (
            14,
            "Vilket land tillhör denna flagga?",
            R.drawable.brasilien,
            "Guatemala",
            "Brasilien",
            "Peru",
            "Argentina",
            2)
        questionsList.add(q14)

        val q15 = Question (
            15,
            "Vilket land tillhör denna flagga?",
            R.drawable.bulgarien,
            "Ungern",
            "Bulgarien",
            "Iran",
            "Italien",
            2)
        questionsList.add(q15)

        val q16 = Question (
            16,
            "Vilket land tillhör denna flagga?",
            R.drawable.burma,
            "Laos",
            "Kambodja",
            "Etiopien",
            "Burma",
            4)
        questionsList.add(q16)

        val q17 = Question (
            17,
            "Vilket land tillhör denna flagga?",
            R.drawable.storbrittanien,
            "Storbritannien",
            "Australien",
            "England",
            "Skottland",
            1)
        questionsList.add(q17)

        val q18 = Question (
            18,
            "Vilket land tillhör denna flagga?",
            R.drawable.vitryssland,
            "Ryssland",
            "Estland",
            "Vitryssland",
            "Ukraina",
            3)
        questionsList.add(q18)

        val q19 = Question (
            19,
            "Vilket land tillhör denna flagga?",
            R.drawable.celfenbenskusten,
            "Indien",
            "Elfenbenskusten",
            "Irland",
            "Sydafrika",
            2)
        questionsList.add(q19)

        val q20 = Question (
            20,
            "Vilket land tillhör denna flagga?",
            R.drawable.chile,
            "Kuba",
            "Chile",
            "Peru",
            "Puerto Rico",
            2)
        questionsList.add(q20)

        val q21 = Question (
            21,
            "Vilket land tillhör denna flagga?",
            R.drawable.ckambodja,
            "Laos",
            "Thailand",
            "Vietnam",
            "Kambodja",
            4)
        questionsList.add(q21)

        val q22 = Question (
            22,
            "Vilket land tillhör denna flagga?",
            R.drawable.ckamerun,
            "Kamerun",
            "Etiopien",
            "Elfenbenskusten",
            "Sydafrika",
            1)
        questionsList.add(q22)

        val q23 = Question (
            23,
            "Vilket land tillhör denna flagga?",
            R.drawable.ckanada,
            "USA",
            "Schweiz",
            "Kanada",
            "Österriket",
            3)
        questionsList.add(q23)

        val q24 = Question (
            24,
            "Vilket land tillhör denna flagga?",
            R.drawable.ckina,
            "Malaysia",
            "Kina",
            "Japan",
            "Nord Korea",
            2)
        questionsList.add(q24)

        val q25 = Question (
            25,
            "Vilket land tillhör denna flagga?",
            R.drawable.ckroatien,
            "Jugoslavien",
            "Serbien",
            "Montenegron",
            "Kroatien",
            4)
        questionsList.add(q25)

        val q26 = Question (
            26,
            "Vilket land tillhör denna flagga?",
            R.drawable.ckuba,
            "Kuba",
            "Belize",
            "Puerto Rico",
            "Dominikanska Republiken",
            1)
        questionsList.add(q26)

        val q27 = Question (
            27,
            "Vilket land tillhör denna flagga?",
            R.drawable.colombia,
            "Ecuador",
            "Venezuela",
            "Colombia",
            "Brasilien",
            3)
        questionsList.add(q27)

        val q28 = Question (
            28,
            "Vilket land tillhör denna flagga?",
            R.drawable.costarica,
            "Kuba",
            "Costa Rica",
            "Puerto Rico",
            "Dominikanska Republiken",
            1)
        questionsList.add(q28)

        val q29 = Question (
            29,
            "Vilket land tillhör denna flagga?",
            R.drawable.danmark,
            "Norge",
            "Danmark",
            "Finland",
            "Sverige",
            2)
        questionsList.add(q29)

        val q30 = Question (
            30,
            "Vilket land tillhör denna flagga?",
            R.drawable.dominikanskarepubliken,
            "Kuba",
            "Costa Rica",
            "Kroatien",
            "Dominikanska Republiken",
            4)
        questionsList.add(q30)

        val q31 = Question (
            31,
            "Vilket land tillhör denna flagga?",
            R.drawable.ecuador,
            "Colombia",
            "Venezuela",
            "Ecuador",
            "Guatemala",
            3)
        questionsList.add(q31)

        val q32 = Question (
            32,
            "Vilket land tillhör denna flagga?",
            R.drawable.egyptne,
            "Egypten",
            "Marocko",
            "Algeriet",
            "Iran",
            1)
        questionsList.add(q32)

        val q33 = Question (
            33,
            "Vilket land tillhör denna flagga?",
            R.drawable.england,
            "Danmark",
            "England",
            "Österiket",
            "Georgien",
            2)
        questionsList.add(q33)

        val q34 = Question (
            34,
            "Vilket land tillhör denna flagga?",
            R.drawable.enordirland,
            "England",
            "Irland",
            "Syd Irland",
            "Nord Irland",
            4)
        questionsList.add(q34)

        val q35 = Question (
            35,
            "Vilket land tillhör denna flagga?",
            R.drawable.eskottland,
            "Finland",
            "Grekland",
            "Skottland",
            "Israel",
            3)
        questionsList.add(q35)

        val q36 = Question (
            36,
            "Vilket land tillhör denna flagga?",
            R.drawable.estland,
            "Estland",
            "Lettland",
            "Litauen",
            "Finland",
            1)
        questionsList.add(q36)

        val q37 = Question (
            37,
            "Vilket land tillhör denna flagga?",
            R.drawable.etiopien,
            "Sydafrika",
            "Etiopien",
            "Bolivia",
            "Kamerun",
            2)
        questionsList.add(q37)

        val q38 = Question (
            38,
            "Vilket land tillhör denna flagga?",
            R.drawable.ewales,
            "Nord Irland",
            "Makedonien",
            "Andorra",
            "Wales",
            4)
        questionsList.add(q38)

        return questionsList
    }

}