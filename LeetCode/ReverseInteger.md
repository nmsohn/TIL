

## Solution
```
class Solution {
    public int reverse(int x) {
        boolean isNegative = false;
        int num = Math.abs(x);
        char[] arr = Integer.toString(num).toCharArray(); // n
        int len = arr.length;
        char temp;
        if (x < 0) isNegative = true;

        for(int i=0; i<len/2;i++){ // n/2
            temp = arr[i];
            arr[i] = arr[len -i -1];
            arr[len - i -1] = temp;
        }
        
        int result = 0;
        try{
            result = Integer.parseInt(String.valueOf(arr));
            if(isNegative == true){
                result *= -1;
            }
                return result;
            }catch(Exception e){
                return 0;
        }
    }
}

//Time complexity: O(n)
```