import java.util.Scanner;

public class BubbleSort{
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

    static void bubbleSort(int[] array, int arrayLength){
        for(int i=0; i<arrayLength-1; i++){
            int counter = 0; //교환횟수를 기록
            System.out.println("패스" + i);
            for(int j=arrayLength-1; j>i; j--){
                if(array[j-1] > array[j]){
                    swap(array, j-1, j);
                    counter++;
                }else{
                    for(int k=0; k<array.length; k++){
                        if(j-1 == k){
                            System.out.print(array[k]+"-");
                        }else{
                            System.out.print(array[k]);
                        }
                    }
                    System.out.println();
                }
            }
            System.out.println("교환횟수: "+counter);
            if(counter == 0) break; // 교환이 한 번도 일어나지 않았으니 종료. 이미 정렬된 상태
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("버블 정렬 방법 2");
        System.out.print("배열의 길이: ");
        int arrayLength = scanner.nextInt();
        int[] array = new int[arrayLength];
        for(int i=0; i<arrayLength; i++){
            System.out.print("array[" + i + "] :");
            array[i] = scanner.nextInt();
        }
        
        bubbleSort2(array, arrayLength);        // 배열 x를 버블 정렬 한다.
        System.out.println("오름차순 정렬완료");
        for(int i=0; i<arrayLength; i++){
            System.out.print(array[i]);
        }
        System.out.println();
    }
}