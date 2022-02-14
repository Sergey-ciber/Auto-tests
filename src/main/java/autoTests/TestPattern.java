package autoTests;

import org.apache.commons.io.FileUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.BasicHttpContext;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.Augmenter;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import static org.apache.commons.io.FileUtils.copyInputStreamToFile;

public abstract class TestPattern {

    private String url;
    private String login;
    private String password;

    public TestPattern() {
        this.getConfig();
    }

    public void getConfig() {

        //** Получаю данные из конфига **
        File file = new File("./config.properties");
        Properties properties = new Properties();
        try {
            properties.load(new FileReader(file));
        } catch (IOException e) {
            e.printStackTrace();
        }


        this.setUrl(properties.getProperty("url"));
        this.setLogin(properties.getProperty("login"));
        this.setPassword(properties.getProperty("password"));
    }

    //    ** Сохранить скриншот. Возвращает путь к файлу **
    public String saveScreenshot (ChromeDriver driver) {

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

    public String saveFile(String downloadLink) {

        String path ="files/PersonaIncome.docx";

        //Set file to save
        File fileToSave = new File(path);

//Download file using default org.apache.http client
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(downloadLink);
        HttpResponse response = null;
        try {
            response = httpClient.execute(httpGet, new BasicHttpContext());
        } catch (IOException e) {
            e.printStackTrace();
        }

//Save file on disk
        try {
            copyInputStreamToFile(response.getEntity().getContent(), fileToSave);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return path;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
