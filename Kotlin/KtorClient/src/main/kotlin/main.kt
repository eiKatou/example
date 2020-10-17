import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.coroutines.runBlocking

fun main(args: Array<String>) {
    // curl "https://httpbin.org/get"
    callGetAPI()
    callGetAPI2()
    callGetAPI3()
    callGetAPI4()
    // $ curl -H "content-type: application/json" -X POST -d'{"data1":"dog", "data2":"cat"}' http://httpbin.org/post
    callPostAPI()
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

        val message :HttpBinGetResponse = client.get("https://httpbin.org/get")

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

        val message :HttpBinGetResponse = client.request {
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

        val responseObj = Gson().fromJson(content, HttpBinGetResponse::class.java)
        println(responseObj.headers["Host"])

        client.close()
    }
}

fun callGetAPI4() {
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

        try {
            val message: HttpBinGetResponse = client.get("https://httpbin.org/status/500")

            println(message.args)
            println(message.headers["Host"])
            println(message.origin)
            println(message.url)
        } catch (e :ServerResponseException) {
            println("status code:${e.response?.status?.value}")
        }

        client.close()
    }
}

data class HttpBinGetResponse(
    val args: Map<String, String>,
    val headers: Map<String, String>,
    val origin: String,
    val url: String)

fun callPostAPI() {
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

        val message = client.post<HttpBinPostResponse> {
            url("http://httpbin.org/post")
            contentType(ContentType.Application.Json)
            body = HttpBinPostBody(
                data1 = "dog",
                data2 = "cat")
        }

        println(message)
        println("data1:${message.json["data1"]}, data2:${message.json["data2"]}")

        client.close()
    }
}

data class HttpBinPostBody(val data1: String, val data2: String)
data class HttpBinPostResponse(
    val args: Map<String, String>,
    val headers: Map<String, String>,
    val origin: String,
    val data: String,
    val json: Map<String, String>,
    val url: String)