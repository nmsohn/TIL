//인접행렬, visited

public class bfs{
    static int[][] adj;
    static boolean[] visited;
    static int e, v;

    public static void bfs(int i){
        Queue<Integer> q = new LinkedList<Integer>();
        q.offer(i);
        visited[i] = true;

        while(!q.isEmpty()){
            int temp = q.poll();
            System.out.print(temp);

            for(int j = 1; j <= v; j++){
                if(adj[temp][j] == 1 && visited[j] == false){
                    q.offer(j);
                    visited[j] = true;
                }
            }
        }
    }

    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        Nv = scan.nextInt();
        Ne = scan.nextInt();
        ad = new int[Nv+1][Nv+1];
        visit = new boolean[Nv+1];
        
        for(int i = 0; i < Ne; i++){
            int t1, t2;
            t1 = scan.nextInt();
            t2 = scan.nextInt();
            
            ad[t1][t2] = ad[t2][t1] = 1;
        }
        
        bfs(1);
    }
}