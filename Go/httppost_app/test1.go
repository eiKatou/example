package main

// https://qiita.com/yyoshiki41/items/fc878494e19b9de93d56

import (
	"bytes"
	"fmt"
	"net/http"
)

func main() {
	error := HttpPost("https://httpbin.org/post", "token")
	if error != nil {
		fmt.Println("error. called http post.")
		return
	}
	fmt.Println("called http post.")
}

func HttpPost(url, text string) error {
	jsonStr := `{"text":"` + text + `"}`

	req, err := http.NewRequest(
		"POST",
		url,
		bytes.NewBuffer([]byte(jsonStr)),
	)
	if err != nil {
		return err
	}

	// Content-Type 設定
	req.Header.Set("Content-Type", "application/json")

	client := &http.Client{}
	resp, err := client.Do(req)
	if err != nil {
		return err
	}
	defer resp.Body.Close()

	return err
}
