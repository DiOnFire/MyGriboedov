package me.dion.mygriboedov

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import me.dion.mygriboedov.core.client.core.Client
import me.dion.mygriboedov.quiz.Answer
import me.dion.mygriboedov.quiz.Question
import me.dion.mygriboedov.quiz.QuizManager
import me.dion.mygriboedov.util.QuestionConverter
import java.util.ArrayList

class QuestionTemplateActivity : AppCompatActivity() {
    private var answerButton: Button? = null
    private var answerGroup: RadioGroup? = null
    private var variable1: RadioButton? = null
    private var variable2: RadioButton? = null
    private var variable3: RadioButton? = null
    private var questionView: TextView? = null
    private var question: Question? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question_template)

        answerButton = findViewById(R.id.answerButton)
        answerGroup = findViewById(R.id.radioGroup)
        variable1 = findViewById(R.id.variable1)
        variable2 = findViewById(R.id.variable2)
        variable3 = findViewById(R.id.variable3)
        questionView = findViewById(R.id.questionView)

        question = intent.extras?.getSerializable("question") as Question

        generateActivity()

        answerButton?.setOnClickListener {
            val answer: Answer = QuestionConverter.convertStringToAnswer(generateAnswer())

            if (answer.compare()) {
                val quizManager: QuizManager = intent.extras?.getSerializable("quizManager") as QuizManager
                quizManager.increaseScore(question)
            }
//            val client: Client = intent.extras?.getSerializable("client") as Client
        }
    }

    private fun generateActivity() {
        val variables: List<String> = question?.variables as List<String>
        questionView?.text = question?.name
        variable1?.text = variables[0]
        variable2?.text = variables[1]
        variable3?.text = variables[2]
    }

    private fun generateAnswer(): String {
        var answer: String = ""
        val client: Client = intent.extras?.getSerializable("client") as Client
        answer += client.nickname + ":"
        val selectedRadioButton = findViewById<RadioButton>(answerGroup?.checkedRadioButtonId!!)
        answer += selectedRadioButton.text
        return answer
    }

    override fun onBackPressed() {

    }
}