public class QuickSort {
    static int partition(int[] array, int start, int end) {
        int pivot = array[start + (end - start) / 2];
        while (start <= end) {
            while (array[start] < pivot) start++;
            while (array[end] > pivot) end--;
            if (start <= end) {
                //swap
                int tmp = array[start];
                array[start] = array[end];
                array[end] = tmp;
                //다음 요소로
                start++;
                end--;
            }
        }
        return start;
    }

    static int[] quickSort(int[] array, int start, int end) {
        int p = partition(array, start, end);
        //pivot의 -1 인덱스
        if (start < p - 1)
            quickSort(array, start, p - 1);
        //pivot 인덱스
        if (p < end)
            quickSort(array, p, end);
        return array
    }

    public static void main(String[] args) {
        int[] array = {
            4,
            2,
            3,
            5,
            9
        };
        array = quickSort(array, 0, array.length - 1);
        System.out.println(Arrays.toString(array));
    }
}