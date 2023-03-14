package com.example.tgBot;

import com.example.tgBot.components.BotCommands;
import com.example.tgBot.components.Buttons;
import com.example.tgBot.config.BotConfig;
import com.example.tgBot.response.BookListResponse;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


@Slf4j
@Component
public class CounterTelegramBot extends TelegramLongPollingBot implements BotCommands {
    final BotConfig config;


    public CounterTelegramBot(BotConfig config) {
        this.config = config;
        try {
            this.execute(new SetMyCommands(LIST_OF_COMMANDS, new BotCommandScopeDefault(), null));
        } catch (TelegramApiException e){
            log.error(e.getMessage());
        }
    }


    @Override
    public String getBotUsername() {
        return config.getBotName();
    }


    @Override
    public String getBotToken() {
        return config.getToken();
    }

   /* @Override
    public void onUpdateRe1ceived(org.telegram.telegrambots.meta.api.objects.Update update) {
        long chatId = 0;
        long userId = 0; //это нам понадобится позже
        String userName = null;
        String receivedMessage;


        //если получено сообщение текстом
        if(update.hasMessage()) {
            chatId = update.getMessage().getChatId();
            userId = update.getMessage().getFrom().getId();
            userName = update.getMessage().getFrom().getFirstName();
            if (update.getMessage().hasText()) {
                receivedMessage = update.getMessage().getText();
                botAnswerUtils(receivedMessage, chatId, userName);
            }


            //если нажата одна из кнопок бота
        } else if (update.hasCallbackQuery()) {
            chatId = update.getCallbackQuery().getMessage().getChatId();
            userId = update.getCallbackQuery().getFrom().getId();
            userName = update.getCallbackQuery().getFrom().getFirstName();
            receivedMessage = update.getCallbackQuery().getData();
            botAnswerUtils(receivedMessage, chatId, userName);
        }
    }*/
    @Override
    public void onUpdateReceived(org.telegram.telegrambots.meta.api.objects.Update update) {
        if (update.hasMessage() && update.getMessage().hasText()){
        String messageText = update.getMessage().getText();
        long chatId = update.getMessage().getChatId();
        String memberName = update.getMessage().getFrom().getFirstName();
        String[] arr = messageText.split(" ");
        String title = update.getMessage().getText();
        switch (arr[0]){
            case "/start":
                startBot(chatId, memberName);
                break;
            case "/all":
                getAllBook(chatId);
                break;
            case "/help":
                sendHelpText(chatId);
                break;
            case "/serch":
                SerchBook(chatId, arr[1]);
                break;
            default:log.info("Unexpected message");
        }
        }

    }
    private void SerchBook(long chatId, String publishing){
        SendMessage message = new SendMessage();
    message.setChatId(chatId);
    ResponseEntity <BookListResponse> responseEntity = new RestTemplate().getForEntity(
            "http://localhost:28242/api/v1/book/?publishing=" +
                    publishing, BookListResponse.class
    );
        System.out.println(responseEntity.getBody().getData());
        message.setText(responseEntity.getBody().getData().toString());

        try {
            execute(message);
            log.info("Replay sent");
        } catch (TelegramApiException e){
            log.error(e.getMessage());
        }
    }


    private void getAllBook(long chatId){
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        ResponseEntity<BookListResponse> responseEntity = new RestTemplate().getForEntity(
                "http://localhost:28242/api/v1/book/all", BookListResponse.class);
        System.out.println(responseEntity.getBody().getData());
        message.setText(responseEntity.getBody().getData().toString());

        try {
            execute(message);
            log.info("Reply sent");
        } catch (TelegramApiException e){
            log.error(e.getMessage());
        }
    }


    /*private void botAnswerUtils(String receivedMessage, long chatId, String userName) {
        switch (receivedMessage){
            case "/start":
                startBot(chatId, userName);
                break;
            case "/help":
                sendHelpText(chatId);
                break;
            default: break;
        }
    }*/


    private void startBot(long chatId, String userName) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText("Hi, " + userName + "! I'm a Telegram bot.'");
        message.setReplyMarkup(Buttons.inlineMarkup());


        try {
            execute(message);
            log.info("Reply sent");
        } catch (TelegramApiException e){
            log.error(e.getMessage());
        }
    }


    private void sendHelpText(long chatId){
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(BotCommands.HELP_TEXT);


        try {
            execute(message);
            log.info("Reply sent");
        } catch (TelegramApiException e){
            log.error(e.getMessage());
        }
    }

}



