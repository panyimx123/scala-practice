package com.y2pan.practice.sort;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Main {
    private static int[] unSort = {23, 6, 84, 34, 13, 32, 54, 12, 3, 4};

    private static int[] maoPaoSort(int[] array) {
        System.out.println("maoPaoSort");
        int id = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] < array[j + 1]) {
                    int tmp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = tmp;
                }
                System.out.print(++id + " ");
                print(array);
            }
        }
        return array;
    }

    private static int[] maoPaoSort1(int[] array) {
        System.out.println("maoPaoSort1");
        int id = 0;
        for (int i = 0; i < array.length; i++) {
            boolean flag = false;
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] < array[j + 1]) {
                    flag = true;
                    int tmp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = tmp;
                }
                System.out.print(++id + " ");
                print(array);
            }
            if (!flag) {
                break;
            }
        }
        return array;
    }

    private static int[] maoPaoSort2(int[] array) {
        System.out.println("maoPaoSort2");
        int id = 0;
        int k = 0;
        int flag = array.length - 1;
        for (int i = 0; i < array.length && flag != 0; i++) {
            k = flag;
            flag = 0;
            for (int j = 0; j < k; j++) {
                if (array[j] < array[j + 1]) {
                    flag = j;
                    int tmp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = tmp;
                }
                System.out.print(++id + " ");
                print(array);
            }
        }
        return array;
    }

    private static int[] insertSort(int[] array) {
        int j;
        int id = 0;
        for (int p = 1; p < array.length; p++) {
            int tmp = array[p];
            for (j = p; j > 0 && tmp > array[j - 1]; j--) {
                array[j] = array[j - 1];
                System.out.print(++id + " ");
                print(array);
            }
            array[j] = tmp;
        }
        return array;
    }

    private static void print(int[] array) {
        System.out.println(Arrays.stream(array).mapToObj(String::valueOf)
                .collect(Collectors.joining(",")));
    }

    public static void main(String[] args) {
        print(unSort);
        print(maoPaoSort(unSort.clone()));
        System.out.println();
        print(unSort);
        print(maoPaoSort1(unSort.clone()));
        System.out.println();
        print(unSort);
        print(maoPaoSort2(unSort.clone()));
        System.out.println();
        print(unSort);
        print(insertSort(unSort.clone()));
    }
}
