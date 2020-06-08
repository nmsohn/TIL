import java.util.Arrays;
 
public class SortMain {
    
    public static void main(String[] args) {
        int[] intArr01 = { 25, 90, 11, 3, 2, 6, 9, 33, 10, 25 };
        int[] intArr02 = { 90, 111, 2, 2, 44, 5, 3, 222, 1, 2, 30, 50, 222, 10, 20, 66, 3, 30, 10, 250, 243, 565, 31, 10, 200 };
        
        // 합병 정렬
        int[] mergeSortArr = intArr02.clone();
        MergeSort mergeSort = new MergeSort();
        mergeSort.mergeSort(mergeSortArr, 0, mergeSortArr.length-1);
        
        System.out.println("Merge Sort : " + Arrays.toString(mergeSortArr));
    }
}
 
 
public class MergeSort {
    public void mergeSort(int[] arr, int start, int end) {
        // arr의 start부터 end 까지를 합병정렬하는 Method
        
        if(start >= end) {
            return;
        }
        // 1. 왼쪽 절반 합병정렬
        // 2. 오른쪽 절반 합병정렬
        // 3. 왼쪽절반 오른쪽 절반을 합친다.
        int mid = (start + end) / 2;
        this.mergeSort(arr, start, mid);
        this.mergeSort(arr, mid+1, end);
        
        // arr[start~mid], arr[mid+1 ~ end] 는 정렬이 이미 되어 있음
        this.merging(arr, start, mid, mid+1, end);
    }
    
    public void merging(int[] arr, int s1, int e1, int s2, int e2) {
        // arr의 s1~e1 이 왼쪽 절반, 
        // arr의 s2~e2 가 오른쪽 절반
        // 이둘을 합쳐서 arr의 s1~e2를 정렬된 결과로 만드는 함수
        int p, q; // 왼쪽과 오른쪽의 현재 최소값을 가리키는 변수
        int temp[] = new int[(e2-s1)+1]; //합쳐진 결과를 저장하는 임시변수 
        int temp_inx = 0;
        
        p = s1;
        q = s2;
        // p 와 q 가 모두 각각의 범위안에 있어야 한다.
        while(p <= e1 && q <= e2) {
            if(arr[p] <= arr[q]) {
                temp[temp_inx++] = arr[p++];
            }else {
                temp[temp_inx++] = arr[q++];
            }
        }
        //    한쪽 인덱스가 범위를 초과 했을때 나머지 한쪽의 값들을 전부 temp 배열에 추가한다.
        if(p > e1) { // 왼쪽은 전부 정렬된 상태 오른쪽 나머지 temp 배열에 추가
            for(int i=q; i<=e2; i++) {
                temp[temp_inx++] = arr[i]; 
            }
        }else {// 오른쪽은 전부 정렬된 상태 왼쪽 나머지 temp 배열에 추가
            for(int i=p; i<=e1; i++) {
                temp[temp_inx++] = arr[i];
            }
        }
        // temp arr이 완성됨
        // arr[s1~e2] 까지에 temp의 값을 복사
        for(int i=s1; i<=e2; i++) {
            arr[i] = temp[i-s1];
        }
    }
}