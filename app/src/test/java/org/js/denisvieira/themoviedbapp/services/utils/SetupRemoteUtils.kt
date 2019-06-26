package org.js.denisvieira.themoviedbapp.services.utils

import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.AfterClass
import org.junit.BeforeClass
import org.junit.Rule
import org.mockito.junit.MockitoJUnit

abstract class SetupRemoteUtils {

    @Rule
    @JvmField
    val rule = MockitoJUnit.rule()!!

    companion object {
        @BeforeClass
        @JvmStatic
        fun setupClass() {
            RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
            RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
        }

        @AfterClass
        @JvmStatic
        fun tearDownClass() {
            RxJavaPlugins.reset()
            RxAndroidPlugins.reset()
        }
    }
}