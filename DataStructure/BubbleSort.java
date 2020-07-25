public class BubbleSort{
    public void bubbleSort(int[] arr){
        for(int i = 1; i < arr.length; i++){
            for(int j =0; j < arr.length -1; j++){
                if(arr[j] > arr[j+1]){
                    SortUtil.swap(arr, j, j+1);
                }
            }
        }
        SortUtil.print(arr);
    }

    public static void main(String[] args){
        int[] list = new int[]{3,1,4,5,2};
        BubbleSort bubble new BubbleSort();
        bubble.bubbleSort(lit);
    }
}

public class SortUtil{
    public static void print(int[] list){
        for(int num : list){
            System.out.println(num + " ");
        }
    }

    public static void swap(int[] arr, int x, int y){
        int temp = arr[x];
        arr[x] = arr[y];
        arr[y] = temp;
    }
}