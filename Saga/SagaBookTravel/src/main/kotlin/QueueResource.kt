object QueueResource {
    const val AWS_PROFILE_NAME = "work"
    const val AWS_REGION = "us-west-2"

    // Travel agent service request
    const val TRAVEL_AGENT_REQUEST_TOPIC_ARN = "arn:aws:sns:us-west-2:954199018376:travel-agent-request-topic"
    const val TRAVEL_AGENT_REQUEST_QUEUE_URL = "https://sqs.us-west-2.amazonaws.com/954199018376/travel-agent-request-queue"

    // Car Rental service request
    const val RENT_CAR_REQUEST_TOPIC_ARN = "arn:aws:sns:us-west-2:954199018376:rent-car-request-topic"
    const val RENT_CAR_REQUEST_QUEUE_URL = "https://sqs.us-west-2.amazonaws.com/954199018376/rent-car-request-queue"

    // Car Rental service response
    const val RENT_CAR_RESPONSE_TOPIC_ARN = "arn:aws:sns:us-west-2:954199018376:rent-car-response-topic"
    const val RENT_CAR_RESPONSE_QUEUE_URL = "https://sqs.us-west-2.amazonaws.com/954199018376/rent-car-response-queue"

    // Hotel Reservation service request
    const val BOOK_HOTEL_REQUEST_TOPIC_ARN = "arn:aws:sns:us-west-2:954199018376:book-hotel-request-topic"
    const val BOOK_HOTEL_REQUEST_QUEUE_URL = "https://sqs.us-west-2.amazonaws.com/954199018376/book-hotel-request-queue"

    // Hotel Reservation service response
    const val BOOK_HOTEL_RESPONSE_TOPIC_ARN = "arn:aws:sns:us-west-2:954199018376:book-hotel-response-topic"
    const val BOOK_HOTEL_RESPONSE_QUEUE_URL = "https://sqs.us-west-2.amazonaws.com/954199018376/book-hotel-response-queue"

    // Airline Reservation service request
    const val BOOK_FLIGHT_REQUEST_TOPIC_ARN = "arn:aws:sns:us-west-2:954199018376:book-flight-request-topic"
    const val BOOK_FLIGHT_REQUEST_QUEUE_URL = "https://sqs.us-west-2.amazonaws.com/954199018376/book-flight-request-queue"

    // Airline Reservation service response
    const val BOOK_FLIGHT_RESPONSE_TOPIC_ARN = "arn:aws:sns:us-west-2:954199018376:book-flight-response-topic"
    const val BOOK_FLIGHT_RESPONSE_QUEUE_URL = "https://sqs.us-west-2.amazonaws.com/954199018376/book-flight-response-queue"

}