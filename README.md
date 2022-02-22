# The Movies Db demo by Refaat

This demo provides an easy way to find and browse the best movies to watch, data provided by <a href='https://www.themoviedb.org/'>The Movie Db API.</a>

## Get The APK:

<a href='theDebugAPK/app-debug.apk'>
<img alt='Get The APK' src="screenshots/apk-file.png" height="100" /></a>

## The app tech:

- Kotlin.
- Android SDK 32.
- Clear Arch (Use cases & MVVM) architecture pattern.
- Single source of truth pattern for Genre.
- Data caching using Room for Genre and Favorites.
- Dependency injection (Dagger Hilt).
- ViewModel.
- Coroutines Flow.
- LiveDate.
- Paging 3.
- Retrofit.
- Glide.
- Navigation Component.
- SafeArgs.
- Material design.
- ViewBinding.

# How to run the app

- Clone the repository to Android Studio.
- Register at <a href='https://www.themoviedb.org/documentation/api'>The Movie Db</a> to get your API_KEY.
- Put the following line to your gradle.properties file".

```bash
API_KEY="<YOUR_API_KEY>"
```

- Sync the run :)

## App Screenshots

<p float="left">
<img src="screenshots/sc1.png" height="400" alt="Screenshot"/> 
<img src="screenshots/sc2.png" height="400" alt="Screenshot"/> 
<img src="screenshots/sc3.png" height="400" alt="Screenshot"/> 
<img src="screenshots/sc4.png" height="400" alt="Screenshot"/> 
</p>

## Roadmap

- Add Unit tests.
- Add Integration tests.
- Add End-to-end tests.

## License

[MIT](https://choosealicense.com/licenses/mit/)