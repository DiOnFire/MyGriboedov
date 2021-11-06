package me.dion.mygriboedov

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import me.dion.mygriboedov.core.client.core.Client

class WaitingForStartActivity : AppCompatActivity() {
    private var test: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_waiting_for_start)

        test = findViewById(R.id.buttonTest)

        test?.setOnClickListener {
            val client: Client? = intent.extras?.getSerializable("client") as? Client

            client?.writeThread?.sendMessage("NIGGA")
        }
    }
}