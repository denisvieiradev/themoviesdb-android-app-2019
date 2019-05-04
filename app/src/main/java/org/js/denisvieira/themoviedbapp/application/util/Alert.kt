package org.js.denisvieira.themoviedbapp.application.util

import android.content.Context
import androidx.appcompat.app.AlertDialog

fun showAlertErrorByStatusCode(message: String, context: Context) {
    val builder = AlertDialog.Builder(context)

    builder.setMessage(message.toInt())
            .setCancelable(false)
            .setPositiveButton("OK") { _, _ ->  }

    val alert = builder.create()
    alert.show()
}