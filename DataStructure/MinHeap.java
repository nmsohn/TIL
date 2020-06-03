class MinHeap{
    public static class minHeap{
        private int[] heap;
        private int size;
        private int maxsize;

        private static final int FRONT = 1;

        public minHeap(int maxzie){
            this.maxsize = maxsize;
            this.size = 0;
            heap = new int[this.maxsize + 1];
            heap[0] = Integer.MIN_VALUE;
        }

        private int parent(int pos){
            //현재 위치의 부모 노드 위치
            return pos/2;
        }

        private int leftChild(int pos){
            return pos * 2;
        }

        private int rightChild(int pos){
            return (2 * pos) +1;
        }

        private boolean isLeaf(int pos){
            //자식 노드가 없는 경우
            if(pos > (size/2) && pos <=size){
                return true;
            }
            return false;
        }

        private void swap(int fpos, int spos){
            int tmp;
            tmp = heap[fpos];
            heap[fpos] = heap[spos];
            heap[spos] = tmp;
        }

        private void minHeapify(int pos){
            //자식 노드 있을 때
            if(isLeaf(pos)) return;
            //자식 노드 값보다 클 때
            if(heap[pos] > heap[leftChild(pos)] || heap[pos] > heap[rightChild(pos)])
            {
                //왼쪽 노드가 작으면
                if(heap[leftChild(pos)] < heap[rightChild(pos)]){
                    //현재 노드와 왼쪽 노드 교환
                    swap(pos, leftChild(pos));
                    //현재 노드 = 왼쪽 노드
                    minHeapify(leftChild(pos));
                }else{
                    //오른쪽 노드가 작으면
                    swap(pos, rightChild(pos));
                    minHeapify(rightChild(pos));
                }
            }
        }

        //push down, sift down
        private void minHeapify2(int pos){
            int smallestChild;
            while(!isLeaf(pos)){
                smallestChild = leftChild(pos);
                if((smallestChild < size) && (heap[smallestChild] > heap[smallestChild+1])){
                    smallestChild = smallestChild +1;
                }

                if(heap[pos] <= heap[smallestChild]) return;
                swap(pos, smallestChild);
                pos = smallestChild;
            }
        }

        public remove2(){
            swap(1, size);
            size--;
            if(size != 0){
                minHeapify2(1);
            }
            return heap[size+1];
        }

        public void print(){
            int i;
            for(i=1, i<=size; i++){
                System.out.print(heap[i] + " ");
            }
            System.out.println();
        }

        public void insert(int elm){
            size++;
            heap[size] = elm;
            int current = size;

            while(heap[current] < heap[parent(current)]){
                //자식 노드가 부모 노드보다 작으면 swap
                swap(current, parent(current));
                //현재 위치 저장
                current = parent(current);
            }
        }

        public int remove(){
            int popped = heap[FRONT];
            heap[FRONT] = heap[size--]; //가장 마지막 값을 맨 앞자리에 이동
            minHeapify(FRONT);
            return popped;
        }

        public void minHeap(){
            //층수 = size/2
            for(int pos = size/2; pos >=1; pos--){
                minHeapify(pos);
            }
        }

        public static void main(String[] args){
            System.out.println("The Min Heap is ");
            MinHeap minHeap = new MinHeap(15);
            minHeap.insert(5); 
            minHeap.insert(3); 
            minHeap.insert(17); 
            minHeap.insert(10); 
            minHeap.insert(84); 
            minHeap.insert(19); 
            minHeap.insert(6); 
            minHeap.insert(22); 
            minHeap.insert(9); 
            minHeap.minHeap();

            minHeap.print();
            System.out.println("The Min val is "+minHeap.remove());
        }

    }
}