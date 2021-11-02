package me.dion.mygriboedov

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import java.lang.IllegalStateException

class TooFewParticipantsAlert : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setTitle("Ошибка!")
                .setMessage("Слишком мало участников для начала игры!")
                .setPositiveButton("Ок") {
                        dialog, id -> dialog.cancel()
                }
            builder.create()
        } ?: throw IllegalStateException("Empty activity!")
    }
}