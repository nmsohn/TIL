class Graph {
    private int V; //노드의 개수
    private LinkedList<Integer> adj[];

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
        visited[v] = true;
        System.out.print(v + " ");

        Iterator<Integer> i = adj[v].listIterator();
        while(i.hasNext()){
            int n = i.next();
            if(!visited[n]){
                DFSHelper(n, visited);
            }
        }
    }

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
}