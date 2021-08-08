import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.openqa.selenium.chrome.ChromeDriver

fun main(args: Array<String>) {
    println("Hello World!")
    System.setProperty("webdriver.chrome.driver", "./driver/chromedriver")
    val driver = ChromeDriver()
    driver.get("https://www.yahoo.co.jp/")
    runBlocking {
        delay(5000L)
    }
    driver.quit()
}