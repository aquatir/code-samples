package com.codesample.advent2021;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class Task1 {

    public static void main(String[] args) throws IOException, URISyntaxException {

        List<Integer> list = Files.lines(Paths.get(Task1.class.getClassLoader().getResource("advent2021/task1_input.txt").toURI()))
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        System.out.println(list);
        int growing = 0;

        int first = list.get(0);
        int second = list.get(1);
        int third = list.get(2);

        for (int i = 3; i < list.size(); i++) {
            if (second + third + list.get(i) > first + second + third) {
                growing++;
            }

            first = second;
            second = third;
            third = list.get(i);
        }

        System.out.println(growing);
    }
}
