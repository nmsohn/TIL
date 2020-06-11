class DFS{
    private int V;
    private ArrayList<ArrayList<Integer>> dfsGraph;
    private boolean[] visitArr;

    public DFS(int V){
        this.V = V;
        this.dfsGraph = new ArrayList<ArrayList<Integer>>();

        for(int i = 0; i < this.V + 1; i++){
            this.DFS.add(new ArrayList<Integer>());
        }

        this.visitArr = new boolean[this.V + 1];
    }

    public ArrayList<ArrayList<Integer>> getGraph(){
        return this.dfsGraph;
    }

    public ArrayList<Integer> getNode(int i){
        return this.dfsGraph.get(i);
    }

    public void printGraphToAdjList() {
        for(int i=1; i<this.dfsGraph.size(); i++) {
            System.out.print("정점 " + i + "의 인접리스트");
            
            for(int j=0; j<this.dfsGraph.get(i).size(); j++) {
                System.out.print(" -> " + this.dfsGraph.get(i).get(j));
            }
            System.out.println();
        }
    }
    
    public void clearVisitArr(){
        for(int i=0; i<this.visitArr.length; i++){
            this.visitArr[i] = false;
        }
    }

    public void dfs(int idx){
        this.visitArr[idx] = true;
        System.out.print(idx + " ");

        for(int i : this.dfsGraph.get(idx)){
            if(this.visitArr[i] == false){
                dfs(i);
            }
        }
    }
}

public class DFSAdjList {
    public static void main(String[] args) {
        // int v = 0;    // 정점 (수동입력에 사용되는 변수)
        // int e = 0;    // 간선 (수동입력에 사용되는 변수)
        int nV = 0;    // 정점의 개수
        int nE = 0;    // 간선의 개수
        
        Scanner sc = new Scanner(System.in);
        nV = sc.nextInt();    // 정점
        nE = sc.nextInt();    // 간선
        
        // 입력받은 정점의 개수로 그래프 초기화
        DfsGraph dfsGraph = new DfsGraph(nV);
        
        // 그래프에 정점과 간선 입력
        // 입력받은 간선의 개수만큼 반복
        // ex) 정점 8, 간선 10
        dfsGraph.put(1, 2);
        dfsGraph.put(1, 3);
        dfsGraph.put(2, 4);
        dfsGraph.put(2, 5);
        dfsGraph.put(3, 6);
        dfsGraph.put(3, 7);
        dfsGraph.put(4, 8);
        dfsGraph.put(5, 8);
        dfsGraph.put(6, 8);
        dfsGraph.put(7, 8);
        
        // 정점과 간선 수동 입력
        /*for(int i=0; i<nE; i++) {
            v = sc.nextInt();
            e = sc.nextInt();
            dfsGraph.put(v, e);
        }*/
        
        sc.close();
        
        // 입력한 정점과 간선으로 구성된 인접리스트 출력
        dfsGraph.printGraphToAdjList();
        
        // 정점 순서대로 그래프 탐색
        System.out.println();
        System.out.print("정점 1부터 탐색 : ");
        dfsGraph.dfs(1);
        
        System.out.println();
        System.out.print("정점 2부터 탐색 : ");
        dfsGraph.clearVisitArr();
        dfsGraph.dfs(2);
        
        System.out.println();
        System.out.print("정점 3부터 탐색 : ");
        dfsGraph.clearVisitArr();
        dfsGraph.dfs(3);
        
        System.out.println();
        System.out.print("정점 4부터 탐색 : ");
        dfsGraph.clearVisitArr();
        dfsGraph.dfs(4);
        
        System.out.println();
        System.out.print("정점 5부터 탐색 : ");
        dfsGraph.clearVisitArr();
        dfsGraph.dfs(5);
        
        System.out.println();
        System.out.print("정점 6부터 탐색 : ");
        dfsGraph.clearVisitArr();
        dfsGraph.dfs(6);
        
        System.out.println();
        System.out.print("정점 7부터 탐색 : ");
        dfsGraph.clearVisitArr();
        dfsGraph.dfs(7);
        
        System.out.println();
        System.out.print("정점 8부터 탐색 : ");
        dfsGraph.clearVisitArr();
        dfsGraph.dfs(8);
    }
}