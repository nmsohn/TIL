import java.util.Scanner;
 
public class BubbleSort4 {
    
    static void swap1(int[] array, int idx1, int idx2){ // j, j+1
        for(int i=0; i<array.length; i++){
            if(idx1 == i){
                System.out.print(array[i]+"+");
            }else{
                System.out.print(array[i]);
            }
        }
        System.out.println();        
        int t = array[idx2];
        array[idx2] = array[idx1];
        array[idx1] = t;        
    }
 
    // a[idx1]와 a[idx2]의 갑을 바꾼다.
    static void swap2(int[] array, int idx1, int idx2){
        for(int i=0; i<array.length; i++){
            if(idx1 == i){
                System.out.print(array[i]+"+");
            }else{
                System.out.print(array[i]);
            }
        }
        System.out.println();        
        int t = array[idx1];
        array[idx1] = array[idx2];
        array[idx2] = t;
    }
    // 8 9 1 3 4 5 6
    // 1 3 4 8 7 9 2
    // 9 1 3 4 6 7 8 배열이 있다면
    // 두번째 요소부터 정렬되어있지만 bubbleSort3 메소드 사용시 빠른시간안에 정렬작업을 할수 없음.
    // 맨 앞에 있는 요소의 값(9)는 1회의 패스를 거쳐도 한칸씩만 뒤로 옮겨지기 때문
    // 홀수 번째 패스는 가장 작은 요소를 맨 앞으로 옮기고 짝수번째 패스는 가장 큰 요소를 맨 뒤로 옮기는 방식 사용
    // 칵테일 정렬, 쉐이커 정렬
    static void bubbleSort4(int[] array, int arrayLength){
        int k=0;
        int pathCount = 0;
        while(k<arrayLength-1){ // 0<6 , 3<6, 4<6
            int last = arrayLength-1; // last = 6W
            if(k%2 == 0){ // 짝수
                pathCount++;
                System.out.println("짝수패스" + pathCount);
                for(int j= 0; j<arrayLength-1; j++){ // 5 6
                    if(array[j] > array[j+1]){ // array[0] > array[1]
                        swap1(array, j, j+1);
                        last = 1;
                    }
                }
            }else{    // 홀수
                pathCount++;
                System.out.println("홀수패스" + pathCount);
                for(int j= arrayLength-1; j>=k; j--){ // j=6, 6>1; j--;
                    if(array[j-1] > array[j]){ // array[5] > array[6]
                        swap2(array, j-1, j);
                        last = 0; 
                    }
                }
            }            
            k = last; // k=3 // k= 4 // k=5 // k=6
        }
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("버블 정렬 방법 4");
        System.out.print("배열의 길이: ");
        int arrayLength = scanner.nextInt();
        int[] array = new int[arrayLength];
        for(int i=0; i<arrayLength; i++){
            System.out.print("array[" + i + "] :");
            array[i] = scanner.nextInt();
        }
        
        bubbleSort4(array, arrayLength);        // 배열 x를 버블 정렬 한다.
        System.out.println("오름차순 정렬완료");
        for(int i=0; i<arrayLength; i++){
            System.out.print(array[i]);
        }
        System.out.println();
    }
}
