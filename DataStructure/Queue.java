public class Queue{
    private static class Node {
        private T data;
        private Node next;

        public Queue(T data){
            this.data = data;
        }
    }

    private Node first;
    private Node last;

    public void add(T item){
        Node t = new Node(item);

        if(last != null) last.next = t;
        last = t;
        if(isEmpty()) first = last;
    }

    public T remove(){
        if(isEmpty()) throw new NoSuchElementException();
        T data = first.data;
        first = first.next;

        if(isEmpty()) last = null;
        return data;
    }

    public T peek(){
        if(isEmpty()) throw new NoSuchElementException();
        return first.data;
    }

    public boolean isEmpty(){
        return first == null;
    }
}