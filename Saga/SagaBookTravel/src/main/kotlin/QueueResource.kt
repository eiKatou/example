object QueueResource {
    const val AWS_PROFILE_NAME = "work"
    const val AWS_REGION = "us-west-2"

    // Travel agent service request
    const val TRAVEL_AGENT_REQUEST_TOPIC_ARN = "arn:aws:sns:us-west-2:954199018376:travel-agent-request-topic"
    const val TRAVEL_AGENT_REQUEST_QUEUE_URL = "https://sqs.us-west-2.amazonaws.com/954199018376/travel-agent-request-queue"

    // Rent car service request
    const val RENT_CAR_REQUEST_TOPIC_ARN = "arn:aws:sns:us-west-2:954199018376:rent-car-request-topic"
    const val RENT_CAR_REQUEST_QUEUE_URL = "https://sqs.us-west-2.amazonaws.com/954199018376/rent-car-request-queue"

    // Rent car service response
    const val RENT_CAR_RESPONSE_TOPIC_ARN = "arn:aws:sns:us-west-2:954199018376:rent-car-response-topic"
    const val RENT_CAR_RESPONSE_QUEUE_URL = "https://sqs.us-west-2.amazonaws.com/954199018376/rent-car-response-queue"
}