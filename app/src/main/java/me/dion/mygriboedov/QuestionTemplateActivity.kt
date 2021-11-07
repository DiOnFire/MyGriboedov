package me.dion.mygriboedov

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import me.dion.mygriboedov.quiz.Answer
import me.dion.mygriboedov.quiz.Question
import me.dion.mygriboedov.quiz.QuizManager
import me.dion.mygriboedov.util.QuestionConverter

class QuestionTemplateActivity : AppCompatActivity() {
    private var answerButton: Button? = null
    private var answerGroup: RadioGroup? = null
    private var variable1: RadioButton? = null
    private var variable2: RadioButton? = null
    private var variable3: RadioButton? = null
    private var questionView: TextView? = null
    private var question: Question? = null
    private var quizManager: QuizManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question_template)

        answerButton = findViewById(R.id.answerButton)
        answerGroup = findViewById(R.id.radioGroup)
        variable1 = findViewById(R.id.variable1)
        variable2 = findViewById(R.id.variable2)
        variable3 = findViewById(R.id.variable3)
        questionView = findViewById(R.id.questionView)

        val bundle: Bundle? = intent.extras
        question = bundle?.getSerializable("question") as Question
        quizManager = bundle.getSerializable("quizManager") as? QuizManager
        //   val client: Client = intent.extras?.getSerializable("client") as Client

        generateActivity()

        answerButton?.setOnClickListener {
            if (generateAnswer() != null) {
                val answer: Answer =
                    QuestionConverter.convertStringToAnswer(question, generateAnswer())
                if (answer.compare()) {
                    quizManager?.increaseScore(question)

                    val intent: Intent = Intent(
                        this,
                        QuestionTemplateActivity::class.java
                    )

                    intent.putExtra("question", quizManager?.nextQuestion)
                    intent.putExtra("quizManager", quizManager)

                    startActivity(intent)
                }
            } else {
                NoAnswerAlert().show(supportFragmentManager, "noAnswerAlert")
            }
        }
    }

    private fun generateActivity() {
        val variables: List<String> = question?.variables as List<String>
        questionView?.text = question?.name
        variable1?.text = variables[0]
        variable2?.text = variables[1]
        variable3?.text = variables[2]
    }

    private fun generateAnswer(): String? {
        var answer: String = ""
//        answer += client.nickname + ":"
        val selectedRadioButton = findViewById<RadioButton>(answerGroup?.checkedRadioButtonId!!)

        if (selectedRadioButton != null) {
            answer += selectedRadioButton.text
            return answer
        }

        return null
    }

    override fun onBackPressed() {

    }
}