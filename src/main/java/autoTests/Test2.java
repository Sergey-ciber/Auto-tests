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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

public class Test2 {
    private static String resultMessage;
    private static int valueStipend;


    //    ** Сохранить скриншот. Возвращает путь к файлу **
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

    public static void startTest(Function<String, String> saveScreenshot, String idDocument) {


        String url = "http://10.225.16.26:8080/wozm/";
        String login = "TEST5210";
        String password = "1111";


        System.setProperty("webdriver.chrome.driver", "C:\\SeleniumDrivers\\chromedriver.exe");

//       ** Режим без интерфейса **
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");

        ChromeDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
        driver.manage().window().maximize();
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

        WebElement searchDocuments = driver.findElement(By.xpath("//div[@id='x-widget-7_f-695716']/div[@class='GHGOUU-BLTC']/img[@src='http://10.225.16.26:8080/wozm/wozmgwt/clear.cache.gif']"));
        searchDocuments.click();

        WebElement searchDocuments2 = driver.findElement(By.xpath("(//span[contains(text(),'Поиск документов')])[2]"));
        actions.doubleClick(searchDocuments2).perform();

        WebElement inputGuidDocument = driver.findElement(By.xpath("//input[@id='x-widget-10-input']"));
        inputGuidDocument.clear();
        inputGuidDocument.sendKeys(idDocument);

        WebElement applySearch = driver.findElement(By.xpath("(//div[@class='GHGOUU-BGDC']//img[@src='http://10.225.16.26:8080/wozm/wozmgwt/clear.cache.gif'])[3]"));
        applySearch.click();

        WebElement document = driver.findElement(By.xpath("//tr[@class='GHGOUU-BBJC GHGOUU-BNK ']"));
        actions.doubleClick(document).perform();

        WebElement maxWindowSize = driver.findElement(By.xpath("//div[@class='GHGOUU-BCP GHGOUU-BHK GHGOUU-BOFC']"));
        maxWindowSize.click();

        saveScreenshot.apply(saveScreenshot(driver));

        WebElement infoPersona = driver.findElement(By.xpath("//span[contains(text(),'Персона')]"));
        infoPersona.click();
        saveScreenshot.apply(saveScreenshot(driver));

        WebElement infoStipend = driver.findElement(By.xpath("//span[@class='GHGOUU-BNSC']/span[contains(text(),'Пособие')]"));
        infoStipend.click();
        saveScreenshot.apply(saveScreenshot(driver));

//        ** Получаем вид пособия **
        WebElement stipend = driver.findElement(By.xpath("(//fieldset[@class='GHGOUU-BJOC'])[14]//input"));
        valueStipend = Integer.parseInt(stipend.getAttribute("value"));
        System.out.println("Пособие " + valueStipend);

        if(valueStipend == 1 || valueStipend == 2 || valueStipend == 3){
            WebElement InfoPeriod = driver.findElement(By.xpath("//span[@class='GHGOUU-BNSC']/span[contains(text(),'Период')]"));
            InfoPeriod.click();
            saveScreenshot.apply(saveScreenshot(driver));

            WebElement InfoCalculation = driver.findElement(By.xpath("//span[@class='GHGOUU-BNSC']/span[contains(text(),'Расчет')]"));
            InfoCalculation.click();
            saveScreenshot.apply(saveScreenshot(driver));
        }

        WebElement infoResult = driver.findElement(By.xpath("//span[@class='GHGOUU-BNSC']/span[contains(text(),'Результат')]"));
        infoResult.click();
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        saveScreenshot.apply(saveScreenshot(driver));

        driver.close();
    }
}