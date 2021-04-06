package ru.strings;

public class StartsWith {
    public boolean startsWith(String initial, String other) {
        return initial.indexOf(other) == 0;
    }

    public boolean endsWith(String initial, String other) {
        return initial.lastIndexOf(other) + other.length() == initial.length();
    }

    public static void main(String[] args) {
        var st = new StartsWith();
        System.out.println(st.startsWith("kek", "ke"));
        System.out.println(st.startsWith("kek", "lo"));
        System.out.println(st.startsWith("", ""));

        System.out.println(st.endsWith("kek", "ek"));
        System.out.println(st.endsWith("", ""));
        System.out.println(st.endsWith("  ", " "));
    }
}
