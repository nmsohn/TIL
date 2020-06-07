def merge_sort(arr):
    if len(arr) < 2:
        return arr
    #중간점 기준으로 왼쪽, 오른쪽
    mid = len(arr) // 2
   
    low_arr = merge_sort(arr[:mid])
    high_arr = merge_sort(arr[mid:])
    return merge(low_arr, high_arr)

def merge(low_arr, high_arr):
    merged_arr = []
    l = h = 0
    while l < len(low_arr) and h < len(high_arr):
        if low_arr[l] < high_arr[h]:
            merged_arr.append(low_arr[l])
            l += 1
        else:
            merged_arr.append(high_arr[h])
            h += 1
     #  리스트 slice를 할 때 배열의 복제가 일어나므로 메모리 사용 효율은 좋지 않음
    merged_arr += low_arr[l:]
    merged_arr += high_arr[h:]
    return merged_arr

def merge2(low_arr, high_arr):
    l = h = 0
    merged_arr = []

    while(l < len(low_arr) and h < len(high_arr)):
        if low_arr[l] < high_arr[h]:
            merged_arr.append(low_arr[l])
            l +=1
        else:
            merged_arr.append(high_arr[h])
            h +=1
    while(l<len(low_arr)):
        merged_arr.append(low_arr[l])
        l +=1
    while(h<len(high_arr)):
        merged_arr.append(high_arr[h])
        h +=1
    
    return merged_arr