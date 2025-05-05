Ozinshe is an app designed to promote the Kazakh language, particularly among children. The app functions like a video streaming platform (similar to YouTube), but it focuses on offering Kazakh-language cartoons and series specifically tailored for young audiences.

Features
Streaming Platform for Kazakh Cartoons and Series: Users can browse and watch various Kazakh-language cartoons and series. This app aims to provide entertaining content for children while promoting the Kazakh language.

Search Functionality: Users can search for specific cartoons or series through an intuitive search interface.

Favorites: The app allows users to add their favorite shows to a personal list, making it easy to access content they enjoy.

Responsive Design: Optimized for mobile devices, ensuring a smooth user experience.

Technologies Used
Retrofit: For making API calls to fetch data.

Glide: For efficient loading of images and multimedia content.

JSON: For parsing the data received from the API.

JetBrains: Used for Android development in Jetpack Compose.

Espresso: For UI testing and validation.

Coroutines: For handling asynchronous tasks and ensuring smooth UI updates.

SwitchButton: For toggling between different states.

OkHttp3: For managing HTTP requests and responses.

Lifecycle: To manage UI-related data in a lifecycle-conscious way.

Requirements
Android Studio: This project is built using Android Studio. You can clone the repository and open it in Android Studio to run the app.

Installation
Clone the repository:

bash
Копировать
Редактировать
git clone https://github.com/Marielle-ux/Ozinshe.git
Open the project in Android Studio.

Sync the project with Gradle.

Build and run the project on your Android device or emulator.

Challenges Faced During Development
Search Functionality: Implementing the search feature required careful planning to ensure that the app could handle filtering content dynamically based on user input.

Favorites Fragment: Creating the favorites fragment that stores user preferences and integrates seamlessly with the other components of the app was a key challenge.

App Logic
The app’s structure and interaction follow an MVVM (Model-View-ViewModel) architecture, where:

Main Screen: Displays a catalog of content (cartoons, series).

Search Functionality: Allows users to search for specific content based on keywords.

Favorites Fragment: Lets users add and view their favorite series.

API Integration: Data is fetched from the server using Retrofit, and asynchronous calls are managed with Coroutines.

State Management: The app utilizes ViewModel and LiveData to manage and persist data during configuration changes (e.g., screen rotation).

License
This project is licensed under the MIT License - see the LICENSE file for details.
