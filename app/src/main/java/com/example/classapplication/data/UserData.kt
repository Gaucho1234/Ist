package com.example.classapplication.data
/**
 * Represents user data.
 *
 * @property userId The unique identifier of the user.
 * @property name The name of the user.
 * @property username The username of the user.
 * @property imageUrl The URL of the user's profile image.
 * @property bio The bio of the user.
 * @property Services The list of servicces that the user is has.
 */
// firebase requires empty constructor thus initalize to null

data class UserData(
    var userId: String? = null,
    var name: String? = null,
    var username: String? = null,
    var imageUrl: String? = null,
    var bio: String? = null,
    var role:List<Roles>? = null,
    var services: List<ServicesData>? = null
) {
    /**
     * Converts the UserData object to a map for Firebase.
     *
     * @return The map representation of the UserData object.
     */
    fun toMap() = mapOf(
        "userId" to userId,
        "name" to name,
        "username" to username,
        "imageUrl" to imageUrl,
        "bio" to bio,
        "role" to role,
        "services" to services
    )
}