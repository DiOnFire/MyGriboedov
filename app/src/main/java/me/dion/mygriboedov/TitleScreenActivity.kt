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


            val toast: Toast = Toast.makeText(applicationContext, "Вопросы загружаются...", Toast.LENGTH_LONG)
            toast.setGravity(Gravity.BOTTOM, 0, 0);
            toast.show();

            intent.putExtra("quizManager", quizManager)

            val question = quizManager.nextQuestion

            val intent: Intent = Intent(
                applicationContext,
                QuestionTemplateActivity::class.java
            )

            intent.putExtra("question", question)

            startActivity(intent)
        }
    }
}