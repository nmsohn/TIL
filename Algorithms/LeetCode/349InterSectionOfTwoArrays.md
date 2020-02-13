# Problem
Given two arrays, write a function to compute their intersection.

## Example 1:
```
Input: nums1 = [1,2,2,1], nums2 = [2,2]
Output: [2]
````

## Example 2:
```
Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
Output: [9,4]
```

## Note:
- Each element in the result must be unique.
- The result can be in any order.

# Solution
1. Solved using LINQ
```
public class Solution {
    public int[] Intersection(int[] nums1, int[] nums2) {
        var cleanedNums1 = nums1.Select(n => n).Distinct();
        var cleanedNums2 = nums2.Select(n => n).Distinct();
        
        return cleanedNums1.Where(n => cleanedNums2.Contains(n)).ToArray();
    }
}
```
2. for  loop
```
public class Solution {
    public int[] Intersection(int[] nums1, int[] nums2) {
        var cleanedNums1 = nums1.Select(n => n).Distinct().ToList();
        var cleanedNums2 = nums2.Select(n => n).Distinct().ToList();
        var array = new List<int>();
        
        foreach(var n in cleanedNums1)
        {
            if(cleanedNums2.Contains(n))
            {
                array.Add(n);
            }
        }
        
        return array.ToArray();
    }
}
```
