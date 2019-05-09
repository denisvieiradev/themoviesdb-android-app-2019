package org.js.denisvieira.themoviedbapp.support

import org.js.denisvieira.themoviedbapp.pages.Page

inline fun <reified T : Page> createPage(): T {
    val page = T::class.constructors.first().call()
    page.checkTrait()
    return page


}