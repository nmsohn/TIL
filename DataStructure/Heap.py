class BinaryHeap:
	def __init__(self):
        self.heap = [0]
        self.currentSize = 0

    def swap(self, a, b):
        tmp = self.heap[a]
        self.heap[a] = self.heap[b]
        self.heap[b] = tmp

    def shiftUp(self, i):
        while i // 2 > 0:
            if self.heap[i] < self.heap[i//2]:
                swap(i//2, i)
            i = i//2
    
    def insert(self, k):
        self.heap.append(k)
        self.currentSize = self.currentSize +1
        self.shiftUp(self.currentSize)
    
    def shiftDown(self, i):
        # 왼쪽 노드가 현재 노드보다 작을 때
        while i * 2 <= self.currentSize:
            mc = self.minChild(i)
            if self.heap[i] > self.heap[mc]:
                swap(i,mc)
            i = mc
    # 작은 쪽 반환
    def minChild(self, i):
        # 오른쪽 노드가 현재 노드보다 크면
        if i * 2 + 1 > self.currentSize:
            #왼쪽 반환
            return i * 2
        else
            if self.heap[i*2] < self.heap[i*2+1]:
                return i * 2
            else:
                return i * 2 + 1
    
    def delMin(self):
        popped = self.heap[1]
        self.heap[1] = self.heap[self.currentSize]
        self.currentSize = self.currentSize - 1
        self.heap.pop()
        self.shiftDown(1)
        return popped

    def findMin(self):
        element = self.heap[1]
        return element
    
    def isEmpty(self):
        if self.currentSize == 0:
            return true
        else
            return false
    
    def size(self):
        size = currentSize
        return size
    
    def buildHeap(self, list):
        l = len(list)
        self.currentSize = len(list)
        self.heap = [0] + list[:]
        while(i > 0):
            self.shiftDown(i)
            i = i - 1
    