package telegram;

import autoTests.Test1;
import autoTests.Test2;
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
            File screenShotFile = new File(fileLocation);
            sendPhoto.setChatId(update.getMessage().getChatId().toString());
            InputFile file = new InputFile();
            file.setMedia(screenShotFile);
            sendPhoto.setPhoto(file);
            execute(sendPhoto); // Call method to send the screenShot
            if (screenShotFile.delete()) {
                System.out.println("Фаил удален");
            } else System.out.println("Фаил не найден");
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

        return fileLocation;
    }

    @Override
    public void onUpdateReceived(Update update) {

        String id_test;
        String id_documents;

        if (update.hasMessage() && update.getMessage().hasText()) {

            //** Разбор входящего запроса из телеги на элементы **
            String[] inputQuery = update.getMessage().getText().split(" ");

            if (inputQuery.length < 1){
                id_test = null;
            } else {id_test = inputQuery[0];}

            if (inputQuery.length < 2){
                id_documents = null;
            } else {id_documents = inputQuery[1];}

            switch (id_test) {
                case "1": {
                    if (inputQuery.length > 1) {
                        sendNewMessage("Для данного теста параметры не передаются", update);
                    }
                    Test1 test1 = new Test1();
                    sendNewMessage("Старт теста №1", update);
                    sendNewMessage(test1.startTest(file -> sendNewPhoto(file, update)), update);
                    break;
                }
                case "2": {
                    if(id_documents == null){
                        sendNewMessage("Некорректный ID документа", update);}
                    else {
                        Test2 test2 = new Test2();
                        sendNewMessage("Старт теста №2", update);
                        test2.startTest(file -> sendNewPhoto(file, update), inputQuery[1]);
                    }
                    break;
                }
                case "тесты": {
                    sendNewMessage("Для запуска теста №1 отправьте \"1\"" + "\n" +
                            "Для запуска теста №2 отправьте \"2 id_документа\"" + "\n" +
                            "пример id D436033617070FA8E0530A13E40A6F84", update);
                    break;
                }
                default: {
                    sendNewMessage("Не корректная команда. Для списка доступных команд отправьте команду \"тесты\"", update);
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
