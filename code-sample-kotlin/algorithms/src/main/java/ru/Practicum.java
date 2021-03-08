package ru;


public class Practicum {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        sb.append("Привет")
                .append(",")
                .append(" ")
                .append("как")
                .append(" ")
                .append("дела")
                .append("?");
        String kek = "kek";
        kek = kek.replace("e", sb);
        System.out.println(sb.toString());
    }
}
