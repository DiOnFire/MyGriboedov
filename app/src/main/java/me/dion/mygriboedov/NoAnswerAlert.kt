package me.dion.mygriboedov

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import java.lang.IllegalStateException

class NoAnswerAlert : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setTitle("Ошибка!")
                .setMessage("Выберите хотя бы один вариант ответа!")
                .setPositiveButton("Ок") {
                        dialog, id -> dialog.cancel()
                }
            builder.create()
        } ?: throw IllegalStateException("Empty activity!")
    }
}