package com.y2pan.practice.sort;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Main {
    private static int[] unsort = {23,43,54,12,3,4};

    private static int[] MaoPaoSort(int[] array) {
        return array;
    }

    private static void print(int[] array) {
        System.out.println(Arrays.stream(array).mapToObj(String::valueOf)
                .collect(Collectors.joining(",")));
    }

    public static void main(String[] args) {
        print(unsort);
        int[] result = MaoPaoSort(unsort);
    }
}
