## Problem
Given n non-negative integers a1, a2, ..., an , where each represents a point at coordinate (i, ai). n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0). Find two lines, which together with x-axis forms a container, such that the container contains the most water.

## Example:
```
Input: [1,8,6,2,5,4,8,3,7]
Output: 49
```

## Solution
```
public class Solution {
    public int MaxArea(int[] height) {
        int max = 0;
        int i =0;
        int j = height.Length -1;
        while(i<j){
            int h = Math.Min(height[j], height[i]);
            int w = j - i;
            int area = h * w;
            if(max < area) {max = area;}
            if(height[j]> height[i]) i++;
            else j--;
        }
        return max;
    }
}
```