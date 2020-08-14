public class MergeSort{
    /*
    의사코드
    mergeSort(arr[], int l, int r){
        if(l < r){
            int mid = (l+r)/2;
            mergeSort(arr, l, mid);
            mergeSort(arr, mid+l, r);
            merge(arr, l, mid, r);
        }
    }

    merge(arr[], int l, int mid, int r){
        정렬된 두 배열 (arr[l..mid]), (arr[mid+l..r])를 합병
        정렬된 하나의 배열 (arr[l..r])을 도출
    }
    */

    public static void mergeSort(int[] arr, int l, int r){
        if(l < r){
            int mid = (l+r)/2;
            mergeSort(arr, l, mid);
            mergeSort(arr, mid+1, r);
            merge(arr, l, mid, r);
        }
    }

    public static void merge(int[] arr, int l, int mid, int r){
        int i, k;
        i = k = l;
        int j = mid +1;
        int[] temp = new int[arr.length];
        while(i <= mid && j <= r){
            if(arr[i] < arr[j]){
                temp[k++] = arr[i++];
            }else{
                temp[k++] = arr[j++];
            }
        }

        while(i <= mid){
            temp[k++] = arr[i++];
        }
        while(j <= r){
            temp[k++] = arr[j++];
        }

        for(int index=l; index<k; index++){
            arr[index] = temp[index];
        }
    }

    public static void printArray(int[] arr){
        for(int i = 0; i < arr.length; i++){
            System.out.print(arr[i] + " ";)
        }
        System.out.println();
    }

    public static void main(String[] args){
        int arr[] = {6,3,3,7,1,21,3,7,2,25};
        mergeSort(arr, 0, arr.length - 1);
        printArray(arr);
    }
}