package com.example.demo.config

import com.amazonaws.services.sqs.AmazonSQSAsync
import org.springframework.cloud.aws.core.env.ResourceIdResolver
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SqsConfig {
    @Bean
    fun queueMessagingTemplate(
        amazonSQSAsync: AmazonSQSAsync?
    ): QueueMessagingTemplate? {
//        return QueueMessagingTemplate(amazonSQSAsync, object : ResourceIdResolver {
//            override fun resolveToPhysicalResourceId(logicalResourceId: String?): String {
//                return "sqs-app"
//            }
//        })
        return QueueMessagingTemplate(amazonSQSAsync)
    }
}