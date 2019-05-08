package org.js.denisvieira.themoviedbapp.modules

import org.junit.Rule

open class BaseFragmentTest {

    @Rule
    @JvmField
    var grantPermissions = GrantPermissionRule.grant(
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
            android.Manifest.permission.READ_EXTERNAL_STORAGE,
            android.Manifest.permission.ACCESS_NETWORK_STATE,
            android.Manifest.permission.INTERNET,
            android.Manifest.permission.READ_PHONE_STATE)!!

}