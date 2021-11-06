package me.dion.mygriboedov

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class MultiplayerActivity : AppCompatActivity() {
    private var createGameButton: Button? = null
    private var joinGameButton: Button? = null
    private var nicknameInput: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_multiplayer)

        createGameButton = findViewById(R.id.createGameButton)
        joinGameButton = findViewById(R.id.joinGameButton)
        nicknameInput = findViewById(R.id.nicknameInput)

        createGameButton?.setOnClickListener {
            if (nicknameInput?.text.toString().isNotEmpty()) {
                val intent: Intent = Intent(applicationContext, CreateGameActivity::class.java)
                intent.putExtra("nickname", nicknameInput?.text.toString())
                startActivity(intent)
            } else {
                val dialog: EmptyNicknameAlert = EmptyNicknameAlert()
                dialog.show(supportFragmentManager, "emptyNicknameAlert")
            }
        }

        joinGameButton?.setOnClickListener {
            if (nicknameInput?.text.toString().isNotEmpty()) {
                val intent: Intent = Intent(applicationContext, JoinGameActivity::class.java)
                intent.putExtra("nickname", nicknameInput?.text.toString())
                startActivity(intent)
            } else {
                val dialog: EmptyNicknameAlert = EmptyNicknameAlert()
                dialog.show(supportFragmentManager, "emptyNicknameAlert")
            }
        }
    }
}