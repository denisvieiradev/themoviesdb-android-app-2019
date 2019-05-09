package org.js.denisvieira.themoviedbapp.support.fixtures

import org.js.denisvieira.themoviedbapp.support.models.UserLogin

object Seeds {
    const val DEFAULT_EMAIL    = "stant@stant.com.br"
    const val WRONG_EMAIL      = "xwrong@gmail.com.br"
    const val WRONG_PASSWORD   = "stantxXXx231"
    const val DEFAULT_PASSWORD = "stant123"
    const val EMPTY_DATA       = ""

    object UserLogin {
        val default = UserLogin(email = DEFAULT_EMAIL, password = DEFAULT_EMAIL)
        val complete = UserLogin(email = DEFAULT_EMAIL, password = DEFAULT_PASSWORD)
        val withoutData = UserLogin(email = EMPTY_DATA, password = EMPTY_DATA)
        val wrongEmail = UserLogin(email = WRONG_EMAIL, password = DEFAULT_PASSWORD)
        val wrongPassword = UserLogin(email = DEFAULT_EMAIL, password = WRONG_PASSWORD)
        val withoutEmail = UserLogin(email = EMPTY_DATA, password = DEFAULT_PASSWORD)
        val withoutPassword = UserLogin(email = DEFAULT_EMAIL, password = EMPTY_DATA)
    }

}