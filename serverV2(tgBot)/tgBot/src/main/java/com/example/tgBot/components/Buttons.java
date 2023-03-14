package com.example.tgBot.components;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.List;


public class Buttons {
    private static final InlineKeyboardButton START_BUTTON = new InlineKeyboardButton("Start");
    private static final InlineKeyboardButton HELP_BUTTON = new InlineKeyboardButton("Help");

    public static InlineKeyboardMarkup inlineMarkup() {
        START_BUTTON.setCallbackData("/star");
        HELP_BUTTON.setCallbackData("/help");

        List<InlineKeyboardButton> rowInLine = List.of(START_BUTTON, HELP_BUTTON);
        List<List<InlineKeyboardButton>> rowsInLine = List.of(rowInLine);

        InlineKeyboardMarkup markupInLine = new InlineKeyboardMarkup();
        markupInLine.setKeyboard(rowsInLine);

        return markupInLine;
    }
}
