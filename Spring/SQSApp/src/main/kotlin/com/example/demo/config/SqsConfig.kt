package com.example.demo.config

import com.amazonaws.services.sqs.AmazonSQSAsync
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.cloud.aws.messaging.config.SimpleMessageListenerContainerFactory
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor

import org.springframework.core.task.AsyncTaskExecutor







@Configuration
class SqsConfig {
    companion object {
        const val QUEUE_SQS_APP = "sqs-app"
        const val QUEUE_CUSTOMER = "customer"
    }

    @Bean(name = ["sqsAppQueueMessaging"])
    fun queueMessagingTemplate(
        amazonSQSAsync: AmazonSQSAsync?
    ): QueueMessagingTemplate {
        val queueMessagingTemplate = QueueMessagingTemplate(amazonSQSAsync)
        queueMessagingTemplate.setDefaultDestinationName(QUEUE_SQS_APP)
        return queueMessagingTemplate
    }

    @Bean(name = ["customerQueueMessaging"])
    fun customerQueueMessagingTemplate(
        amazonSQSAsync: AmazonSQSAsync?
    ): QueueMessagingTemplate {
        val queueMessagingTemplate = QueueMessagingTemplate(amazonSQSAsync)
        queueMessagingTemplate.setDefaultDestinationName(QUEUE_CUSTOMER)
        return queueMessagingTemplate
    }

//    @Bean
//    fun simpleMessageListenerContainerFactory(amazonSQS: AmazonSQSAsync?): SimpleMessageListenerContainerFactory? {
//        val factory = SimpleMessageListenerContainerFactory()
//        factory.setAmazonSqs(amazonSQS)
//        factory.setMaxNumberOfMessages(10)
//        factory.setTaskExecutor(getAsyncExecutor())
//        return factory
//    }
//
//    fun getAsyncExecutor(): AsyncTaskExecutor? {
//        val executor = ThreadPoolTaskExecutor()
//        executor.corePoolSize = 2
//        executor.maxPoolSize = 4
//        executor.setQueueCapacity(3)
//        executor.setThreadNamePrefix("threadPoolExecutor-SimpleMessageListenerContainer-")
//        executor.initialize()
//        return executor
//    }
}