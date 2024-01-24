package com.example.classapplication.data
data class ServicesData(
    val serviceId: Any? = null,
    val userId: String? = null,
    val username: String? = null,
    val userImage: String? = null,
    val serviceImage: String? = null,
    val serviceDescription: String? = null,
    val time: Long? = null,
) {
    fun toMap() = mapOf(
        "serviceId" to serviceId,
        "userId" to userId,
        "username" to username,
        "userImage" to userImage,
        "serviceImage" to serviceImage,
        "serviceDescription" to serviceDescription,
        "time" to time
    )
}