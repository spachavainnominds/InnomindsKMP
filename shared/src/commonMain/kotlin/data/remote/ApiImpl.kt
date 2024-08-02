package data.remote

import data.remote.models.ApiResponse
import data.remote.models.Post
import data.remote.utils.ApisUrls
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.http.isSuccess
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray

class ApiImpl : ApiRepo {

    private val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json(
                Json {
                    prettyPrint = true
                    ignoreUnknownKeys = true
                    isLenient = true
                }
            )
        }
    }

    val json = Json {
        ignoreUnknownKeys = true
        isLenient = true
        prettyPrint = true
    }

    override suspend fun getData(): ApiResponse<List<Post>> {
        val response = client.get("https://jsonplaceholder.typicode.com/posts")

        val body1: JsonArray = response.body<JsonArray>()
        val posts: List<Post> = json.decodeFromString(body1.toString())
//        val posts: List<Post> = Post.formJson(body1)
        println("getData =========== ${Post.formJson(body1)}")

        return if (!response.status.isSuccess()) {

            ApiResponse(success = false, data = null, error = response.status.description)

        } else {
            ApiResponse(success = true, data = posts, error = "")
        }
    }

    override suspend fun login(userName: String, password: String): ApiResponse<String> {

        val response = ApiServiceClient.httpClient.get(ApisUrls.EndPointUrl.plus("posts"))

        val body: List<Post> = response.body<List<Post>>()

       /* return if (!response.status.isSuccess()) {

//            ApiResponse(success = true, data = body, error = "")

        } else {
//            ApiResponse(success = false, data = null, error = response.status.description)
        }

*/
        return ApiResponse(success = true, data = "Success", error = "")
    }

    // TODO -- Write some other methods to invoke other apis

}