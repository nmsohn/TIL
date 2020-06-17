class Graph {
    private int V; //노드의 개수
    private LinkedList<Integer> adj[]; //인접리스트
    //ArrayList와 차이 비교

    Graph(int v){
        V = v;
        adj = new LinkedList[v];
        for(int i = 0; i < v; ++i)
            adj[i] = new LinkedList();
    }

    void addEdge(int v, int w){
        adj[v].add(w);
    }

    void DFSHelper(int v, boolean visited[]){
        //방문 표시
        visited[v] = true;
        System.out.print(v + " ");
        //방문한 노드와 인접한 노드 가져오기
        Iterator<Integer> i = adj[v].listIterator();
        while(i.hasNext()){
            int n = i.next();
            if(!visited[n]){
                //재귀 호출
                DFSHelper(n, visited);
            }
        }
    }

    //v부터 탐색
    void DFS(int v){
        boolean visited[] = new boolean[V];
        DFSHelper(v, visited);
    }

    void DFS(){
        boolean visited[] = new boolean[V];
        for(int i = 0; i < V; ++i){
            if(visited[i] == false){
                DFSHelper(i, visited);
            }
        }
    }

    public static void main(String args[]) {
        Graph g = new Graph(4);

        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 0);
        g.addEdge(2, 3);
        g.addEdge(3, 3);

        g.DFS(2); /* 주어진 노드를 시작 노드로 DFS 탐색 */
        g.DFS(); /* 비연결형 그래프의 경우 */
    }
}