package ru.strings;

import java.util.ArrayList;

public class GradesReversed {

    private String gradeStringToInt(String grade) {
        switch (grade) {
            case "Безупречно": {
                return "5";
            }
            case "Потрясающе": {
                return "4";
            }
            case "Восхитительно": {
                return "3";
            }
            case "Прекрасно": {
                return "2";
            }
        }
        return "1";
    }

    public String serializeGrades(String[] grades) {
        var list = new String[grades.length];
        var index = 0;
        for (String grade : grades) {
            var split = grade.split(" ");
            var str = String.join(",",
                    split[0].toLowerCase(),
                    split[1].toLowerCase(),
                    split[2].toLowerCase(),
                    gradeStringToInt(split[4]));
            list[index++] = str;
        }
        return String.join(";", list);
    }

    public static void main(String[] args) {
        var g = new GradesReversed();
        var list = new String[]{"Вероника Чехова физика — Безупречно", "Анна Строкова математика — Потрясающе"};
        System.out.println(g.serializeGrades(list));
    }
}
