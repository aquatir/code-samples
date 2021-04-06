package ru.strings;

import java.util.Locale;

public class Capitalizator {

    public String capitalize(String str) {
        return str.substring(0,1).toUpperCase() + str.substring(1);
    }
}
