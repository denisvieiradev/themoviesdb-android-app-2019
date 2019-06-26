package org.js.denisvieira.themoviedbapp.domain.usecases.movies

import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(
    GetMovieDetailUseCaseActionTest::class,
    GetUpcomingMoviesUseCaseActionTest::class,
    SearchMoviesUseCaseActionTest::class
)
class MoviesUseCasesSuiteTest