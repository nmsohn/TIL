## Sample Input
```
6
1 4 3 5 6 2
```

## Sample Output
```
1 4 3 5 6 2 
1 3 4 5 6 2 
1 3 4 5 6 2 
1 3 4 5 6 2 
1 2 3 4 5 6 
```

## Solution
```
    static void insertionSort2(int n, int[] arr) {
        int j=0;
        for(int i=1; i<n; i++){
            int temp = arr[i];
            for(j=i-1; j>=0; j--){
              if(arr[j] > temp){
                arr[j+1] = arr[j];
              }else{
                  break;
              }
            }
            arr[j+1] = temp;
            for (int k : arr) {
                System.out.print(k + " ");
            }
            System.out.println("");
        }
    }
```