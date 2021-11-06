package me.dion.mygriboedov

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import me.dion.mygriboedov.core.client.core.Client
import me.dion.mygriboedov.core.client.exception.ServerNotFoundException
import me.dion.mygriboedov.core.server.GameIDGenerator
import java.lang.Exception
import java.net.InetAddress

class JoinGameActivity : AppCompatActivity() {
    private var client: Client? = null
    private var ConnectToGameButton: Button? = null
    private var GameIdInput: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join_game)

        ConnectToGameButton = findViewById(R.id.connectButton)
        GameIdInput = findViewById(R.id.gameCodeInput)

        ConnectToGameButton?.setOnClickListener {
            if (GameIdInput?.text.toString().isNotEmpty()) {
                Thread(
                    Runnable {
                        val extras: Bundle? = intent.extras
                        client = Client(
                            InetAddress.getByName(GameIDGenerator.ipDecrypt(GameIdInput?.text.toString())),
                            extras?.getString("nickname")
                        )
                        intent.putExtra("client", client) // Adding client variable as "global"

                        val connect: Boolean? = client?.connect()

                        if (!connect!!) {
                            val dialog: InvalidCodeIDAlert = InvalidCodeIDAlert()
                            dialog.show(supportFragmentManager, "invalidCodeIDAlert")
                        } else {
                            val intent: Intent = Intent(applicationContext, WaitingForStartActivity::class.java)
                            startActivity(intent)
                        }
                    }
                ).start()
            } else {
                val dialog: InvalidCodeIDAlert = InvalidCodeIDAlert()
                dialog.show(supportFragmentManager, "invalidCodeIDAlert")
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        intent.removeExtra("client")
    }
}