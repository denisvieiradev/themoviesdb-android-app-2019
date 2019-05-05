package org.js.denisvieira.themoviedbapp.domain.usecases

import org.js.denisvieira.themoviedbapp.domain.usecases.genres.GenresUseCasesSuiteTest
import org.js.denisvieira.themoviedbapp.domain.usecases.movies.MoviesUseCasesSuiteTest
import org.js.denisvieira.themoviedbapp.services.remote.RemoteDataSourceSuiteTest
import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(
    GenresUseCasesSuiteTest::class,
    MoviesUseCasesSuiteTest::class
)
class UseCasesSuiteTest