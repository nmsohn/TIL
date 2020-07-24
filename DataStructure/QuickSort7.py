def swap(arr, left, right):
    arr[left], arr[right] = arr[right], arr[left]

def pivot(arr, start, end):
    pivot_val = arr[start]
    pivot_index = start

    while start < end:
        while start <= end and arr[start] <= pivot_val
            start +=1
        while start <= right and arr[end] >= pivot_val
            end -=1
        if start <= end:
            swap(arr, start, end)
            end +=1
            start -=1
    swap(arr, pivot_index, end)
    return end

def quickSort(x, pivotMethod=pivot):
    def _qsort(x, first, last):
        if first < last:
            splitpoint = pivotMethod(x, first, last)
            _qsort(x, first, splitpoint-1)
            _qsort(x, splitpoint+1, last)
    _qsort(x, 0, len(x)-1)