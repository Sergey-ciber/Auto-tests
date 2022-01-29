package telegram;

import autoTests.AutoTests;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.File;

public class Telegram extends TelegramLongPollingBot {

    SendMessage message = new SendMessage(); // Create a SendMessage object with mandatory fields
    SendPhoto sendPhoto = new SendPhoto();

    public void sendNewMessage(String newMessage, Update update) {

        message.setChatId(update.getMessage().getChatId().toString());
        message.setText(newMessage);

        try {
            execute(message); // Call method to send the message
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public String sendNewPhoto(String fileLocation, Update update) {



        try {
            sendPhoto.setChatId(update.getMessage().getChatId().toString());
            InputFile file = new InputFile();
            file.setMedia(new File(fileLocation));
            sendPhoto.setPhoto(file);
            execute(sendPhoto); // Call method to send the message
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

        return fileLocation;
    }

    @Override
    public void onUpdateReceived(Update update) {

        if (update.hasMessage() && update.getMessage().hasText()) {

            switch (update.getMessage().getText()) {
                case "1": {
                    sendNewMessage("Старт теста №1", update);
                    sendNewMessage(AutoTests.startTest(file -> sendNewPhoto(file, update)), update);
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
