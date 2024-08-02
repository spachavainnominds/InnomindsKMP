package data.remote.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.jsonObject

@Serializable
//@OptIn(ExperimentalSerializationApi::class)
//@Serializer(forClass = Post::class)
data class Post(
    var userId: String,
    @SerialName(value = "id") var ids: String,
    var title: String,
    var body: String,
) {
    override fun toString(): String {
        return "Post(userId='$userId', ids='$ids', title='$title', body='$body')"
    }

    companion object {
        fun formJson(jsonArray: JsonArray): MutableList<Post> {
            val postList = mutableListOf<Post>()
            jsonArray.forEach { postObject ->
                postList.add(Post(userId = postObject.jsonObject["userId"].toString(),
                    postObject.jsonObject["id"].toString(),
                    postObject.jsonObject["title"].toString(),
                    postObject.jsonObject["body"].toString())
                )
            }
            return postList
        }
    }
}