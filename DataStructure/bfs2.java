//인접행렬, hashmap
public class bfs{
    static int e;
    static int v;
    static int[][] adj;
    static boolean[] visited;

    public static void bfs(int i){
        Queue<Integer> q = new LinkedList<Integer>();
        HashMap<Integer, Boolean> hash = new HashMAp<Integer, Boolean>();
        q.offer(i);

        while(!q.isEmpty()){
            int temp = q.poll();
            visited[temp] = true;
            System.out.print(temp);

            for(int j = 1; j <= e; j++){
                if(adj[temp][j] == 1 && visited[j] == false){
                    if(!hash.containsKey(j)){
                        q.offer(j);
                        hash.put(j, true);
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Ne = scan.nextInt();
        Nv = scan.nextInt();
        
        ad = new int[Nv+1][Nv+1];
        visit = new boolean[Nv+1];
        
        for(int i = 0; i < Nv; i++){
            int t1, t2;
            t1 = scan.nextInt();
            t2 = scan.nextInt();
            
            ad[t1][t2] = ad[t2][t1] = 1;
        }
        
        bfs(1);   
    }
}