# TicTacToe

## Overview

This Tic Tac Toe game client provides both **offline** and **online** gameplay modes. 
The client includes several features such as playing against the PC, playing against another player, and tracking game history. 
It also allows users to play online against others after registering and logging in.

## Project Architecture: Clean Architecture

The project follows a **Clean Architecture** structure, which organizes the application into multiple layers: **Data**, **Domain**, and **UI**. Below is a breakdown of the project structure:

### 1. Data Layer
The Data Layer handles all external data sources, such as server connections and network requests.

#### Network
- **`NetworkService.java`**: Manages all network requests and interactions with the server. It handles sending and receiving data over the network.

#### Repository
- **`Repo.java`**: A repository class that provides a clean API for interacting with data from external sources (like network requests). It abstracts the implementation of the data-fetching logic.

- **`ConnectionService.java`**: Handles all connections to the server, manages network responses, and ensures communication between the client and server is stable and reliable.

### 2. Domain Layer
The Domain Layer contains the business logic and the core components of the application.

#### Model
- **`Record.java`**: Represents a record of a game session, including game data and results.
- **`Tile.java`**: Represents a game tile on the board.
- **`User.java`**: Represents a user, including their username, status, and related data.

#### Use Cases

The following are the use cases implemented in the application:

- **`GetRandomPositionUseCase`**: Retrieves a random position from the available positions.
- **`GetTileUseCase`**: Retrieves the tile from the position provided.
- **`GetXOImageUseCase`**: Fetches the image for either X or O.
- **`HashingUseCase`**: Handles password hashing.
- **`IsWinnerUseCase`**: Determines if a player has won the game.
- **`OnTimeStopped`**: Interface called when the timer ends.
- **`PlayBackgroundMusicUseCase`**: Handles the playback of background music.
- **`PlaySoundUseCase`**: Manages sounds during gameplay.
- **`RandomAvatarUseCase`**: Retrieves a random avatar for the user.
- **`RecordingUseCase`**: Handles the recording of match data.
- **`RecordPositionUseCase`**: Saves the positions for each player.
- **`ShowPopupUseCase`**: Manages the display of pop-up messages.
- **`TimerUseCase`**: Handles the timer functionality in the app.
- **`ToJsonUseCase`**: Converts data into JSON format.
- **`ValidationUseCase`**: Validates user input and game data.

### 3. UI Layer
The UI Layer is responsible for rendering the user interface, managing screen transitions, and handling user interaction.

#### Alert
- **`ConnectionLostPopup.java`**: Displays an alert when the client loses connection to the server.
- **`EndGameAlert.java`**: Displays a message when the game ends, showing the result (win/loss/fair).
- **`IncomingRequestDialog`**: Displays an incoming request dialog to the user.
- **`MessagePopup`**: Shows a message popup with a custom message.
- **`PromptUserName`**: Prompts the user to enter a username.
- **`PromptUserNames`**: Prompts the user to enter usernames for two players.
- **`EndReplayGameAlert`**: Displays an alert at the end of the game.

#### Screens
- **`SplashScreen.java`**: The initial screen that appears when the app starts.
- **`HomeScreen.java`**: The main menu where users can choose between Online Mode and Offline Mode.
- **`LoginScreen.java`**: The screen for logging in to the app.
- **`SignUpScreen.java`**: The screen for creating a new account.
- **`NewGameScreen.java`**: The screen where users can start a new game.
- **`OnlineScreen.java`**: The screen for online gameplay and viewing available online players.
- **`OfflineScreen.java`**: The screen for offline gameplay.
- **`HistoryScreen.java`**: Displays the user's game history.

#### Board Screens
- **`PcBoard.java`**: The board for playing against the PC.
- **`PlayerTwoBoard.java`**: The board for playing against another player locally.
- **`OnlineBoard.java`**: The board for online multiplayer games.

#### View
- **`RecordItem.java`**: A custom view for displaying individual game records in the history section.



## How to Use

### Play vs PC (Easy Level)
- Choose this option to play against the PC at an easy level.
- You can record the game board and forfeit if needed.
- When the game ends, a video alert will appear with the game status:
  - **Winner**
  - **Loser**
  - **Draw**

### Play vs Player Two
- This option allows you to play against another player locally on the same device.

### History
- View a list of all your offline games played, including the results.

---
### Online Mode

1. **Login Screen**  
   Selecting **Online Mode** will navigate you to the **Login Screen** where you can:
   - Register a new account
   - Log in with your existing credentials

2. **New Game Screen**  
   After logging in, you will be redirected to the **New Game Screen** where you can:
   - View your game history
   - Play against other online players
   -Shows all users who are currently online and available to play against you.

---

## How to Run

1. **Clone the Repository**:
   Clone the project to your local machine:
   ```bash
    git clone https://github.com/ahmedsaad207/TicTacToe.git

2. **Install Dependencies**: Ensure that all necessary dependencies are installed before running the app. This may include Java or other required libraries. You may need to set up a Java environment or install any specific dependencies listed in the project files.

3. **Run the Application**: Once dependencies are installed, navigate to the project directory and run the application using your IDE or through the terminal with the following command:


## Where Records are Saved
The application saves game records in different locations based on the operating system:  

### Windows
The game records are saved in the App-Local directory, typically located at:  
`C:\Users<YourUsername>\AppData\Local\XOGame\`

### macOS
The game records are saved in the **Application Support** folder, typically located at:  
`~/Library/Application Support/XOGame/`

## JAR Dependencies  
The project requires the following JAR files for proper functionality:  

**JSON** (version 1.0.4): A lightweight library for working with JSON data in Java.
File: json-1.0.4.jar  

## Server Repository

The server code for this project is available at the following GitHub repository:

[Server GitHub Repository](https://github.com/ahmedsaad207/TicTacToeServer)



## Contributers:

Ahmed Saad – comm.ahmedsaad@alexu.edu.eg  
Ali Kotb – Alikotb38@gmail.com  
Nour Agami – nuralquds123@gmail.com  
Yousef Adel – youssefadelfayad@gmail.com 


