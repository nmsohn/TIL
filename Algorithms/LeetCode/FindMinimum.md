## [Problem](https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/)
Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

(i.e.,  [0,1,2,4,5,6,7] might become  [4,5,6,7,0,1,2]).

Find the minimum element.

You may assume no duplicate exists in the array.

## Example 1:
```
Input: [3,4,5,1,2] 
Output: 1
```

## Example 2:
```
Input: [4,5,6,7,0,1,2]
Output: 0
```

## Solution
```
class Solution {
    public int findMin(int[] nums) {
        int min = 0;
        int max = nums.length -1;
        while(min<max){
            int mid = min + (max - min)/2; //avoid overflow
            if(nums[mid] < nums[max]) max = mid;
            else min = mid+1;
        }
        return nums[min];
    }
}
```

## Note
`(max - min)>>>2` resulted in time limit exceeded