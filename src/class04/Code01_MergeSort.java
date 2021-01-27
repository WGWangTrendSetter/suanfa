package class04;

/**
 * 递归就是把大问题变成：范围缩小成同等定义的子问题讨论
 * Created by wwg on 2021/1/27 0027
 */
public class Code01_MergeSort {

    // 递归实现方法

    /**
     * 整体是递归，左边排好序+右边排好序+merge让整体有序
     * 时间复杂度O(N*logN)
     *
     * @param arr
     */
    public static void mergeSort1(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }

        process(arr, 0, arr.length - 1);

    }


    /*
       请把arr[L...R]排序

     */

    /**
     * @param arr
     * @param L
     * @param R
     */
    public static void process(int[] arr, int L, int R) {
        // 递归结束
        if (L == R) {
            return;
        }

        int M = L + ((R - L) >> 1);
        process(arr, L, M);
        process(arr, M + 1, R);

        merge(arr, L, M, R);
    }

    // merge过程两指针没有回退，复杂度O（N）
    public static void merge(int[] arr, int L, int M, int R) {
        int[] help = new int[R - L + 1];
        int i = 0;
        int p1 = L;
        int p2 = M + 1;

        while (p1 <= M && p2 <= R) {
            help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
        }

        while (p1 <= M) {
            help[i++] = arr[p1++];
        }

        while (p2 <= R) {
            help[i++] = arr[p2++];
        }

        for (i = 0; i < help.length; i++) {
            arr[L + i] = help[i];
        }
    }

    // 非递归实现方法，步长实现
    public static void mergeSort2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }

        int N = arr.length;
        int mergeSize = 1;
        while (mergeSize < N) {
            int L = 0;
            while (L < N) {
                int M = L + mergeSize - 1;
                if (M >= N) {
                    break;
                }

                int R = Math.min(M + mergeSize, N - 1);
                merge(arr, L, M, R);
                L = R + 1;
            }

            if (mergeSize > N / 2) {
                break;
            }
            mergeSize <<= 1;
        }
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }

    // for test
    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + ",");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 5;
        int maxValue = 10;
        System.out.println("测试开始");

//        int[] arr1 = generateRandomArray(maxSize, maxValue);
        int[] arr1 = {2,9,-1,-4};
        printArray(arr1);
        mergeSort2(arr1);
        printArray(arr1);

//        for (int i = 0; i < testTime; i++) {
//            int[] arr1 = generateRandomArray(maxSize, maxValue);
//            int[] arr2 = copyArray(arr1);
//            mergeSort1(arr1);
//            mergeSort2(arr2);
//            if (!isEqual(arr1, arr2)) {
//                System.out.println("出错了！");
//                printArray(arr1);
//                printArray(arr2);
//                break;
//            }
//        }
        System.out.println("测试结束");
    }
}
