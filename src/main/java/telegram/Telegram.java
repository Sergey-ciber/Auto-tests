package telegram;

import autoTests.Test1;
import autoTests.Test2;
import autoTests.Test3;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Telegram extends TelegramLongPollingBot {

    private String botUsername;
    private String botToken;


    public Telegram () {
        super();

        //** Получаю данные из конфига **
        File file = new File("./config.properties");
        Properties properties = new Properties();
        try {
            properties.load(new FileReader(file));
        } catch (IOException e) {
            e.printStackTrace();
        }

        botUsername = properties.getProperty("botName");
        botToken = properties.getProperty("botToken");
    }

    SendMessage message = new SendMessage(); // Create a SendMessage object with mandatory fields
    SendPhoto sendPhoto = new SendPhoto();
    SendDocument sendDocument = new SendDocument();


    //    ** Метод для отправки сообщения в телеграм **
    public void sendNewMessage(String newMessage, Update update) {

        message.setChatId(update.getMessage().getChatId().toString());
        message.setText(newMessage);

        try {
            execute(message); // Call method to send the message
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    //    ** Метод для отправки документов в телеграм **
    public String sendNewDocuments(Update update) {

        String message = "message";

        File document = new File("files/NDFL.DOCX");
        sendDocument.setChatId(update.getMessage().getChatId().toString());
        InputFile file = new InputFile();
        file.setMedia(document);
        sendDocument.setDocument(file);

        try {
            execute(sendDocument); // Call method to send the message
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

        return message;
    }

    //    ** Метод для отправки скриншота в телеграм **
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
        String parameter1;

        if (update.hasMessage() && update.getMessage().hasText()) {

            //** Разбор входящего запроса из телеги на элементы **
            String[] inputQuery = update.getMessage().getText().split(" ");

            if (inputQuery.length < 1) {
                id_test = null;
            } else {
                id_test = inputQuery[0];
            }

            if (inputQuery.length < 2) {
                parameter1 = null;
            } else {
                parameter1 = inputQuery[1];
            }

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
                    if (parameter1 == null) {
                        sendNewMessage("Некорректный ID документа", update);
                    } else {
                        Test2 test2 = new Test2();
                        sendNewMessage("Старт теста №2", update);
                        test2.startTest(file -> sendNewPhoto(file, update), parameter1);
                    }
                    break;
                }
                case "3": {
                    if (parameter1 == null) {
                        sendNewMessage("Некорректный СНИЛС", update);
                    } else {
                        Test3 test3 = new Test3();
                        sendNewMessage("Старт теста №3", update);
                        test3.startTest(file -> sendNewPhoto(file, update), parameter1);
                    }
                    break;
                }
                case "4": {
                    sendNewMessage("Старт кейса 4", update);
                    sendNewDocuments(update);
                }
                break;

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
        return botUsername;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }
}
