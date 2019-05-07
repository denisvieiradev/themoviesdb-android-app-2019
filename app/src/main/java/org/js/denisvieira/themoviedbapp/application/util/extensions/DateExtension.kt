package org.js.denisvieira.themoviedbapp.application.util.extensions

import java.text.SimpleDateFormat
import java.util.*

/**
 * Pattern: yyyy-MM-dd
 */

fun Date.formatToServerDateDefaults(): String? {
    val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    return sdf.format(this)
}

