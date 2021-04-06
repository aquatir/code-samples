package ru.strings;

public class FindRepeats {
    int numberOfRepeats(String text, String substring) {
        var count = 0;
        while (text.contains(substring)) {
            count++;
            text = text.substring(text.indexOf(substring) + substring.length());
        }
        return count;
    }

    public static void main(String[] args) {
        var repeats = new FindRepeats();
        System.out.println(repeats.numberOfRepeats("раз раз раз", "раз"));
    }
}
