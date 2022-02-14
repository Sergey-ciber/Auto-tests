import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.BasicHttpContext;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import telegram.Telegram;

import java.io.File;
import java.io.IOException;


import static org.apache.commons.io.FileUtils.copyInputStreamToFile;

public class Main {
    public static void main(String[] args) {

        System.out.println("Start my test");

        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(new Telegram());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }



//        //Set file to save
//        File fileToSave = new File("files/test.DOCX");
//
////Download file using default org.apache.http client
//        CloseableHttpClient httpClient = HttpClients.createDefault();
//        HttpGet httpGet = new HttpGet("http://10.225.16.26:8080/wozm/downloadOperationService?filePath=INCOME_PERSON_PRINT/PersonaIncome.docx&browserInfo=mozilla/5.0%20(windows%20nt%2010.0;%20win64;%20x64)%20applewebkit/537.36%20(khtml,%20like%20gecko)%20chrome/96.0.4664.174%20yabrowser/22.1.3.848%20yowser/2.5%20safari/537.36");
//        HttpResponse response = null;
//        try {
//            response = httpClient.execute(httpGet, new BasicHttpContext());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
////Save file on disk
//        try {
//            copyInputStreamToFile(response.getEntity().getContent(), fileToSave);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }
}
