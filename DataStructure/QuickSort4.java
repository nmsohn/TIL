public class QuickSort {
    private static void quickSort(int[] arr, int start, int end){
        int left = start;
        int right = end;
        int pivot = arr[left + (left+start)/2];

        do{
            //왼쪽에 있는 값이 pivot보다 작다면
            while(arr[left] < pivot) left++; //위치를 중앙으로 계속 이동
            //오른쪽에 있는 값이 pivot보다 크다면
            while(arr[right] > pivot) right--; //위치를 중앙으로 계속 이동
            //오른쪽 값이 더 크다면
            if(left <= right){
                //ㅣleft 에 있는 값과 right에 있는 값을 교환
                int temp = arr[left];
                arr[left] = arr[right];
                arr[right] = temp;
                left++;
                right--;
            }
        }while(left <= right); //왼쪽의 위치가 오른쪽 위치보다 작을 때까지 진행

        //순환
        if(start < right) quickSort(data, start, right);
        if(end > left) quickSort(data, left, end);
    }
}