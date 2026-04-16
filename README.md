# iStream – Android App

## Overview
iStream is an Android application where users can log in, add YouTube video links, save them to a personal playlist and play videos directly from the app. The app uses Room Database to store user details and playlist data locally.

---

## Features

- User Signup and Login  
- Data stored using Room Database  
- Play YouTube videos inside the app  
- Add video URLs to personal playlist  
- View saved playlist using RecyclerView  
- User specific playlist (each user sees only their data)  
- Click playlist item to load video on home screen  
- Logout functionality  

---

## Technologies Used

- Java  
- Android Studio  
- Room Database  
- RecyclerView  
- WebView (YouTube embed)  

---

## App Flow

1. User signs up with full name, username and password  
2. User logs in using credentials  
3. Homepage opens where user can:
   - Enter YouTube URL  
   - Play video  
   - Add to playlist  
4. Playlist page shows saved videos for that user  
5. Clicking a playlist item loads the video back on the home screen  
6. User can logout and return to login screen  

---

## Database Structure

### User Table
- id (Primary Key)  
- fullName  
- username  
- password  

### Playlist Table
- id (Primary Key)  
- videoUrl  
- userId (Foreign key reference to User)  

---

## Key Concepts Used

- Room Database for local storage  
- DAO for database operations  
- RecyclerView for dynamic list display  
- Intent for passing data between screens  
- WebView for playing YouTube videos  


---

## Project Structure (Important Files)

- `MainActivity.java` → Login logic  
- `Signuppage.java` → User registration  
- `Homepage.java` → Main functionality (play, add, navigation)  
- `PlaylistPage.java` → Displays playlist  
- `PlaylistAdapter.java` → RecyclerView adapter  
- `user.java` → User entity  
- `Playlist.java` → Playlist entity  
- `UserDao.java` → User database operations  
- `PlaylistDao.java` → Playlist operations  

---



