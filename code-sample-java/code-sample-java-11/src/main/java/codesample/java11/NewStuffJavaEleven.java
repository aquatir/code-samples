package codesample.java11;


import java.util.List;
import static java.util.function.Predicate.not;

import java.util.function.Predicate;
import java.util.stream.Collectors;

public class NewStuffJavaEleven {
    public static void main(String[] args) {

        // Строка содержит только whitespaces
        var strr = "  ";
        System.out.println(strr.isBlank());

        // .lines() возвращает stream из строк
        var whatIsLove = "What is love\n" +
                "Baby don't hurt me\n" +
                "Don't hurt me\n" +
                "no more";
        whatIsLove.lines()
                .filter(str -> str.contains("hurt"))
                .forEach(System.out::println);

        // Повторение строк
        var nuan = "nuan ".repeat(5).strip();
        System.out.println(nuan);

        // OK
        var list1 = List.of("abc", "", "Bob").stream()
                .filter(String::isBlank)
                .collect(Collectors.toList());

        // not OK
//        var list2 = List.of("abc", "", "Bob").stream()
//                .filter(!String::isBlank)
//                .collect(Collectors.toList());

        // OK
        var list3 = List.of("abc", "", "Bob").stream()
                .filter(Predicate.not(String::isBlank))
                .collect(Collectors.toList());

        // OK
        var list4 = List.of("abc", "", "Bob").stream()
                .filter(not(String::isBlank))
                .collect(Collectors.toList());


    }

    interface Function<T> {
        T calculate(T x, T y);
    }
}
