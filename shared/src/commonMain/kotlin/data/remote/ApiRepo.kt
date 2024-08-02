package data.remote

import data.remote.models.ApiResponse
import data.remote.models.Post

interface ApiRepo {
    suspend fun login(userName: String, password: String): ApiResponse<String>
    suspend fun getData(): ApiResponse<List<Post>>
}