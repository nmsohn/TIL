## Problem
계단 오르는 데는 다음과 같은 규칙이 있다.

계단은 한 번에 한 계단씩 또는 두 계단씩 오를 수 있다. 즉, 한 계단을 밟으면서 이어서 다음 계단이나, 다음 다음 계단으로 오를 수 있다.
연속된 세 개의 계단을 모두 밟아서는 안 된다. 단, 시작점은 계단에 포함되지 않는다.
마지막 도착 계단은 반드시 밟아야 한다.

## Input
입력의 첫째 줄에 계단의 개수가 주어진다.

둘째 줄부터 한 줄에 하나씩 제일 아래에 놓인 계단부터 순서대로 각 계단에 쓰여 있는 점수가 주어진다. 계단의 개수는 300이하의 자연수이고, 계단에 쓰여 있는 점수는 10,000이하의 자연수이다.

## Output
첫째 줄에 계단 오르기 게임에서 얻을 수 있는 총 점수의 최댓값을 출력한다.

## Example
Input
```
6
10
20
15
25
10
20
```

Output
```
75
```

## Solution
```
import java.util.Scanner;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
    int num = sc.nextInt();
    int[] stairs = new int[num+1]; 
    int[] dp = new int[num+1];

    for(int i=1; i<=num; i++) stairs[i] = sc.nextInt();

    dp[1] = stairs[1];
    dp[2] = stairs[2] + dp[1];
    for(int j=3; j<=num;j++) dp[j] = Math.max(dp[j-2], dp[j-3]+stairs[j-1])+stairs[j];
    System.out.println(dp[num]);
	}
}
```