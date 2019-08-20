## Problem
계단 오르는 데는 다음과 같은 규칙이 있다.

1. 계단은 한 번에 한 계단씩 또는 두 계단씩 오를 수 있다. 즉, 한 계단을 밟으면서 이어서 다음 계단이나, 다음 다음 계단으로 오를 수 있다.
2. 연속된 세 개의 계단을 모두 밟아서는 안 된다. 단, 시작점은 계단에 포함되지 않는다.
3. 마지막 도착 계단은 반드시 밟아야 한다.

```
1. A user can only take one step or two steps at a time.
2. A user cannot take three steps. But, the first step does not count
3. A user must be on the last step
```

## Input
입력의 첫째 줄에 계단의 개수가 주어진다.
```
Number of the stairs
```

둘째 줄부터 한 줄에 하나씩 제일 아래에 놓인 계단부터 순서대로 각 계단에 쓰여 있는 점수가 주어진다.
```
Each step has a score
```
계단의 개수는 300이하의 자연수이고, 계단에 쓰여 있는 점수는 10,000이하의 자연수이다.
```
The number of steps is equal or less than 300
The score is equal or less than 10,000
Both values are integer
```

## Output
첫째 줄에 계단 오르기 게임에서 얻을 수 있는 총 점수의 최댓값을 출력한다.
```
Find the max sum of the score
```

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

## Recurrence relation Equation
### Scenario 1
If a user stepped on step (n-1), the user could have not step on step (n-2).
dp[n] = dp[n-3] + stairs[n-1] + stairs[n]

### Scenario 2
If a user did not step on step (n-1)
dp[n] = dp[n-2] + stairs[n]

Compare the two scenarios, find the maximum score.

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
