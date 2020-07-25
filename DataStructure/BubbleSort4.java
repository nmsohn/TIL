public class BubbleSort {
    public static void srot(int[] arr){
        int end = arr.length - 1;
        while(end > 0){
            int last_swap = 0;
            for(int i =0; i < end; i++){
                if(arr[i] > arr[i+1]){
                    swap(arr, i, i+1);
                    last_swap = i;
                }
            }
            end = last_swap; //마지막으로 비교가 있었던 index를 기억
        }
    }

    private static void swap(int[] arr, int a, int b) {
        int tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
    }
}