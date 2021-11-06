package me.dion.mygriboedov

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ScrollView
import android.widget.TextView
import androidx.annotation.RequiresApi
import me.dion.mygriboedov.core.client.core.Client
import me.dion.mygriboedov.core.server.GameIDGenerator
import me.dion.mygriboedov.core.server.core.Server
import me.dion.mygriboedov.core.server.exception.NoInternetConnectionException
import java.net.InetAddress
import kotlin.concurrent.thread

class CreateGameActivity : AppCompatActivity() {
    private var server: Server? = null
    private var client: Client? = null
    private var test: TextView? = null
    private var broadcast: Button? = null
    private var connections: Int = 0
    private var gameIdView: TextView? = null
    private var key: String? = null
    private var membersView: TextView? = null
    private var startGameButton: Button? = null

    @SuppressLint("SetTextI18n")
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_game)

        gameIdView = findViewById(R.id.gameIdView)
        membersView = findViewById(R.id.connectionCountView)
        startGameButton = findViewById(R.id.startGameButton)
        broadcast = findViewById(R.id.sendBroadcast)
        test = findViewById(R.id.testView)

        key = try {
            GameIDGenerator.ipEncrypt(applicationContext)
        } catch (e: NoInternetConnectionException) {
            e.message;
        }

        val intent: Intent = Intent(applicationContext, CreateGameActivity::class.java)

        // Server init

        server = Server(GameIDGenerator.getLocalIP(applicationContext))
        server?.startServer()

        intent.putExtra("server", server) // Adding server variable as "global"

        // Client init

        Thread(
            Runnable {
                initClient()
                client?.connect()
            }
        ).start()

        Thread(
            Runnable {
                while (!server?.isInterruptedConnections!!) {
                    membersView?.text = "Игроков: " + server?.connectionsSize
                    test?.text = server?.test
                }
            }
        ).start()

        gameIdView?.text = key

        startGameButton?.setOnClickListener {
            if (server?.connectionsSize!! < 2) {
                val dialog: TooFewParticipantsAlert = TooFewParticipantsAlert()
                dialog.show(supportFragmentManager, "tooFewParticipantsAlert")
            } else {
                server?.interruptConnections()
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun initClient() {
        val extras: Bundle? = intent.extras
        client = Client(InetAddress.getByName(GameIDGenerator.getLocalIP(applicationContext)), extras?.getString("nickname"))
        intent.putExtra("client", client) // Adding client variable as "global"
    }

    override fun onBackPressed() {
        super.onBackPressed()
        intent.removeExtra("client")
        intent.removeExtra("server")
        server?.closeServer()
    }
}