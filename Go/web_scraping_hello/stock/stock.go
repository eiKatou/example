package main

// go get github.com/PuerkitoBio/goquery

import (
	"fmt"
	"strconv"
	"strings"

	"github.com/PuerkitoBio/goquery"
)

func main() {
	ivv := NewStock("https://www.bloomberg.co.jp/quote/IVV:US")
	ivv.Println()

	agg := NewStock("https://www.bloomberg.co.jp/quote/AGG:US")
	agg.Println()
}

type Stock struct {
	Ticker        string
	Price         float64
	PriceDatetime string
}

func (stock Stock) Println() {
	fmt.Println(stock.Ticker)
	fmt.Println(stock.Price)
	fmt.Println(stock.PriceDatetime)
}

func NewStock(url string) *Stock {
	doc, err := goquery.NewDocument(url)
	if err != nil {
		fmt.Print("url scarapping failed")
	}

	newStock := Stock{}
	basicQuote := doc.Find("div.basic-quote")
	basicQuote.Find("div.ticker").
		Each(func(_ int, s *goquery.Selection) {
			newStock.Ticker = strings.TrimSpace(s.Text())
		})
	basicQuote.Find("div.price").
		Each(func(_ int, s *goquery.Selection) {
			priceString := strings.TrimSpace(s.Text())
			newStock.Price, _ = strconv.ParseFloat(priceString, 16)
		})
	basicQuote.Find("div.price-datetime").
		Each(func(_ int, s *goquery.Selection) {
			newStock.PriceDatetime = strings.TrimSpace(s.Text())
		})
	return &newStock
}
