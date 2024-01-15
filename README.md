# DailyPulse
Educational repo offered as material in the course Kotlin Multiplatform.
The course aims to teach the state-of-art KMP development. 

News App includes a native Android and a native iOS apps, where the business logic and infrastructure is shared in a KMP module.

News App is using the news API to fetch, cache and display the top US business articles. It also contains a screen to display the list of news sources we use to fetch the articles from.
Finally, it contains third screen to display information regarding the user's device.

## Tech Stack
It is a prototype app based on the following technologies and patterns:

1. Clean Architecture
2. MVI
3. Ktor
4. SQL Delight
5. Koin
6. Jetpack Compose
7. Swift UI

## Architectural diagram

The UI/Framework layers reside in the native apps, while everything from the View Model up to Data layer is in the common KMP module.

![Untitled Diagram drawing](https://github.com/smsameer88/Daily-News-App/tree/main/androidApp/src/main/java/com/sameer/dailypulse/screens/mvvm-architecture.png)

## Architectural Layers

<img width="531" alt="Screenshot 2023-12-04 at 18 19 53" src="https://github.com/smsameer88/Daily-News-App/tree/main/androidApp/src/main/java/com/sameer/dailypulse/screens/architecture.png">
