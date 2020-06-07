def merge_sort(arr):
    if len(arr) < 2:
        return arr
    mid = len(arr)//2
    leftList = arr[:mid]
    rightList = arr[mid:]
    leftList = merge_sort(leftList)
    rightList = merge_sort(rightList)
    return merge(leftList, rightList)

def merge(l, h):
    result = []
    while len(l) > 0 or len(r) > 0:
        if len(l) > 0 and len(r) > 0:
            if l[0] <= r[0]:
                result.append(l[0])
                l = l[1:]
            else:
                result.append(r[0])
                r = r[1:]
        elif len(l) > 0:
            result.append(l[0])
            l = l[1:]
        elif len(r) > 0:
            result.append(r[0])
            r = r[1:]
    return result