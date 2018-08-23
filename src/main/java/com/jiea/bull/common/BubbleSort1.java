package com.jiea.bull.common;

public class BubbleSort1 {

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
            System.out.println("第"+(i+1)+"轮 :");
            for (int k = 0; k < array.length; k++) {
                System.out.print(array[k] + ",");
            }
            System.out.println();
        }

        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + ",");
        }
    }

    private static void sort1(int[] array) {
        int tmp;
        for (int i = 0; i < array.length; i++) {
            boolean flag = true;
            for (int j = 0; j < array.length - 1; j++) {
                if (array[j] > array[j + 1]) {
                    flag = false;
                    tmp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = tmp;
                }
            }
            if(flag){
                break;
            }
            System.out.println("第"+(i+1)+"轮 :");
            for (int k = 0; k < array.length; k++) {
                System.out.print(array[k] + ",");
            }
            System.out.println();
        }

        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + ",");
        }
    }




    public static void main(String[] args) {
        int[] i = {5, 8, 6, 3, 9, 2, 1, 7};
        sort1(i);
    }
}
