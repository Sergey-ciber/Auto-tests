import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import telegram.*;

public class Main {
    public static void main(String[] args) {

        System.out.println("Start my test");

        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(new Telegram());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
