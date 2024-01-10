package com.example.classapplication.data

// firebase requires empty constructor
data class UserData(
    var userId: String? = null,
    var name: String? = null,
    var username: String? = null,
    var imageUrl: String? = null,
    var bio: String? = null,
    var following: List<String>? = null,
    var roles : List<String>? = null
)
{
    //convert to map for firebase
    fun toMap() = mapOf(
        "userId" to userId,
        "name" to name,
        "username" to username,
        "imageUrl" to imageUrl,
        "bio" to bio,
        "following" to following,
        "roles" to  roles
    )
}