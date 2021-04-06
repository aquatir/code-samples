package ru.strings;

public class FindRepeatsEffective {
    int numberOfRepeats(String text, String substring) {
        var count = 0;
        var sb = new StringBuilder(text);
        while (sb.indexOf(substring) != -1) {
            count++;
            sb.delete(0, sb.indexOf(substring) + substring.length());
        }
        return count;
    }

    public static void main(String[] args) {
        var repeats = new FindRepeatsEffective();
        System.out.println(repeats.numberOfRepeats("разразраз", "раз"));
    }
}
