public class BubbleSort {
    public static void sort(int[] arr){
        for(int i = arr.length -1; i > 0; i--){
            boolean swapped = false; // swap 여부를 체크
            for(int j=0; j < i; j++){
                if(arr[j] > arr[j+1]){
                    swap(arr, i, j+1);
                    swapped = true;
                }
            }
            if(!swapped) break;
        }
    }

    private static void swap(int[] arr, int a, int b) {
        int tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
    }
}