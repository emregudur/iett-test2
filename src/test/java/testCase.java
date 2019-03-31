import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public class testCase {
    @Test
    public void fristCase() throws InterruptedException {
        System.setProperty("webdriver.gecko.driver","geckodriver.exe");

        WebDriver driver = new FirefoxDriver();
        driver.get("https://iett.istanbul");
        driver.manage().deleteAllCookies();
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        WebElement searchBox;
        driver.findElement(By.id("select2-searchShortcutsLine-container")).click();
        searchBox = driver.findElement(By.className("select2-search__field"));
        searchBox.sendKeys("300");

        Thread.sleep(1000);

        searchBox.sendKeys(Keys.ENTER);

        Thread.sleep(2000);

        driver.findElement(By.className("select2-selection__clear")).click();

        Thread.sleep(1000);

        jse.executeScript("window.scrollBy(0,-(window.scrollY))", "");

        Actions action = new Actions(driver);
        for (WebElement fElem : driver.findElements(By.className("SiteNavigation_link"))) {
            action.moveToElement(fElem).perform();
            Thread.sleep(1000);
            if (fElem.getText().equals("Kurumsal")){
                fElem.click();
                Thread.sleep(500);

                WebElement industries = driver.findElement(By.cssSelector("nav.SiteMenuList > ul"));
                List<WebElement> links = industries.findElements(By.tagName("li"));

                for (WebElement elem: links) {
                    if (elem.getText().length() > 0) {
                        action.moveToElement(elem).perform();
                        Thread.sleep(100);
                    }
                }
                Thread.sleep(2000);
                fElem.click();
            }
        }
        driver.findElement(By.id("select2-searchShortcutsLine-container")).click();
        Thread.sleep(1000);
        WebElement searchBox2 = driver.findElement(By.className("SiteSearch_button"));
        searchBox2.click();
        WebElement searchBox2Input = driver.findElement(By.className("SiteSearch_text"));
        searchBox2Input.sendKeys("Nasıl giderim");
        Thread.sleep(1000);
        searchBox2Input.submit();

        Thread.sleep(2000);
        WebElement logoTopLeft = driver.findElement(By.className("SiteLogo"));
        action.moveToElement(logoTopLeft).perform();
        Thread.sleep(2000);
        logoTopLeft.click();
        Thread.sleep(2000);
        driver.findElement(By.id("select2-searchShortcutsLine-container")).click();
        searchBox = driver.findElement(By.className("select2-search__field"));
        searchBox.sendKeys("Perfect!!");
        Thread.sleep(5000);
        driver.quit();
    }
}
