## [Problem](https://leetcode.com/problems/zigzag-conversion/)
The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)

```
P   A   H   N
A P L S I I G
Y   I   R
```

And then read line by line: "PAHNAPLSIIGYIR"

Write the code that will take a string and make this conversion given a number of rows:

```
string convert(string s, int numRows);
```

## Example 1:

```
Input: s = "PAYPALISHIRING", numRows = 3
Output: "PAHNAPLSIIGYIR"
```

## Example 2:

```
Input: s = "PAYPALISHIRING", numRows = 4
Output: "PINALSIGYAHRPI"

Explanation:

P     I    N
A   L S  I G
Y A   H R
P     I
```

## Solution

There is a arithmetic sequence found in the first and the last rows. The common difference here is 2(rows-1).

There is a regular pattern also found in other rows which is 2(rows-1) - 2(i).

```
class Solution {
    public String convert(String s, int numRows) {
        int diff = 2 * (numRows-1); //common difference
        int len = s.length();
        StringBuilder sb = new StringBuilder(); 
        
        if(numRows == 1) return s;
        if(len == 0) return "";
        
        for(int i=0; i < numRows; i++){ //rows
            for(int j=i; j<len; j+=diff){ //cols
                sb.append(s.charAt(j));
                if(i > 0 && i < numRows -1){ //not the first row or the last
                int diff2 = diff - 2 * i + j;
                    if(diff2 < len) sb.append(s.charAt(diff2));
                }
            }
        }
        
        return sb.toString(); 
    }
}

//Time Complexity: O(len)
```