# The Movies DB APP

## Simple app that consuming [TMDB Api](https://www.themoviedb.org/documentation/api)

[The Movie DB Android App - Apk](https://drive.google.com/open?id=xx)

### Main features:
- List Upcoming Movies
- Pagination on Upcoming Movie List
- Search Movies
- Movie Details

### The Project 
- [Model-View-ViewModel - MVVM](https://www.toptal.com/android/android-apps-mvvm-with-clean-architecture)

- Communications between View <-> ViewModel are made using DataBinding and LiveData. 
- Using [Android Jetpack Navigation](https://developer.android.com/jetpack) library to download and cache images.
- Using [Glide](https://github.com/bumptech/glide) library to download and cache images.
- Using [Retrofit](https://square.github.io/retrofit/) library to create interfaces with MarvelApi.
- Using [Mockito](https://github.com/mockito/mockito) library to create tests.
- Using [Gson](https://github.com/google/gson) library to make TMDB Api json response parse.
- Handling network connection lost (With retry button).
- Avoiding lost data when configurations changes.
- Movies list pagination with UI feedback\'s.
- Movie details with comics, events, stories and series participation\'s.
- Custom transitions between list and movie details.
- Movie search by name.

# Screens

 Listing movies             |  Movie details             | Searching Movie                    
:-------------------------:|:-------------------------:|:-------------------------:|:-------------------------
<img src="gifs/listing.gif" width="180" height="320">  |    <img src="gifs/movie_details.gif" width="180" height="320">  |    <img src="gifs/searching.gif" width="180" height="320">
