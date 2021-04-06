package ru.strings;

import java.util.ArrayList;
import java.util.List;

public class PoemFixer {

    public List<String> readPoem() {
        var list = new ArrayList<String>();
        list.add("   Это кто там ложку «ложит»?");
        list.add("  ");
        list.add("Знай, такого быть не может!");
        list.add("  Ложку мы на стол кладём,  ");
        list.add("");
        list.add("А тебя – к обеду ждём.");
        return list;
    }

    public String fixPoem(List<String> poem) {
        var sb = new StringBuilder();
        for (String line: poem) {
            if (!line.isEmpty() && !line.isBlank()) {
                sb.append(line.trim());
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        var poemFixer = new PoemFixer();
        var poem = poemFixer.readPoem();
        var poemAsString = poemFixer.fixPoem(poem);
        System.out.println(poemAsString);
    }

}
