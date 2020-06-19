public class BackTracking{
    private static int v = 0;
    private static int count = 0;

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        v = sc.nextInt();

        for(int i = 1; i <= v; i++){
            //인덱스 = row, value = col
            int[] tile = new int[v+1];
            tile[1] = i; //1행 i열

            //1열
            dfs(tile, 1);
        }
        System.out.println(count);
    }

    private static void dfs(int[] tile, int row){
        //마지막에 다다랐을 때
        if(row == v){
            count++;
        }else{
            for(int i = 1; i <= v; i++){
                tile[row + 1] = i;
                //가능하면
                if(isPossible(tile, row +1)){
                    //하위 노드로 이동
                    dfs(tile, row + 1);
                }else{
                    //가지치기
                    //백트래킹
                    //값이 0이면 queen이 올 수 없음.
                    tile[row+1] = 0;
                }
            }
        }
        tile[row] = 0;
    }

    private static boolean isPossible(int[] tile, int row){
        for(int i = 1; i < row; i++){
            //같은 곳에 있을 수 없음
            if(tile[i] == tile[row]) return false;
            //대각선 위치
            if(Math.abs(i - row) == Math.abs(tile[i] - tile[row])) return false;
        }
        return true;
    }
}