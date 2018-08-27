package com.jiea.bull.common;

import java.util.Arrays;

/**
 * 冒泡排序算法 https://mp.weixin.qq.com/s/nyUumQ6mp2PPqdzpzE6C7Q
 */
public class BubbleSort1 {

    /**
     * 一般写法
     *
     * @param array
     */
    private static void sort(int[] array) {
        int tmp;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length - 1; j++) {
                if (array[j] > array[j + 1]) {
                    tmp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = tmp;
                }
            }
            System.out.println("第" + (i + 1) + "轮 :");
            for (int k = 0; k < array.length; k++) {
                System.out.print(array[k] + ",");
            }
            System.out.println();
        }

        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + ",");
        }
    }

    /**
     * 优化1（如果在某轮元素没有交换，则说明序列已经有序，直接跳出外层循环。）
     *
     * @param array
     */
    private static void sort1(int[] array) {
        int tmp;
        for (int i = 0; i < array.length; i++) {
            boolean flag = true;
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    flag = false;
                    tmp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = tmp;
                }
            }
            if (flag) {
                break;
            }
            System.out.println("第" + (i + 1) + "轮 :");
            for (int k = 0; k < array.length; k++) {
                System.out.print(array[k] + ",");
            }
            System.out.println();
        }
    }

    /**
     * 优化2（如果序列是有序的，则不再比较大小。）
     *
     * @param array
     */
    private static void sort2(int[] array) {
        int tmp;
        int lastExchangeIndex = 0;
        int sortBorder = array.length - 1;
        for (int i = 0; i < array.length; i++) {
            boolean flag = true;
            for (int j = 0; j < sortBorder; j++) {
                if (array[j] > array[j + 1]) {
                    flag = false;
                    tmp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = tmp;

                    lastExchangeIndex = j;
                }
            }
            sortBorder = lastExchangeIndex;
            if (flag) {
                break;
            }
            System.out.println("第" + (i + 1) + "轮 :");
            for (int k = 0; k < array.length; k++) {
                System.out.print(array[k] + ",");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[] i = {9, 9, 2, 4, 5, 6, 7, 8};
        sort2(i);
        System.out.println(Arrays.toString(i));
    }
}
