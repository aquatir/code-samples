package ru.strings;

public class CheckPrinter {

    public void printCheck(String[] items) {
        for (String item: items) {
            var split = item.split(", ");
            System.out.printf("%-10s%-7s%-7s\n", split[0], split[1], split[2]);
        }
    }

    public static void main(String[] args) {
        var c = new CheckPrinter();
        var str = new String[] {"Пицца, 1 шт., 10.50", "Чай, 2 шт., 3.30", "Печенье, 1 уп., 5.75"};
        c.printCheck(str);
    }
}
