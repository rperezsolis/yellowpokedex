# Android Pokedex

###Description

The project simulates a login-flow that give you access to the "Pokedex", which is a RecyclerView that shows a list of all the pokemons retrieved by the [PokéApi](https://pokeapi.co/).

The project uses the [architecture components](https://developer.android.com/topic/libraries/architecture), from Android Jetpack, which are a set of android libraries that helps to build robust and maintainable apps.

To handle the network requests the [Retrofit](https://square.github.io/retrofit/) library is implemented. And, to handle with json conversions in a easy way, the project also uses [Moshi](https://github.com/square/moshi), a Json library for Kotlin and Java.

The project also uses [Glide](https://github.com/bumptech/glide), an image loading and caching library for android.

####Follow the next instructions to run the application:

- Download the project as a zip (be sure that you are in the master branch) and paste it in a location of your preference. 
- Unzip de project.
- Open the project with Android Studio. If you don't have Android Studio installed yet, you can found the instructions to install it [here](https://developer.android.com/studio/install).
- To run this project use a Pixel device with API level 28 (Android Pie). You can find the instructions to create a virtual device [here](https://developer.android.com/studio/run/managing-avds#createavd).
- To start the emulator open the AVD manager, double-click a virtual device or click Run and wait until the emulator loads. 
- Back to Android Studio, run the project on the emulator.
- Once inside the login page, in order to be able to see the main view, use these credentials to login:
  - user email: ashketchum@pokemon.com
  - password:   1234
- Enjoy the list.
