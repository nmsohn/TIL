## [Problem](https://leetcode.com/problems/valid-parentheses/)

Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

An input string is valid if:

1. Open brackets must be closed by the same type of brackets.
2. Open brackets must be closed in the correct order.
Note that an empty string is also considered valid.

## Example 1:
```
Input: "()"
Output: true
```

## Example 2:
```
Input: "()[]{}"
Output: true
```

## Example 3:
```
Input: "(]"
Output: false
```

## Example 4:
```
Input: "([)]"
Output: false
```

## Example 5:
```
Input: "{[]}"
Output: true
```

## Approach
There are some considerations to be taken.
1. s should start with either (, {, [
2. s should include even number of characters

Solving it using ASCII difference.

## Solution
```
public class Solution {
    public bool IsValid(string s) {
        if(s.Length % 2 != 0) return false;
        char[] arr = s.ToCharArray();
        Stack<char> st = new Stack<char>();
        foreach(char c in arr){
            if(c == '(' || c == '{' || c== '[') st.Push(c);
            else{
                if(st.Count == 0 || (c-st.Peek() !=1 &&  c-st.Peek()!=2)) return false;
                st.Pop();
            }
        }
        if(st.Count == 0) return true;
        else return false;
    }
}
```