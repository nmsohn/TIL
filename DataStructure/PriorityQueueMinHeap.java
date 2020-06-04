public static class Heap{
    int[] heap = new int[n];
    int count = 0;

    public Heap(){}

    public void push(int x){
        heap[++count] = x;
        int index = count;
        while(index > 1 && heap[index/2] > heap[index]){
            if(index == 1 || heap[index/2]<heap[index])
                break;
            int tmp = heap[index/2];
            heap[index/2] = heap[index];
            heap[index] = tmp;
            index/=2;
        }
    }

    public int pop(){
        int pop = heap[1];
        heap[1] = heap[count--];
        int index=1;
        int next;
        while(true){
            next = index*2;
            if(next < count && heap[next] > heap[next+1])
                next++;
            if(next > count || heap[next] > heap[index])
                break;
            int temp = heap[index];
            heap[index] = heap[next];
            heap[next] = tmp;
            index = next;
        }
        return pop;
    }
}