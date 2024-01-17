package com.example.classapplication.data
data class ServicesData(
    val serviceId: String? = null,
    val userId: String? = null,
    val username: String? = null,
    val userImage: String? = null,
    val serviceImage: String? = null,
    val serviceDescription: String? = null,
    val time: Long? = null,
) {
    fun toMap() = mapOf(
        "userId" to userId,
        "username" to username,
        "imageUrl" to userImage,
        " ServiceDescription" to serviceDescription,
    )
}