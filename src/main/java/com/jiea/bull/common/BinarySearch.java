package com.jiea.bull.common;

/**
 * 二分查找法
 * 又称折半查找，主要是解决“在指定的一堆数中找到指定的数”，前提是“这堆数要有序”。
 */
public class BinarySearch {

    public static int binarySearch(int[] array, int tar) {
        int min = 0;
        int max = array.length - 1;
        while (min <= max) {
            int middle = (min + max) >> 1;
            int middleValue = array[middle];
            if (middleValue == tar) {
                return middle;
            } else if (middleValue > tar) {
                max = middle - 1;
            } else {
                min = middle + 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] i = {1,2,4,5,6,78,89,90};
        System.out.println(binarySearch(i, 5));
    }

}
