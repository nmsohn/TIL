class Node:
    item = -1
    next = None

    def __init__(self, item):
        self.item = item

class Queue:
    front = None
    rear = None

    def __init__(self):
        self.front = None
        self.rear = None
    
    def enqueue(self, item):
        new_node = Node(item)

        if self.is_empty():
            self.rear = new_node
            self.front = new_node
        else:
            self.rear.next = new_node
            self.rear = new_node
    
    def dequeue(self):
        if self.is_empty():
            return
        else:
            delete_node = self.front
            self.front = self.front.next
            if self.front is None:
                self.rear = None
    
    def is_empty(self):
        if self.rear is None:
            return True
        return False
    
    def print_queue(self):
        temp = self.front
        while temp is not None:
            print(temp.item)
            temp = temp.next