package autoTests;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.Augmenter;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.function.Function;

public class AutoTests {
    private static String resultMessage;

    private static String saveScreenshot (ChromeDriver driver) {

        String path;
        try {
            WebDriver webDriver = new Augmenter().augment(driver);
            File source = ((TakesScreenshot)webDriver).getScreenshotAs(OutputType.FILE);
            path = "./" + source.getName();
            FileUtils.copyFile(source, new File(path));
        }
        catch(IOException e) {
            path = "Failed to capture screenshot: " + e.getMessage();
        }
        return path;
    }

    public static String startTest(Function<String, String> saveScreenshot) {

        String url = "http://10.225.16.26:8080/wozm/";
        String login = "TEST5210";
        String password = "1111";
        String dateBegin = "20.12.2021 00:00:00";
        String dateEnd = "31.12.2022 00:00:00";


        System.setProperty("webdriver.chrome.driver", "C:\\SeleniumDrivers\\chromedriver.exe");

//       ** Режим без интерфейса **
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");

        ChromeDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
        Actions actions = new Actions(driver);

        driver.get(url);

        WebElement inputLogin = driver.findElement(By.name("j_username"));
        WebElement inputPassword = driver.findElement(By.name("j_password"));


        inputLogin.sendKeys(login);
        inputPassword.sendKeys(password, Keys.ENTER);

        WebElement armFilial = driver.findElement(By.xpath("//*[@id='x-widget-5']/div[2]/table/tbody/tr/td/div[@class='GHGOUU-BCP GHGOUU-BHK GHGOUU-BGGC']"));
        armFilial.click();

        WebElement documents = driver.findElement(By.xpath("//*[@id='x-widget-7_f-695656']/div/img[2]"));
        documents.click();

        WebElement oneTimeDocuments = driver.findElement(By.xpath("//div[@id='x-widget-7_f-695681']/div[@class='GHGOUU-BLTC']/img[@src='http://10.225.16.26:8080/wozm/wozmgwt/clear.cache.gif']"));
        oneTimeDocuments.click();

        WebElement calculations = driver.findElement(By.xpath("//div[@id='x-widget-7_f-695694']/div[@class='GHGOUU-BLTC']/img[@src='http://10.225.16.26:8080/wozm/wozmgwt/clear.cache.gif']"));
        calculations.click();

        WebElement calculations2 = driver.findElement(By.xpath("//div[@id='x-widget-7_m-695695']/div/img[@src='http://10.225.16.26:8080/wozm/wozmgwt/clear.cache.gif']"));
        calculations2.click();

        WebElement filter = driver.findElement(By.xpath("(//div[@class='GHGOUU-BFK GHGOUU-BPJ GHGOUU-BMK GHGOUU-BJK'])[5]/div[@class='GHGOUU-BGMC GHGOUU-BCNC']/table/tbody/tr/td/div/div/table/tbody/tr/td/img"));
        filter.click();

        WebElement filterDateBegin = driver.findElement(By.id("x-widget-9-input"));
        filterDateBegin.click();
        filterDateBegin.sendKeys(dateBegin);

        WebElement filterDateEnd = driver.findElement(By.id("x-widget-10-input"));
        filterDateEnd.click();
        filterDateEnd.sendKeys(dateEnd);

        WebElement submitFilter = driver.findElement(By.xpath("(//div[@class='GHGOUU-BGDC']//div[@class='GHGOUU-BMP']//div[@class='GHGOUU-BFK GHGOUU-BPJ GHGOUU-BMK GHGOUU-BJK']//img[@src='http://10.225.16.26:8080/wozm/wozmgwt/clear.cache.gif'])[2]"));
        submitFilter.click();

        WebElement collectionOfDocuments = driver.findElement(By.xpath("//div[@class='GHGOUU-BJK GHGOUU-BKSB']"));
        actions.contextClick(collectionOfDocuments).perform();

        WebElement noticeDocuments = driver.findElement(By.xpath("//div[@id='x-menu-el-']/div[@class='GHGOUU-BNQC']/a[contains( text(),'Инвертировать отметки')]"));
        noticeDocuments.click();

        actions.contextClick(collectionOfDocuments).perform();

        WebElement calculationsDocuments = driver.findElement(By.xpath("//div[@id='x-menu-el-']/div[@class='GHGOUU-BNQC']/a[contains( text(),'Расчет пособий')]"));
        calculationsDocuments.click();

        WebElement startCalculations = driver.findElement(By.xpath("((//div[@class='GHGOUU-BMP'])[8]//img[@src='http://10.225.16.26:8080/wozm/wozmgwt/clear.cache.gif'])[2]"));
        startCalculations.click();

        WebElement calculationResult = driver.findElement(By.xpath("//div[contains( text(),'RegistrProcessingStarter')]"));
        calculationResult.click();

        WebElement resultText = driver.findElement(By.xpath("//textarea[@class='GHGOUU-BMW GHGOUU-BOW']"));

        saveScreenshot.apply(saveScreenshot(driver));

        resultMessage = resultText.getText();
        driver.close();

        return resultMessage;
    }
}