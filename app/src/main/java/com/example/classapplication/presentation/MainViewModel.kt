package com.example.classapplication.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.classapplication.common.USERS
import com.example.classapplication.data.Event
import com.example.classapplication.data.UserData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject
import com.google.firebase.storage.FirebaseStorage
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
/**
 * ViewModel class for the main screen of the application.
 *
 * This class is responsible for handling the business logic and data operations
 * related to the main screen of the application.
 *
 * @property auth The instance of FirebaseAuth used for authentication.
 * @property db The instance of FirebaseFirestore used for database operations.
 * @property storage The instance of FirebaseStorage used for storage operations.
 */
@HiltViewModel
class MainViewModel @Inject constructor(
    val auth: FirebaseAuth, val db: FirebaseFirestore, val storage: FirebaseStorage
) : ViewModel() {

    /**
     * ViewModel class for the main screen.
     *
     * This class holds the state of the main screen, including whether the user is signed in,
     * whether there is an ongoing operation in progress, the user data, and any popup notifications.
     */
    val signedIn = mutableStateOf(false)
    val inProgress = mutableStateOf(false)
    val userData = mutableStateOf<UserData?>(null)
    val popupNotification = mutableStateOf<Event<String>?>(null)

    /**
     * Initializes the MainViewModel.
     * - Checks if the user is signed in by accessing the current user from the authentication service.
     * - Updates the value of the `signedIn` LiveData based on whether the current user is null or not.
     * - If the current user is not null, retrieves the user data using the user's unique identifier (UID).
     */

    init {
        //sign out user
        auth.signOut()
        //Use the currentUser property to get the currently signed-in user.
        val currentUser = auth.currentUser
        signedIn.value = currentUser != null
        currentUser?.let {
            getUserData(it.uid)
        }

    }

    /**
     * Method called when a user signs up.
     *
     * This method is responsible for handling the signup process by taking the
     * username, email, and password as parameters.
     *
     * @param username The username of the user.
     * @param email The email of the user.
     * @param pass The password of the user.
     */
    // user exists
    //return an error message
    //create user
    //pass model data to firestore
    fun onSignup(username: String, email: String, pass: String) {
        //validate all fields are filled
        if (username.isEmpty() || email.isEmpty() || pass.isEmpty()) {
            popupNotification.value = Event("Please fill in all the fields")
            return
        }
        inProgress.value = true
        //check if username already exists if not create user
        db.collection(USERS).whereEqualTo("username", username).get()
            .addOnSuccessListener { documents ->
                if (documents.size() > 0) {
                    handleException(customMessage = "Username already exists")
                    inProgress.value = false
                } else {/*
                    *  function completes, either successfully or with an
                    * error, it triggers the addOnCompleteListener.
                    * This listener receives a Task object,
                    * which represents the result of the asynchronous operation.
                    * The Task object is passed to the lambda expression as the task parameter.
                     */
                    auth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            signedIn.value = true
                            createOrUpdateProfile(username = username)
                        } else {
                            handleException(task.exception, "Signup failed")
                        }
                        inProgress.value = false
                    }

                }
            }
            /**
             * Adds a failure listener to the current task.
             */
            .addOnFailureListener { }
    }



    fun onLogin(email: String, pass: String) {

        inProgress.value = true
        auth.signInWithEmailAndPassword(email, pass)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    signedIn.value = true
                    getUserData(auth.currentUser?.uid ?: "")
                    //test whether the user is signed in
                    //handleException(customMessage = "Login successful")
                } else {
                    handleException(task.exception, "Login failed")
                    inProgress.value = false
                }
            }
            .addOnFailureListener { exc ->
                handleException(exc, "Login failed")
                inProgress.value = false
            }
    }

    /**
     * Creates or updates the user profile with the provided information.
     *
     * @param name The name of the user. If null, the existing name will be used.
     * @param username The username of the user. If null, the existing username will be used.
     * @param bio The bio of the user. If null, the existing bio will be used.
     * @param imageUrl The URL of the user's profile image. If null, the existing image URL will be used.
     */

    private fun createOrUpdateProfile(
        name: String? = null,
        username: String? = null,
        bio: String? = null,
        imageUrl: String? = null
    ) {
        val uid = auth.currentUser?.uid
        val userData = UserData(
            userId = uid,
            name = name ?: userData.value?.name,
            username = username ?: userData.value?.username,
            bio = bio ?: userData.value?.bio,
            imageUrl = imageUrl ?: userData.value?.imageUrl,
            role = userData.value?.role,
            services = userData.value?.services
        )

        uid?.let { uid ->
            inProgress.value = true
            db.collection(USERS).document(uid).get().addOnSuccessListener {
                if (it.exists()) {
                    it.reference.update(userData.toMap()).addOnSuccessListener {
                        this.userData.value = userData
                        inProgress.value = false
                    }.addOnFailureListener {
                        handleException(it, "Profile update failed")
                        inProgress.value = false
                    }

                } else {
                    db.collection(USERS).document(uid).set(userData)
                    getUserData(uid)
                    inProgress.value = false
                }

            }.addOnFailureListener { exc ->
                handleException(exc, "cannot create user")
                inProgress.value = false
            }
        }

    }

    /**
     * Retrieves user data from the Firestore database based on the provided user ID.
     *
     * @param uid The ID of the user whose data needs to be retrieved.
     */
    fun getUserData(uid: String) {
        inProgress.value = true
        db.collection(USERS).document(uid).get().addOnSuccessListener {
            /**
             * Converts the Firestore document to a UserData object.
             *
             * @param it The Firestore document to convert.
             * @return The converted UserData object.
             */
            val user = it.toObject<UserData>()
            userData.value = user
            inProgress.value = false
            //popupNotification.value = Event("User data retrieved successfully")
        }
            /**
             * Adds a failure listener to the Firebase Firestore query.
             * This listener handles the exception and updates the inProgress value to false.
             *
             * @param exc The exception that occurred.
             */
            .addOnFailureListener { exc ->
                handleException(exc, "cannot get user data")
                inProgress.value = false
            }

    }

    /**
     * Handles exceptions and displays a notification message.
     *
     * @param exception The exception to handle. Defaults to null.
     * @param customMessage A custom message to display along with the exception. Defaults to an empty string.
     */
    fun handleException(exception: Exception? = null, customMessage: String = "") {
        exception?.printStackTrace()
        val errorMsg = exception?.localizedMessage ?: ""
        val message = if (customMessage.isEmpty()) errorMsg else "$customMessage: $errorMsg"
        popupNotification.value = Event(message)
    }

}