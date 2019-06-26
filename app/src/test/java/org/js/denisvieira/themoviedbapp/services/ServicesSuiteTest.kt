package org.js.denisvieira.themoviedbapp.services

import org.js.denisvieira.themoviedbapp.services.remote.RemoteDataSourceSuiteTest
import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(
    RemoteDataSourceSuiteTest::class
)
class ServicesSuiteTest