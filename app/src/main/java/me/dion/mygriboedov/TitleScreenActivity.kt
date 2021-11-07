package me.dion.mygriboedov

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.Toast
import me.dion.mygriboedov.quiz.QuizManager

class TitleScreenActivity : AppCompatActivity() {
    private var startButton: Button? = null

    @SuppressLint("ShowToast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startButton = findViewById(R.id.startButton)

        startButton?.setOnClickListener {
            val quizManager: QuizManager = QuizManager()
            quizManager.loadQuestions()

            val question = quizManager.nextQuestion

            val intent: Intent = Intent(
                this,
                QuestionTemplateActivity::class.java
            )

            intent.putExtra("question", question)
            intent.putExtra("quizManager", quizManager)

            startActivity(intent)
        }
    }
}