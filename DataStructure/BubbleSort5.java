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
    }

    static void bubbleSort(int[] arr, int arrayLength){
        for(int i = 0; i < arrayLength -1; i++){
            System.out.println("패스"+ i);
            for(int j = arrayLength -1; j > i; j--){
                if(arr[j-1] > arr[j]){
                    swap(arr, j-1; j);
                }else{
                    for(int k = 0; k < arr.length; k++){
                        if(j-1 == k){
                            System.out.print(array[k]+"-");
                        }else{
                            System.out.print(array[k]);
                        }
                    }

                    System.out.println();
                }
            }
            System.out.println("패스"+i+" 정렬결과 ");
            for(int l = 0; l < arr.length; l++){
                System.out.print(array[l]);
            }
            System.out.println();
        }
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("버블 정렬 방법 1");
        System.out.print("배열의 길이: ");
        int arrayLength = scanner.nextInt();
        int[] array = new int[arrayLength];
        for(int i=0; i<arrayLength; i++){
            System.out.print("array[" + i + "] :");
            array[i] = scanner.nextInt();
        }
        bubbleSort(array, arrayLength);
        System.out.println("오름차순 정렬완료");
        for(int i=0; i<arrayLength; i++){
            System.out.print(array[i]);
        }
        System.out.println();
    }
}