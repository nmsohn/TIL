## [Problem](https://leetcode.com/problems/majority-element/)
Given an array of size n, find the majority element. The majority element is the element that appears **more than** `⌊ n/2 ⌋` times.

You may assume that the array is non-empty and the majority element always exist in the array.

**Example 1:**

```
Input: [3,2,3]
Output: 3
```

**Example 2:**

```
Input: [2,2,1,1,1,2,2]
Output: 2

```

## Solution

```csharp
public class Solution {
    public int MajorityElement(int[] nums) {
        Dictionary<int, int> hash = new Dictionary<int, int>();
        for(int i =0; i<nums.Length; i++){
            if(!hash.ContainsKey(nums[i]))
            {
                hash[nums[i]] =1;
            }else{
                hash[nums[i]]++;
            }
        }
        foreach(var h in hash)
        {
            if(h.Value > nums.Length/2)
            {   
                return h.Key;
            }
        }
        return -1;
    }
}
```

TC: O(n)
SC: Hash = n/2, Array = n -> (n - n/2) -> O(n)