package me.dion.mygriboedov

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.annotation.RequiresApi
import me.dion.mygriboedov.core.server.GameIDGenerator

class CreateGameActivity : AppCompatActivity() {
    public var GameIdView: TextView? = null

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_game)

        GameIdView = findViewById(R.id.gameIdView)

        val key: String = GameIDGenerator.ipEncrypt(applicationContext)

        GameIdView?.text = key
    }
}