package com.y2pan.practice.sort;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Main {
    private static int[] unSort = {23,6,84,34,13,32,54,12,3,4};

    private static int[] maoPaoSort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int tmp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = tmp;
                }
            }
        }
        return array;
    }

    private static int[] maoPaoSort1(int[] array) {
        for (int i = 0; i < array.length; i++) {
            boolean flag = false;
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    flag = true;
                    int tmp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = tmp;
                }
            }
            if (!flag) {
                break;
            }
        }
        return array;
    }

    private static int[] maoPaoSort2(int[] array) {
        int k = 0;
        int flag = array.length;
        for (int i = 0; i < flag; i++) {
            k = flag;
            flag = 0;
            System.out.println(k);
            for (int j = 0; j < k - 1;  j++) {
                System.out.print(array[j] + ":" + array[j+1]+" ");
                if (array[j] > array[j + 1]) {
                    flag = j;
                    int tmp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = tmp;
                }
            }
        }
        return array;
    }

    private static int[] insertSort(int[] array) {
        int j;
        for (int p = 1; p < array.length; p++) {
            int tmp = array[p];
            for (j = p; j > 0 && tmp < array[j-1]; j--) {
                array[j] = array[j-1];
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
