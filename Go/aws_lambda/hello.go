package main

// https://dev.classmethod.jp/cloud/aws/aws-lambda-supports-go/
import (
	"fmt"

	"github.com/aws/aws-lambda-go/lambda"
)

// MyEvent is ..
type MyEvent struct {
	Name string `json:"What is your name?"`
}

// MyResponse is ..
type MyResponse struct {
	Message string `json:"Answer:"`
}

func hello(event MyEvent) (MyResponse, error) {
	return MyResponse{Message: fmt.Sprintf("Hello %s!!", event.Name)}, nil
}

func main() {
	lambda.Start(hello)
}
