package ru.strings;

import java.util.ArrayList;
import java.util.Locale;

public class Grades {

    private String capitalize(String str) {
        return str.substring(0,1).toUpperCase() + str.substring(1);
    }

    /**
     * @param grades - строка вида "имя,фамилия,предмет,оценка;имя,фамилия,предмет,оценка;"
     * */
    public void gradeBeautifier(String grades) {

        var students = grades.split(";");

        for (String student: students) {
            var studentSplit = student.split(",");
            String str = capitalize(studentSplit[0]) +
                    " " +
                    capitalize(studentSplit[1]) +
                    " " +
                    studentSplit[2].toLowerCase() +
                    " — " +
                    gradeToString(studentSplit[3]);
            System.out.println(str);
        }
    }

    private String gradeToString(String grade) {
        switch (grade) {
            case "5": {
                return "Волшебно";
            }
            case "4": {
                return "Восхитительно";
            }
            case "3": {
                return "Потрясающе";
            }
            case "2": {
                return "Прекрасно";
            }
        }
        return "Очаровательно";
    }

    public static void main(String[] args) {
        var grades = new Grades();
        grades.gradeBeautifier("вероника,чехова,ФИЗИКА,5;виктория,строкова,МАТЕМАТИКА,4");
    }
}
