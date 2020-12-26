package com.example.demo

import com.example.demo.config.SqsConfig
import org.springframework.cloud.aws.messaging.listener.SqsMessageDeletionPolicy
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener
import org.springframework.stereotype.Component
import java.lang.RuntimeException

@Component
class HelloMessageReceiver {
    @SqsListener(value = [SqsConfig.QUEUE_SQS_APP], deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS)
    fun queueListener(message: String?) {
        println(message)
        // deletionPolicyの設定により、例外時はメッセージが削除されない。
//        throw RuntimeException("test exception.")
    }
}