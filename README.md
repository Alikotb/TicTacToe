# TicTacToe

## Overview

This Tic Tac Toe game client provides both **offline** and **online** gameplay modes. 
The client includes several features such as playing against the PC, playing against another player, and tracking game history. 
It also allows users to play online against others after registering and logging in.

## Project Structure

The project follows a **Clean Architecture** structure, which organizes the application into multiple layers: **Data**, **Domain**, and **UI**. Below is a breakdown of the project structure:

### 1. **Data Layer**
The **Data Layer** handles all external data sources, such as server connections and network requests.

- **Network**:
  - **`NetworkService.java`**: Manages all network requests and interactions with the server. It handles sending and receiving data over the network.
  
- **Repository**:
  - **`Repo.java`**: A repository class that provides a clean API for interacting with data from external sources (like network requests). It abstracts the implementation of the data-fetching logic.

- **`ConnectionService.java`**:
  - Handles all connections to the server, manages network responses, and ensures communication between the client and server is stable and reliable.

### 2. **Domain Layer**
The **Domain Layer** contains the business logic and the core components of the application.

- **Model**:
  - **`Record.java`**: Represents a record of a game session, including game data and results.
  - **`Tile.java`**: Represents a game tile on the board.
  - **`User.java`**: Represents a user, including their username, status, and related data.

- **Use Cases**:
  The **Use Cases** implement various business rules for different actions in the game:
  - **Timer**: Manages game timing.
  - **Playing Sounds**: Controls sound effects during gameplay.
  - **Getting Images and Avatars**: Fetches and manages images and user avatars.
  - **Recording**: Handles recording gameplay data.
  - **Showing Pop-ups**: Displays pop-up messages or alerts to the user.
  - **ToJSON Use Case**: Converts game data into JSON format for storage or transmission.
  - **Validation**: Validates user input and game data.
  - **Get Random Position**: Provides a random position for the game logic (e.g., placing tiles on the board).

### 3. **UI Layer**
The **UI Layer** is responsible for rendering the user interface, managing screen transitions, and handling user interaction.

- **Alert**:
  - **`ConnectionLostPopup.java`**: Displays an alert when the client loses connection to the server.
  - **`EndGameAlert.java`**: Displays a message when the game ends, showing the result (win/loss/tie).
  - **`Replay.java`**: Offers the option to replay a game after it ends.
  - Other pop-ups: Manages additional alerts and notifications.

- **Screens**:
  - **`SplashScreen.java`**: The initial screen that appears when the app starts.
  - **`HomeScreen.java`**: The main menu where users can choose between **Online Mode** and **Offline Mode**.
  - **`LoginScreen.java`**: The screen for logging in to the app.
  - **`SignUpScreen.java`**: The screen for creating a new account.
  - **`NewGameScreen.java`**: The screen where users can start a new game.
  - **`OnlineScreen.java`**: The screen for online gameplay and viewing available online players.
  - **`OfflineScreen.java`**: The screen for offline gameplay.
  - **`HistoryScreen.java`**: Displays the user's game history.
  - **Board Screens**:
    - **`PcBoard.java`**: The board for playing against the PC.
    - **`PlayerTwoBoard.java`**: The board for playing against another player locally.
    - **`OnlineBoard.java`**: The board for online multiplayer games.

- **View**:
  - **`RecordItem.java`**: A custom view for displaying individual game records in the history section.


## How to Run

1. **Clone the Repository**:
   Clone the project to your local machine:
   ```bash
    git clone https://github.com/ahmedsaad207/TicTacToe.git

2. **Install Dependencies**: Ensure that all necessary dependencies are installed before running the app. This may include Java or other required libraries. You may need to set up a Java environment or install any specific dependencies listed in the project files.

3. **Run the Application**: Once dependencies are installed, navigate to the project directory and run the application using your IDE or through the terminal with the following command:

Play vs PC (Easy Level):  
Choose this option to play against the PC at an easy level.  
Play vs Player Two:  
This option allows you to play against another player locally.  
History:  
View a list of all your offline games played, including the results.  

Playing vs PC Mode:  
There is only one mode available (Easy Level) where you can play against the PC.  
You can record the game board and forfeit if needed.  
When a game ends, a video alert will appear with the game status: whether there is a winner, loser, or a fair.  
Online Mode  
Selecting Online Mode will navigate you to the Login Screen.  

You can register a new account or log in with existing credentials.  

Once logged in, you will be redirected to the New Game Screen, where you can:  

View your game history.  
Play against other online players.  
Online Users:  

The Online Mode shows all users who are currently online and available to play.  

## Where Records are Saved
The application saves game records in different locations based on the operating system:  

**Windows**:  

The game records are saved in the App-Local directory, typically located at:
php
Copy
C:\Users\<YourUsername>\AppData\Local\XOGame\

**macOS**:

The game records are saved in the Application Support folder

Copy
~/Library/Application Support/XOGame/



## Server Repository

The server code for this project is available at the following GitHub repository:

[Server GitHub Repository](https://github.com/ahmedsaad207/TicTacToeServer)

## JAR Dependencies  
The project requires the following JAR files for proper functionality:  

**JSON** (version 1.0.4): A lightweight library for working with JSON data in Java.
File: json-1.0.4.jar  





## The following individuals contributed to this project:

Ahmed Saad – comm.ahmedsaad@alexu.edu.eg  
Ali Kotb – Alikotb38@gmail.com  
Nour Agami – nuralquds123@gmail.com  
Yousef Adel – youssefadelfayad@gmail.com  
