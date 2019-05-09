package org.js.denisvieira.themoviedbapp.application.util.extensions

import android.content.Context
import org.js.denisvieira.themoviedbapp.R
import java.text.SimpleDateFormat
import java.util.*

/**
 * Pattern: yyyy-MM-dd
 */

fun Date?.formatToServerDateDefaults(context: Context): String {
    if(this == null){
        return context.getString(R.string.invalid_date)
    }

    val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    return sdf.format(this)
}


