# GitHubDemo
## Friendly reminder: You should put your GitHub token on /asserts/token.txt.
 
🔧 Architecture
This project is based on MVVM (Model–View–ViewModel) architecture and follows clean architecture principles with separation of concerns.

View → ViewModel → UseCase → Repository → Model

- Jetpack Navigation is used to handle multi-page navigation and deep links.

- UseCase encapsulates business logic and improves function reusability and testability.

- Repository abstracts data sources (such as API, local DB) and provides a clean interface for domain logic.

- ViewModel manages UI state and communicates with UseCase.

- Hilt is used for Dependency Injection (DI), reducing boilerplate and improving modularity.

- Jetpack Compose builds a modern, declarative, and reactive UI layer.

🧱 Architecture Diagram

![image](https://github.com/user-attachments/assets/230df969-aeb3-42ff-aa57-e3f8547eb5ba)

📌 Technologies
| Layer | Technology |
| -------- | -------- |
|UI	       |       Jetpack Compose, Navigation|
|DI	       |       Hilt|
|Logic	    |       ViewModel, UseCase (Clean Pattern)|
|Data	     |       Repository Pattern, Retrofit, Room|
|State Mgmt|	      stateFlow, sharedFlow|
