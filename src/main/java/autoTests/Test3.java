package autoTests;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.BasicHttpContext;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.function.Function;

import static org.apache.commons.io.FileUtils.copyInputStreamToFile;

public class Test3 extends TestPattern {

    public Test3() {
        super();
    }

    public void startTest(Function<String, String> sendDocument, String parameter1) {

        System.setProperty("webdriver.chrome.driver", "C:\\SeleniumDrivers\\chromedriver.exe");

//       ** Режим без интерфейса **
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");

        ChromeDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
        driver.manage().window().maximize();
        Actions actions = new Actions(driver);

        driver.get(getUrl());

        WebElement inputLogin = driver.findElement(By.name("j_username"));
        WebElement inputPassword = driver.findElement(By.name("j_password"));


        inputLogin.sendKeys(getLogin());
        inputPassword.sendKeys(getPassword(), Keys.ENTER);

        WebElement armFilial = driver.findElement(By.xpath("//*[@id='x-widget-5']/div[2]/table/tbody/tr/td/div[@class='GHGOUU-BCP GHGOUU-BHK GHGOUU-BGGC']"));
        armFilial.click();

        WebElement references = driver.findElement(By.xpath("//div[@id='x-widget-7_f-695627']//img[@src='http://10.225.16.26:8080/wozm/wozmgwt/clear.cache.gif']"));
        references.click();

        WebElement insured = driver.findElement(By.xpath("//span[contains(text(),'Застрахованные')]"));
        actions.doubleClick(insured).perform();

        WebElement inputGuidDocument = driver.findElement(By.xpath("//input[@id='x-widget-16-input']"));
        inputGuidDocument.clear();
        inputGuidDocument.sendKeys(parameter1);

        WebElement submit = driver.findElement(By.xpath("(//div[@class='GHGOUU-BBDC']//div[@class='GHGOUU-BFK GHGOUU-BPJ GHGOUU-BMK GHGOUU-BJK']//td[@class='GHGOUU-BMMC'])[2]"));
        submit.click();

        WebElement persona = driver.findElement(By.xpath("//tr[@class='GHGOUU-BBJC GHGOUU-BNK ']"));
        actions.doubleClick(persona).perform();

        WebElement operations = driver.findElement(By.xpath("(//div[contains(text(),'Операции')])[2]"));
        operations.click();

        WebElement printNDFL = driver.findElement(By.xpath("(//div[@id='x-menu-el-'])[2]"));
        printNDFL.click();

        WebElement startPrintNDFL = driver.findElement(By.xpath("((//div[@class='GHGOUU-BGDC'])[2]//div[@class='GHGOUU-BBDC']//div[@class='GHGOUU-BMP']/div[@class='GHGOUU-BFK GHGOUU-BPJ GHGOUU-BMK GHGOUU-BJK'])[2]"));
        startPrintNDFL.click();

        WebElement downloadLink = driver.findElement(By.xpath("//a[contains(text(),'PersonaIncome.docx')]"));
        downloadLink.click();

////        Get download link
//        String downloadLink = driver
//                .findElement(By.xpath("//a[contains(text(),'PersonaIncome.docx')]"))
//                .getAttribute("href");
//
//        sendDocument.apply(saveFile(downloadLink));
//
//        driver.close();
    }
}