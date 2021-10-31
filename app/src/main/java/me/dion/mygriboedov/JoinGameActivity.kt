package me.dion.mygriboedov

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import me.dion.mygriboedov.core.client.core.Client

class JoinGameActivity : AppCompatActivity() {
    private var ConnectToGameButton: Button? = null
    private var GameIdInput: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join_game)

        ConnectToGameButton = findViewById(R.id.connectButton)
        GameIdInput = findViewById(R.id.gameCodeInput)

        ConnectToGameButton?.setOnClickListener {
            val client: Client = Client("192.168.0.169", "test")
        }
    }
}