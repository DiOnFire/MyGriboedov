package me.dion.mygriboedov

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.annotation.RequiresApi
import me.dion.mygriboedov.core.server.GameIDGenerator
import me.dion.mygriboedov.core.server.exception.NoInternetConnectionException

class CreateGameActivity : AppCompatActivity() {
    public var GameIdView: TextView? = null
    private var key: String? = null

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_game)

        GameIdView = findViewById(R.id.gameIdView)

        key = try {
            GameIDGenerator.ipEncrypt(applicationContext)
        } catch (e: NoInternetConnectionException) {
            e.message;
        }

        GameIdView?.text = key
    }
}