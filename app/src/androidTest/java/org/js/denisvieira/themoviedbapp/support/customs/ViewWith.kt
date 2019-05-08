package br.com.stant.stant_android_occurrences.support.customs

import android.content.res.Resources
import android.view.View
import org.hamcrest.Description
import org.hamcrest.TypeSafeMatcher

class ViewWith(val id: Int) : TypeSafeMatcher<View>() {

    private var resources: Resources? = null
    var view: View? = null

    override fun describeTo(description: Description) {
        var idDescription = Integer.toString(id)
        if (resources != null) {
            try {
                idDescription = resources?.getResourceName(id)
            } catch (e: Resources.NotFoundException) {
                // No big deal, will just use the int value.
                idDescription = String.format("%s (resource name not found)", id)
            }

        }
        description.appendText("with id: " + idDescription)
    }

    override fun matchesSafely(view: View): Boolean {
        resources = view.resources
        var result = id == view.id
        if (result) {
            this.view = view
        }
        return result
    }


}