    package org.js.denisvieira.themoviedbapp.application.modules

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import org.js.denisvieira.themoviedbapp.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findNavController(R.id.my_nav_host_fragment).navigate(R.id.selectMovieFragment)

    }
}
