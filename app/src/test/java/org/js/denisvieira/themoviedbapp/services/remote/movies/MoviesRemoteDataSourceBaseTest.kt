package org.js.denisvieira.themoviedbapp.services.remote.movies

import org.js.denisvieira.themoviedbapp.services.utils.SetupRemoteUtils
import org.mockito.InjectMocks
import org.mockito.Mock


/**
 * Unit tests for the implementation of {@link MoviesRemoteDataSourceImpl }
 */

open class MoviesRemoteDataSourceBaseTest : SetupRemoteUtils(){

    @InjectMocks
    lateinit var moviesRemoteDataSourceImpl: MoviesRemoteDataSourceImpl

    @Mock
    lateinit var moviesApiDataSource: MoviesApiDataSource


}

