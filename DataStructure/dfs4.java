public class DFS{
    static int V;
    static int start;
    static int[][] map;
    static int[] visited;
    static int v1, v2;

    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        V = scan.nextInt();
        start = Integer.parseInt(scan.nextLine().trim());
        map = new int[V + 1][V + 1];
        visited = new int[V + 1];

        while(true){
            v1 = scan.nextInt();
            v2 = scan.nextInt();

            if(v1 < 0 && v2 < 0) break;

            map[v1][v2] = map[v2][v1] = 1;
        }
    }

    public static void dfs(int start){
        visit[start] = 1;

        for(int i = 1; i <= V; i++){
            if(map[start][i] == 1 && visited[i] == 0){
                System.out.println(start + "->" + i + "로 이동");
                dfs(i);
            }
        }
    }

}