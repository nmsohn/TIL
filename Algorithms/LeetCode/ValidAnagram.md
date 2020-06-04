## [Problem](https://leetcode.com/problems/valid-anagram/)
Given two strings s and t , write a function to determine if t is an anagram of s.

## Example 1:
```
Input: s = "anagram", t = "nagaram"
Output: true
```
## Example 2:
```
Input: s = "rat", t = "car"
Output: false
```

## Note:
You may assume the string contains only lowercase alphabets.

## Follow up:
What if the inputs contain unicode characters? How would you adapt your solution to such case?

## Solution:
```
public class Solution {
    public bool IsAnagram(string s, string t) {
        byte[] an1 = Encoding.ASCII.GetBytes(s);
        byte[] an2 = Encoding.ASCII.GetBytes(t);
        if(an1.Count() != an2.Count())
            return false;
        Array.Sort(an1);
        Array.Sort(an2);
        
        return Enumerable.SequenceEqual(an1, an2);
    }
}
```