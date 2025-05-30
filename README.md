# EduConnect - Learning App

EduConnect is a modern Android learning app that connects students and teachers. It features a clean, intuitive UI with separate login flows for students and teachers.

## Features

- Welcome screen with app introduction
- Student login with email/mobile and password
- Teacher login with unique ID and password
- Home page with personalized welcome messages
- Different UI for students and teachers after login
- Dark/light theme toggle
- Firebase Authentication integration
- Firestore database for user profiles and roles
- Logout functionality with session management

## Screenshots

- Welcome Screen
- Student Login
- Teacher Login
- Student Home Page
- Teacher Home Page

## Setup Instructions

### Prerequisites

- Android Studio Arctic Fox (2021.3.1) or newer
- Kotlin 1.6.0 or newer
- Firebase account

### Firebase Setup

1. Go to the [Firebase Console](https://console.firebase.google.com/)
2. Create a new project (or use an existing one)
3. Add an Android app to your Firebase project
   - Use package name: `com.example.educonnect`
   - Register the app
4. Download the `google-services.json` file and place it in the `app/` directory
5. Enable Authentication in Firebase Console
   - Go to Authentication > Sign-in method
   - Enable Email/Password authentication
6. Create Firestore Database
   - Go to Firestore Database > Create database
   - Start in production mode or test mode
   - Choose a location close to your users

### Project Setup

1. Clone the repository
2. Open the project in Android Studio
3. Make sure you've added the `google-services.json` file to the `app/` directory
4. Sync the project with Gradle files
5. Build and run the app

## Firebase Database Structure

The app uses Firestore with the following structure:

```
/users
  /USER_ID
    - email: string
    - userType: "student" | "teacher"
    - uniqueId: string (for teachers)
    - name: string
    - ... other user data
```

## Creating Test Users

### Quick Method (Easiest)

1. **Run the app** in Android Studio
2. **Long press on the app logo** on the welcome screen
3. **Wait for the dialog** showing account creation results
4. **Use these credentials**:
   - **Teacher**: Unique ID: `teacher001`, Password: `password123`
   - **Student**: Email: `student@example.com`, Password: `password123`

### Manual Method via Firebase Console

1. Go to Firebase Console → Authentication → Users
2. Click "Add user"
3. For Students:
   - Email: `student@example.com`
   - Password: `password123`
4. For Teachers:
   - Email: `teacher001@educonnect.teacher`
   - Password: `password123`
5. Go to Firestore Database → users collection
6. Add documents with user data (see structure below)

### Test Credentials

After setting up, you can use these credentials:

**Student Login:**
- Email: `student@example.com`
- Password: `password123`

**Teacher Login:**
- Unique ID: `teacher001`
- Password: `password123`

### Creating Custom Teacher Accounts

To create additional teacher accounts:

```kotlin
// Example: Create a Math teacher
/*
val testSetup = TestDataSetup()
testSetup.createTestTeacher(
    uniqueId = "math001",
    password = "mathteacher123",
    name = "Sarah Johnson",
    subject = "Advanced Mathematics"
) { success, message ->
    println(message)
}
*/
```

### Creating Custom Student Accounts

```kotlin
// Example: Create a student
/*
val testSetup = TestDataSetup()
testSetup.createTestStudent(
    email = "alice@student.com",
    password = "student123",
    name = "Alice Wilson",
    grade = "12"
) { success, message ->
    println(message)
}
*/
```

## License

This project is licensed under the MIT License - see the LICENSE file for details.

## Acknowledgments

- Material Design Components
- Firebase Authentication and Firestore
- Kotlin Coroutines and Flow 

# Build the project
./gradlew build

# Install on connected device
./gradlew installDebug

# Run tests
./gradlew test 