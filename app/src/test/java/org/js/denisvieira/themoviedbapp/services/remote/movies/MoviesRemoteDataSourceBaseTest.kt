package org.js.denisvieira.themoviedbapp.services.remote.movies

import br.com.stant.obras.service.remote.utils.SetupRemoteUtils
import org.mockito.InjectMocks
import org.mockito.Mock


/**
 * Unit tests for the implementation of {@link ConstructionSiteRemoteDataSourceImpl }
 */

open class MoviesRemoteDataSourceBaseTest : SetupRemoteUtils(){

    @InjectMocks
    lateinit var moviesRemoteDataSourceImpl: MoviesRemoteDataSourceImpl

    @Mock
    lateinit var moviesApiDataSource: MoviesApiDataSource



}

