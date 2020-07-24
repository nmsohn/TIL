class Queue:
    front = 0
    rear = 0
    size = 0
    queue = []

    def __init__(self, size):
        self.queue = [-1 for i in range(0,size)]
        self.size = size
        self.front = 0
        self.rear = 0
    
    def is_empty(self):
        if self.rear is self.front:
            return True
        return False
    
    def is_full(self):
        if(self.rear + 1) % size is self.front:
            return True
        return False

    def enqueue(self, item):
        if self.is_full():
            return
        self.rear = (self.rear + 1) % self.size
        self.queue[self.rear] = item

    def dequeue(self):
        if self.is_empty():
            return
        self.front = (self.front + 1) % self.size
        self.queue[self.front] = -1
        