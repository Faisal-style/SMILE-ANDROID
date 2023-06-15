# :lion: SMILE (Support and Mental Insight for Life Empowerment) (In Progress)

> _This README is written by faisal_

<p align="justify">
<img src="https://github-production-user-asset-6210df.s3.amazonaws.com/72845777/246027389-77389087-5d54-4d13-b643-903075874de0.png" width="190px" height=auto align="right" alt="Computador"/>
According to the Indonesia-National Adolescent Mental Health Survey 2022, 15.5 million (34.9%) adolescents experience mental health problems, and 2.45 million (5.5%) adolescents experience mental disorders. Out of that number, only 2.6% have accessed counseling services. Mental health has become a crucial issue for young generations lately. Many young people feel anxious about their circumstances but don't know who to talk to. In addition, anxiety and shame make it difficult to share their problems with others, fearing they may be seen as exaggerating. Therefore, our Capstone project aims to create and develop a mental health chatbot.

The main goal is to provide a platform for those who want to express their anxiety and receive an initial diagnosis of their mental state without the fear of being known by others.<br/>

This project is still in the development stage, and I myself still have a lot to learn in building this Android application using Jetpack Compose. So please understand if the structure in this project is still a bit random.<br/>

This application is 100% built using Jetpack Compose and implements a bit of the MVVM (Model-View-ViewModel) architecture (not fully implemented yet as I am still learning).

</p>

## Screenshots

<p>
<img src="https://github-production-user-asset-6210df.s3.amazonaws.com/72845777/246038001-e9610505-b8ee-4038-8a10-1654154a065c.gif" width="200px"/>
<img src="https://github.com/Faisal-style/SMILE-ANDROID/assets/72845777/085ec989-6040-49dc-ae7e-fb43ef0a4aaf" width="200px"/>
<img src="https://github-production-user-asset-6210df.s3.amazonaws.com/72845777/246039370-4274b259-ec63-4f1d-b9ba-108ead07ca2f.jpeg" width="200px"/>
<img src="https://github.com/Faisal-style/SMILE-ANDROID/assets/72845777/a49ae9ab-6a07-4151-af6f-c544dc56153a" width="200px"/>
</p>

## Requirements

- Android Studio Electric Eel or Later
- Internet Conection

## Getting Started

1. Clone the repository.
2. Please wait until the dependency installation process is complete.
3. Build and run the app on an emulator or physical device.

## Directory Structure

```terminal
.
├── app
│   ├── build.gradle
│   ├── proguard-rules.pro
│   ├── src
│   │   ├── androidTest
│   │   ├── main
│   │   │   ├── java/com/example/smiletryone
│   │   │   │   ├── component
│   │   │   │   ├── data
│   │   │   │   ├── di
│   │   │   │   ├── navigation
│   │   │   │   ├── repository
│   │   │   │   ├── screen
│   │   │   │   ├── util
│   │   │   │   ├── ui.theme
│   │   │   │   ├── viewmodel
│   │   │   │   └── MainActivity.kt
│   │   │   │   └── MainViewModel.kt
│   │   │   ├── res
│   │   │   └── AndroidManifest.xml
│   │   ├── test
│   │   └── ...
├── build.gradle
├── gradle
│   └── wrapper
│       ├── gradle-wrapper.jar
│       └── gradle-wrapper.properties
├── gradle.properties
├── gradlew
├── gradlew.bat
└── settings.gradle
```

The **_app_** directory contains all the source code and resources of the application. It contains a build.gradle file, which is used to configure the build process for the application, and a proguard-rules.pro file, which contains rules for ProGuard, a tool used to shrink and obfuscate code.

Within the **_src_** directory, there are two subdirectories: androidTest and main. The androidTest directory contains integration tests for the application on Android. The main directory contains the majority of the source code and resources for the application, and is further broken down into subdirectories:

- `component`: This directory contains constant values used throughout the application.
- `data`: This directory contains the data layer of the application, including data from remote and endpoint configuration.
- `di`: This directory contains the Dependency Injection setup for the application.
- `navigation`: This directory contains navigation in entire aplication.
- `repository`: This directory contains repository files that contain functions used for API requests.
- `screen`: This directory contains the user interface components of the application, including Composables.
- `util`: This directory contains constant values used throughout the application.
- `ui.theme`: This directory contains the ui component like color, font, and theme.
- `viewmodels`: This directory contains the viewmodel that is used to manage the logic within the UI screen.
- `MainActivity.kt`: This file contains the implementation of the main activity for the application.
- `MainViewModel.kt`: This file contains the implementation of the main view model for the application.

The project also contains other files and directories at the root level, including the build.gradle file, which is used to configure the build process for the entire project, the gradle directory, which contains files related to the Gradle build system, and the gradlew and gradlew.bat scripts, which are used to run Gradle commands. The gradle.properties file contains properties used by the Gradle build system, and the settings.gradle file is used to configure the Gradle settings for the project.

## Features

- [x] New Chat
- [x] Chat History
- [x] ChatBot smile
- [x] Delete conversation

## Acknowledgments

- Jetpack Compose
- Retrofit
- okHttp
- Coil for image and gift
- Dagger-Hilt
- Kotlin-Coroutines
- Data Store
- Splash API
- Pager Indicator Acompanist

## Contact Information

If you have any questions or suggestions related to this application, please contact me via email: faisalrezarahmat1@gmail.com.
