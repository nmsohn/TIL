//인접리스트, hashmap

public class bfs{
    static ArrayList<ArrayList<Integer>> adj;
    static boolean[] visited;
    static int e, v;

    public static void bfs(int i){
        Queue<Integer> q = new LinkedList<Integer>();
        HashMap<Integer, Boolean> hash = new HashMap<Integer, Boolean>();

        q.offer(i);

        while(!q.isEmpty()){
            int temp = q.poll();
            visited[temp] = true;
            System.out.print(temp);

            for(int j : adj.get(temp)){
                if(visited[j] == false && !hash.containsKey(j)){
                    q.offer(j);
                    hash.put(j, true);
                }
            }
        }
    }
     public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Nv = scan.nextInt();
        Ne = scan.nextInt();
        ad = new <ArrayList<Integer>> ArrayList(Nv+1);
        visit = new boolean[Nv+1];
        
        for(int i = 0; i <= Nv+1; i++){
            ad.add(new ArrayList());
        }
        
        for(int i = 0; i < Ne; i++){
            int t1, t2;
            t1 = scan.nextInt();
            t2 = scan.nextInt();
            
            ad.get(t1).add(t2);
            ad.get(t2).add(t1);    
        }
        
        bfs(1);        
    }
}