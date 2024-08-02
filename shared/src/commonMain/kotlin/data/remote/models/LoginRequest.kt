package data.remote.models

import kotlinx.serialization.Serializable

@Serializable
data class LoginRequest(val username: String, val password: String)