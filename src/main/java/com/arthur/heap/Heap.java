package com.arthur.heap;

import java.util.Arrays;

public class Heap {

    private static int[] arr = null;

    private static void initArr() {
        arr = new int[]{53, 17, 78, 9, 45, 65, 87, 23, 8, 20};
    }

    public static void main(String[] args) {
        initArr();
        buildHeap();
        /*for (int ele : arr) {
            System.out.println(ele);
        }*/
        insert(2);
        /*for (int ele : arr) {
            System.out.println(ele);
        }*/

        heapSort();


    }

    /**
     * 建立最小堆，使用向上渗透方法
     */
    private static void buildHeap() {
        int lastEleIndex = arr.length - 1;
        int currentIndex = (lastEleIndex - 1) / 2;
        while (currentIndex >= 0) {
            shifDown(currentIndex);
            currentIndex--;
        }
    }

    public static void insert(int ele) {
        arr = Arrays.copyOf(arr, arr.length + 1);
        arr[arr.length - 1] = ele;

        int index = arr.length - 1;
        while (index > 0) {
            int parentIndex = (index - 1) / 2;
            if (arr[index] >= arr[parentIndex]) {
                break;
            }
            swap(parentIndex, index);
            index = parentIndex;
        }
    }

    public static void heapSort() {
        while (arr.length > 0) {
            int minEle = removeMin();
            System.out.println(minEle);
        }

    }

    public static int removeMin() {
        int minEle = arr[0];
        if (arr.length == 0) {
            return minEle;
        }
        arr[0] = arr[arr.length - 1];
        arr = Arrays.copyOf(arr, arr.length - 1);
        if (arr.length > 1) {
            shifDown(0);
        }
        return minEle;
    }

    public static void shifDown(int currentIndex) {

        //如果是叶子节点，则不再进行比较
        if (currentIndex > (arr.length - 2) / 2) {
            return;
        }
        //获取该元素的2个子节点中的最小元素下标
        int minChildIndex = currentIndex * 2 + 1;

        if (validEle(minChildIndex) && arr[minChildIndex] > arr[minChildIndex + 1]) {
            minChildIndex++;
        }

        //如果父节点比2个子节点中的最小值都小，说明不需要调整
        if (arr[currentIndex] < arr[minChildIndex]) {
            return;
        }

        //如果父节点大于其2个子节点中的最小元素，则与最小元素交换
        //且需要从最小元素节点继续向下调整
        swap(currentIndex, minChildIndex);
        shifDown(minChildIndex);
    }

    private static boolean validEle(int eleIndex) {
        return (eleIndex <= arr.length - 1) && (eleIndex + 1 <= arr.length - 1);
    }

    private static void swap(int fitstEleIndex, int secondEleIndex) {
        int temp = arr[fitstEleIndex];
        arr[fitstEleIndex] = arr[secondEleIndex];
        arr[secondEleIndex] = temp;
    }
}
