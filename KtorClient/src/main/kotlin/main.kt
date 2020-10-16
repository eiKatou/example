import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.json.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.coroutines.runBlocking

fun main(args: Array<String>) {
    callGetAPI()
    callGetAPI2()
    callGetAPI3()
}

fun callGetAPI() {
    runBlocking {
        val client = HttpClient(CIO) {
            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.ALL
            }
            install(JsonFeature) {
                serializer = GsonSerializer {
                    // .GsonBuilder
                    serializeNulls()
                    disableHtmlEscaping()
                    setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                }
            }
        }

        val message :HttpBinResponse = client.get("https://httpbin.org/get")

        println(message.args)
        println(message.headers["Host"])
        println(message.origin)
        println(message.url)

        client.close()
    }
}

fun callGetAPI2() {
    runBlocking {
        val client = HttpClient(CIO) {
            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.ALL
            }
            install(JsonFeature) {
                serializer = GsonSerializer {
                    // .GsonBuilder
                    serializeNulls()
                    disableHtmlEscaping()
                    setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                }
            }
        }

        val message :HttpBinResponse = client.request {
            url("https://httpbin.org/get")
            method = HttpMethod.Get
            headers.append("User-Agent", "iPhone")
        }

        println(message.headers["User-Agent"])
        println(message.origin)
        println(message.url)

        client.close()
    }

}

fun callGetAPI3() {
    runBlocking {
        val client = HttpClient(CIO) {
            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.ALL
            }
        }

        val response = client.get<HttpResponse>("https://httpbin.org/get")

        println(response.status)
        val content = response.readText()
        println(content)

        val responseObj = Gson().fromJson(content, HttpBinResponse::class.java)
        println(responseObj.headers["Host"])

        client.close()
    }
}

data class HttpBinResponse(
    val args: Map<String, String>,
    val headers: Map<String, String>,
    val origin: String,
    val url: String)