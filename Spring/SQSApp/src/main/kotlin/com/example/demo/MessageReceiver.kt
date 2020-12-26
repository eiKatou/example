package com.example.demo

import org.springframework.cloud.aws.messaging.listener.SqsMessageDeletionPolicy
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener
import org.springframework.stereotype.Component
import java.lang.RuntimeException

@Component
class MessageReceiver {
    @SqsListener(value = ["sqs-app"], deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS)
    fun queueListener(message: String?) {
        println(message)
        // deletionPolicyの設定により、例外時はメッセージが削除されない。
//        throw RuntimeException("test exception.")
    }
}