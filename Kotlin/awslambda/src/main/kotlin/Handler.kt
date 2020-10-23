import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.RequestHandler

// 参考
// https://docs.aws.amazon.com/ja_jp/lambda/latest/dg/java-handler.html
// https://github.com/awsdocs/aws-lambda-developer-guide/tree/master/sample-apps/java-basic/src/main/java/example
class Handler : RequestHandler<Map<String, String>, String> {
    override fun handleRequest(input: Map<String, String>?, context: Context?): String {
        println(input.toString())
        return "lambda success. version2."
    }
}