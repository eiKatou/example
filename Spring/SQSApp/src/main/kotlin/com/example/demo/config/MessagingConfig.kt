package com.example.demo.config

import com.amazonaws.services.sns.AmazonSNS
import com.amazonaws.services.sqs.AmazonSQSAsync
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.cloud.aws.messaging.config.SimpleMessageListenerContainerFactory
import org.springframework.cloud.aws.messaging.core.NotificationMessagingTemplate
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor
import org.springframework.core.task.AsyncTaskExecutor

@Configuration
class MessagingConfig {
    companion object {
        const val QUEUE_SQS_APP = "friend"
        const val NOTIFICATION_CUSTOMER = "customer-topic"
        const val QUEUE_CUSTOMER = "customer"
    }

    @Bean(name = ["friendQueueMessaging"])
    fun queueMessagingTemplate(
        amazonSQSAsync: AmazonSQSAsync?
    ): QueueMessagingTemplate {
        val queueMessagingTemplate = QueueMessagingTemplate(amazonSQSAsync)
        queueMessagingTemplate.setDefaultDestinationName(QUEUE_SQS_APP)
        return queueMessagingTemplate
    }

    @Bean(name = ["customerNotificationMessaging"])
    fun customerNotificationMessagingTemplate(
        amazonSNS: AmazonSNS?
    ): NotificationMessagingTemplate {
        val notificationMessagingTemplate = NotificationMessagingTemplate(amazonSNS)
        notificationMessagingTemplate.setDefaultDestinationName(NOTIFICATION_CUSTOMER)
        return notificationMessagingTemplate
    }

    @Bean(name = ["customerQueueMessaging"])
    fun customerQueueMessagingTemplate(
        amazonSQSAsync: AmazonSQSAsync?
    ): QueueMessagingTemplate {
        val queueMessagingTemplate = QueueMessagingTemplate(amazonSQSAsync)
        queueMessagingTemplate.setDefaultDestinationName(QUEUE_CUSTOMER)
        return queueMessagingTemplate
    }

    @Bean
    fun simpleMessageListenerContainerFactory(amazonSQS: AmazonSQSAsync?): SimpleMessageListenerContainerFactory? {
        val factory = SimpleMessageListenerContainerFactory()
        factory.setAmazonSqs(amazonSQS)
        factory.setMaxNumberOfMessages(10)
        factory.setTaskExecutor(getAsyncExecutor())
        return factory
    }

    fun getAsyncExecutor(): AsyncTaskExecutor? {
        val executor = ThreadPoolTaskExecutor()
        executor.corePoolSize = 2
        executor.maxPoolSize = 5
        executor.setThreadNamePrefix("threadPoolExecutor-mySimpleMessageListenerContainer-")
        executor.setQueueCapacity(0)
        executor.afterPropertiesSet()
        return executor
    }
}