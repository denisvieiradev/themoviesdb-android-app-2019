package br.com.stant.stant_android_occurrences.support

import br.com.stant.stant_android_occuPagerrences.new.pages.Page

inline fun <reified T : Page> createPage(): T {
    val page = T::class.constructors.first().call()
    page.checkTrait()
    return page


}