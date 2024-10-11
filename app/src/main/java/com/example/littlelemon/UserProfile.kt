package com.example.littlelemon

import kotlinx.serialization.Serializable


@Serializable
data class UserProfile(val firstName: String, val lastName: String, val email: String )
