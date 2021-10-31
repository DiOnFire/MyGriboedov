package me.dion.mygriboedov

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ScrollView
import android.widget.TextView
import androidx.annotation.RequiresApi
import me.dion.mygriboedov.core.server.GameIDGenerator
import me.dion.mygriboedov.core.server.core.Server
import me.dion.mygriboedov.core.server.exception.NoInternetConnectionException

class CreateGameActivity : AppCompatActivity() {
    private var gameIdView: TextView? = null
    private var key: String? = null
    private var membersView: ScrollView? = null
    private var startGameButton: Button? = null

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_game)

        gameIdView = findViewById(R.id.gameIdView)
        membersView = findViewById(R.id.membersView)
        startGameButton = findViewById(R.id.startGameButton)

        key = try {
            GameIDGenerator.ipEncrypt(applicationContext)
        } catch (e: NoInternetConnectionException) {
            e.message;
        }

        // Server init

        val server: Server = Server(GameIDGenerator.getLocalIP(applicationContext))
        server.startServer()

        gameIdView?.text = key

        startGameButton?.setOnClickListener {
            server.interruptConnections()
        }
    }

    fun getMemberListFromServer() {
        var thread: Thread = Thread() {

        }
    }
}