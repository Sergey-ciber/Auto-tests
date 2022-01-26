package telegram;

import autoTests.AutoTests;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class Telegram extends TelegramLongPollingBot {

    SendMessage message = new SendMessage(); // Create a SendMessage object with mandatory fields

    public void sendNewMessage(String newMessage, Update update) {

        message.setChatId(update.getMessage().getChatId().toString());
        message.setText(newMessage);

        try {
            execute(message); // Call method to send the message
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpdateReceived(Update update) {

        if (update.hasMessage() && update.getMessage().hasText()) {

            switch (update.getMessage().getText()) {
                case "тест 1": {
                    sendNewMessage("Старт теста №1", update);
                    AutoTests.startTest();
                    break;
                }
                default: {
                    sendNewMessage("Неверная команда", update);
                    break;
                }
            }
        }
    }

    @Override
    public String getBotUsername() {
        return "PVSO_auto_tests_bot";
    }

    @Override
    public String getBotToken() {
        return "5200651683:AAFj7z5VFvaFj-Dzt5h-ci3LpkYEqQXjgVk";
    }
}
