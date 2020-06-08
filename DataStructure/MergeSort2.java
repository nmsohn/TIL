public class MergeSort{
    public static void mergeSort(int[] arr){
        sort(arr, 0, arr.length);
    }

    private static void sort(int[] arr, int l, int h){
        if(h - l < 2) return;

        int mid = (l + h) /2;
        sort(arr, 0, mid);
        sort(arr, mid, h);
        merge(arr, l, mid, h);
    }

    private static void merge(int[] arr, int l, int mid, int h){
        int[] temp = new int[h - l];
        int t = 0, low = l, high = mid;

        while(low < mid && high < h){
            if(arr[low] < arr[high]){
                temp[t++] = arr[low++];
            }else{
                temp[t++] = arr[high++];
            }
        }

        while(low < mid){
            temp[t++] = arr[low++];
        }

        while(high < h){
            temp[t++] = arr[high++];
        }

        for(int i = l; i < h; i++){
            arr[i] = temp[i - l];
        }
    }
}