public class BackTracking{
    static int tile[];
    static int n;
    static int answer;

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();

        for(int i =1; i <= n; i++){
            tile = new int[n+1];
            tile[1] = i;

            dfs(2);
        }
        System.out.println(ans);
    }

    private static void dfs(int row){
        if(row > n){
            ++ans;
        }else{
            for(int i = 1; i <=n; i++){
                tile[row] = i;

                if(isPossible(row)){
                    dfs(row+1);
                }else{
                    tile[row]=0;
                }
            }
        }
    }
    private static boolean isPossible(int c){
        for(int i = 1; i<=n; i++){
            //상위 노드에서 같은 행에 퀸이 있는가?
            if(tile[i] == col[c]) return false;
            //대각선
            //가로, 세로 거리가 같은지 검사
            if(Math.abs(tile[i] - tile[c]) == Math.abs(i-c)) return false;
        }
        return true;
    }
}