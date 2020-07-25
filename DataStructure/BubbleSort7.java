import java.util.Scanner;

public class BubbleSort {
    static void swap(int[] arr, int x, int y){
        for(int i =0; i <arr.length; i++){
            if(x == i){
                System.out.print(arr[i] + "+");
            }else{
                System.out.print(arr[i]);
            }
        }

        System.out.println();
        int temp = arr[x];
        arr[x] = arr[y];
        arr[y] = temp;

        if(x == 0){
            for(int i=0; i<arr.length; i++){
                System.out.print(arr[i]);
            }
            System.out.println();
        }
    }

    static void bubbleSort(int[] arr, int arrayLength){
        int k = 0;
        while(k < arrayLength - 1){
            int last = arrayLength - 1;
            for(int j = arrayLength -1; j > k; j--){
                if(arr[j-1] > arr[j]){
                    swap(arr, j-1, j);
                    last = j;
                }
            }
            k = last;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("버블 정렬 방법 3");
        System.out.print("배열의 길이: ");
        int arrayLength = scanner.nextInt();
        int[] array = new int[arrayLength];
        for(int i=0; i<arrayLength; i++){
            System.out.print("array[" + i + "] :");
            array[i] = scanner.nextInt();
        }
        
        bubbleSort3(array, arrayLength);        // 배열 x를 버블 정렬 한다.
        System.out.println("오름차순 정렬완료");
        for(int i=0; i<arrayLength; i++){
            System.out.print(array[i]);
        }
        System.out.println();
    }
}