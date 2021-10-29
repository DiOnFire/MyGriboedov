package me.dion.mygriboedov

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    public var CreateGameButton: Button? = null
    public var JoinGameButton: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        CreateGameButton = findViewById(R.id.createGameButton)
        JoinGameButton = findViewById(R.id.joinGameButton)

        CreateGameButton?.setOnClickListener {
            val intent: Intent = Intent(this, CreateGameActivity::class.java)
            startActivity(intent)
        }

        JoinGameButton?.setOnClickListener {
            val intent: Intent = Intent(this, JoinGameActivity::class.java)
            startActivity(intent)
        }
    }
}