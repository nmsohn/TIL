
[문제링크](https://www.acmicpc.net/problem/1920)

## Problem
N개의 정수 A[1], A[2], …, A[N]이 주어져 있을 때, 이 안에 X라는 정수가 존재하는지 알아내는 프로그램을 작성하시오

## Input
```
5
4 1 5 2 3
5
1 3 7 9 5
```

## Output
```
1
1
0
0
1
```

## Solution (Java)
```
import java.util.Scanner;
import java.util.Arrays;

public class Main {
  public static int binarySearch(int target, int[] a){
    int low =0;
    int high = a.length-1;
    while(high >= low){
      int mid = low + (high-low)/2;
      if(a[mid] == target) return 1;
      else if(a[mid] > target) high= mid-1;
      else low = mid+1;
    }
    return 0;
  }
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int num = sc.nextInt();
    int[] a = new int[num];
    for(int i=0; i<num; i++){
      a[i] = sc.nextInt();
    }
    Arrays.sort(a);
    int num2 = sc.nextInt();
    for(int j=0; j<num2; j++){
      System.out.println(binarySearch(sc.nextInt(), a));
    }
   }
}
```

## Review
이분탐색, 퀵소트