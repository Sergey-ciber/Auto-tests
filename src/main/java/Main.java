import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Main {
    public static void main(String[] args) {

        String url = "http://10.225.16.26:8080/wozm/";
        String login = "TEST5210";
        String password = "1111";

        System.setProperty("webdriver.chrome.driver", "C:\\SeleniumDrivers\\chromedriver.exe");

        ChromeDriver driver = new ChromeDriver();
        Actions actions = new Actions(driver);

        driver.get(url);

        WebElement inputLogin = driver.findElement(By.name("j_username"));
        WebElement inputPassword = driver.findElement(By.name("j_password"));


        inputLogin.sendKeys(login);
        inputPassword.sendKeys(password, Keys.ENTER);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

        WebElement armFilial = driver.findElement(By.xpath("//*[@id='x-widget-5']/div[2]/table/tbody/tr/td/div[@class='GHGOUU-BCP GHGOUU-BHK GHGOUU-BGGC']"));
        armFilial.click();

        WebElement documents = driver.findElement(By.xpath("//*[@id='x-widget-7_f-695656']/div/img[2]"));
        documents.click();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

        WebElement oneTimeDocuments = driver.findElement(By.xpath("//div[@id='x-widget-7_f-695681']/div[@class='GHGOUU-BLTC']/img[@src='http://10.225.16.26:8080/wozm/wozmgwt/clear.cache.gif']"));
        oneTimeDocuments.click();

        WebElement calculations = driver.findElement(By.xpath("//div[@id='x-widget-7_f-695694']/div[@class='GHGOUU-BLTC']/img[@src='http://10.225.16.26:8080/wozm/wozmgwt/clear.cache.gif']"));
        calculations.click();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

        WebElement calculations2 = driver.findElement(By.xpath("//div[@id='x-widget-7_m-695695']/div/img[@src='http://10.225.16.26:8080/wozm/wozmgwt/clear.cache.gif']"));
        calculations2.click();

        WebElement collectionOfDocuments = driver.findElement(By.xpath("//div[@class='GHGOUU-BJK GHGOUU-BKSB']"));
        actions.contextClick(collectionOfDocuments).perform();

        WebElement noticeDocuments = driver.findElement(By.xpath("//div[@id='x-menu-el-']/div[@class='GHGOUU-BNQC']/a[contains( text(),'Инвертировать отметки')]"));
        noticeDocuments.click();

        actions.contextClick(collectionOfDocuments).perform();

        WebElement calculationsDocuments = driver.findElement(By.xpath("//div[@id='x-menu-el-']/div[@class='GHGOUU-BNQC']/a[contains( text(),'Расчет пособий')]"));
        calculationsDocuments.click();

        WebElement startCalculations = driver.findElement(By.xpath("((//div[@class='GHGOUU-BMP'])[8]//img[@src='http://10.225.16.26:8080/wozm/wozmgwt/clear.cache.gif'])[2]"));
        startCalculations.click();

        WebElement calculationResult = (new WebDriverWait(driver, Duration.ofSeconds(100)).until(ExpectedConditions
                .presenceOfElementLocated(By.xpath("//div[contains( text(),'RegistrProcessingStarter')]"))));
        calculationResult.click();

        WebElement resultText = driver.findElement(By.xpath("//textarea[@class='GHGOUU-BMW GHGOUU-BOW']"));

        System.out.println(resultText.getText());

        driver.close();

    }
}
