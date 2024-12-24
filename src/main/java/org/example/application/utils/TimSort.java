package org.example.application.utils;

import java.util.Comparator;
import java.util.List;
import java.util.ListIterator;

public class TimSort {
    private static final int MIN_RUN_SIZE = 4;

    public static <T> void run(List<T> list, Comparator<? super T> comparator) {
        Object[] array = list.toArray();
        sort(array, (Comparator) comparator);
        ListIterator<T> i = list.listIterator();
        for (Object element : array) {
            i.next();
            i.set((T) element);
        }
    }

    private static <T> void sort(T[] array, Comparator<? super T> comparator) {
        for (int start = 0; start < array.length; start += MIN_RUN_SIZE) {
            int end = Math.min((start + MIN_RUN_SIZE - 1), (array.length - 1));
            sort(array, comparator, start, end);
        }

        for (int runSize = MIN_RUN_SIZE; runSize < array.length; runSize *= 2) {
            for (int left = 0; left < array.length; left += 2 * runSize) {
                int mid = left + runSize - 1;
                int right = Math.min((left + 2 * runSize - 1), (array.length - 1));
                if (mid < right) merge(array, comparator, left, mid, right);
            }
        }
    }

    private static <T> void sort(T[] array, Comparator<? super T> comparator, int start, int end) {
        for (int i = start; i <= end; i++) {
            int j = i;
            while (j > 0 && comparator.compare(array[j], (array[j - 1])) < 0) {
                T temp = array[j];
                array[j] = array[j - 1];
                array[j - 1] = temp;
                j--;
            }
        }
    }

    private static <T> void merge(T[] array, Comparator<? super T> comparator, int low, int middle, int high) {
        T[] leftArray = (T[]) new Comparable[middle - low + 1];
        T[] rightArray = (T[]) new Comparable[high - middle];

        System.arraycopy(array, low, leftArray, 0, leftArray.length);
        System.arraycopy(array, middle + 1, rightArray, 0, rightArray.length);

        int leftSubArrCounter = 0;
        int rightSubArrCounter = 0;
        int arrCounter = low;
        while (leftSubArrCounter < leftArray.length && rightSubArrCounter < rightArray.length) {
            array[arrCounter++] = comparator.compare(leftArray[leftSubArrCounter], rightArray[rightSubArrCounter]) <= 0
                    ? leftArray[leftSubArrCounter++]
                    : rightArray[rightSubArrCounter++];
        }

        while (leftSubArrCounter < leftArray.length) {
            array[arrCounter++] = leftArray[leftSubArrCounter++];
        }

        while (rightSubArrCounter < rightArray.length) {
            array[arrCounter++] = rightArray[rightSubArrCounter++];
        }

    }
}
