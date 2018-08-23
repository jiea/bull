package com.jiea.bull.common;

public class BubbleSort {

    public static void main(String[] args) {
        int[] array = {5, 8, 6, 3, 9, 2, 1, 7};

        int a;
        for (int i = 0; i < array.length; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[i] > array[j]) {
                    a = array[i];
                    array[i] = array[j];
                    array[j] = a;
                }
            }

        }
        for (int k = 0; k < array.length; k++) {
            System.out.print(array[k] + ",");
        }
    }
}
