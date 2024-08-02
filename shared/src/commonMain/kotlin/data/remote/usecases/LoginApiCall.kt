package data.remote.usecases

import data.remote.ApiImpl
import data.remote.models.ApiResponse
import data.remote.models.Post


object LoginApiCall {

    private val repo = ApiImpl()

    suspend operator fun invoke(userName: String, password: String): String {

        try {
           /* if (userName.isEmpty() || password.isEmpty()) {
                return ApiResponse(false, null, "Username or password is empty")
            } else {
                val response = repo.login(userName, password)
                return response
            }*/
//            val response = repo.login(userName, password)
            val response = repo.getData()
            return ""
        } catch (e: Exception) {
//            ApiResponse(false, null, e.message)
            println("LoginApiCall :: ApiServiceClient Failed == ${e.message}")
            return e.message ?: ""
        }
    }

    suspend operator fun invoke(): ApiResponse<List<Post>> {

        try {
            val response = repo.getData()
            println("LoginApiCall :: ApiServiceClient Success == ${response.success}")
            return response
        } catch (e: Exception) {
            println("LoginApiCall :: ApiServiceClient Failed == ${e.message}")
            return ApiResponse(false, null, e.message)
        }
    }
}