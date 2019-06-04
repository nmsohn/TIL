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