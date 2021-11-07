package me.dion.mygriboedov

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import me.dion.mygriboedov.quiz.QuizManager

class GameOverActivity : AppCompatActivity() {
    private var quizManager: QuizManager? = null
    private var resultView: TextView? = null
    private var endGameButton: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_over)

        resultView = findViewById(R.id.resultView)
        endGameButton = findViewById(R.id.endGameButton)

        val bundle: Bundle? = intent.extras
        quizManager = bundle?.getSerializable("quizManager") as? QuizManager

        resultView?.text = quizManager?.score.toString()

        endGameButton?.setOnClickListener {
            quizManager?.reload()
            bundle?.remove("quizManager")
            val intent: Intent = Intent(
                this,
                TitleScreenActivity::class.java
            )
            startActivity(intent)
        }
    }

    override fun onBackPressed() {

    }
}