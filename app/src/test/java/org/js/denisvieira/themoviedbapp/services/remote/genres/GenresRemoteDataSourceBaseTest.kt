package org.js.denisvieira.themoviedbapp.services.remote.genres

import org.js.denisvieira.themoviedbapp.services.utils.SetupRemoteUtils
import org.mockito.InjectMocks
import org.mockito.Mock


/**
 * Unit tests for the implementation of {@link GenresRemoteDataSourceImpl }
 */

open class GenresRemoteDataSourceBaseTest : SetupRemoteUtils(){

    @InjectMocks
    lateinit var genresRemoteDataSourceImpl: GenresRemoteDataSourceImpl

    @Mock
    lateinit var genresApiDataSource: GenresApiDataSource



}

