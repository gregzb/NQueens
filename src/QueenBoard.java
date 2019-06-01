public class QueenBoard{
    private int[][] board;
    public QueenBoard(int size){
        board=new int[size][size];
        //set the square board's size
        this.clear();
        //make everything zero
    }
    private void clear(){
        for (int i=0;i<board.length;i++){
            for (int j=0;j<board.length;j++){
                board[i][j]=0;
            }
        }
    }
    //private boolean addQueen(int r,int c){}
    //private boolean removeQueen(int r,int c){}
    public String toString(){
        String s="";
        for (int i=0;i<board.length;i++){
            s+="\n";
            for (int j=0;j<board.length;j++){
                if (board[i][j]!=-1){
                    s+="_ ";
                }
                else{
                    s+="Q ";
                }
            }
            s=s.substring(0,s.length()-1);
        }
        return s;
    }
    public boolean solve(){
        for(int i=0;i<board.length;i++){
            for(int h=0;h<board.length;h++){
                if (board[i][h]!=0){
                    throw new IllegalStateException();
                    //if states do not begin with all zeros, throw error
                }
            }
        }
        if (solution(0)){
            return true;
        }
        else{
            this.clear();
            //if no solution, clear it first
            return false;
        }
    }
    public boolean solution(int r){
        if(r==board.length){
            return true;
        }
        for (int i=0;i<board.length;i++){
            if (addQueen(r,i)){
                if (solution(r+1)){
                    return true;
                }
            }
            removeQueen(r,i);
        }
        return false;
    }
    private boolean addQueen(int r,int c){
        if (board[r][c]!=0){
            return false;
        }
        for (int i=0;i<board.length;i++){
            board[r][i]=board[r][i]+1;
            if(r!=c||(r!=i)){
                board[i][c]=board[i][c]+1;
            }
        }
        for (int i=0;i<board.length;i++){
            if(i+r<board.length&&i+c<board.length){
                board[r+i][c+i]=board[r+i][c+i]+1;
            }
            if(r-i>=0&&c-i>=0){
                board[r-i][c-i]=board[r-i][c-i]+1;
            }
            if(i+r<board.length&&c-i>=0){
                board[r+i][c-i]=board[r+i][c-i]+1;
            }
            if(r-i>=0&&i+c<board.length){
                board[r-i][c+i]=board[r-i][c+i]+1;
            }
        }
        board[r][c]=-1;
        return true;
    }
    private boolean removeQueen(int r,int c){
        if (board[r][c]!=-1){
            return false;
        }
        for (int i=0;i<board.length;i++){
            board[r][i]=board[r][i]-1;
            if(r!=c||(r!=i)){
                board[i][c]=board[i][c]-1;
            }
        }
        for (int i=0;i<board.length;i++){
            if(i+r<board.length&&i+c<board.length){
                board[r+i][c+i]=board[r+i][c+i]-1;
            }
            if(r-i>=0&&c-i>=0){
                board[r-i][c-i]=board[r-i][c-i]-1;
            }
            if(i+r<board.length&&c-i>=0){
                board[r+i][c-i]=board[r+i][c-i]-1;
            }
            if(r-i>=0&&i+c<board.length){
                board[r-i][c+i]=board[r-i][c+i]-1;
            }
        }
        board[r][c]=0;
        return true;
    }
    public int countSolutions(){
        for(int i=0;i<board.length;i++){
            for(int h=0;h<board.length;h++){
                if (board[i][h]!=0){
                    throw new IllegalStateException();
                }
            }
        }
        int answer=countingSol(0);
        this.clear();
        return answer;
    }
    public int countingSol(int r){
        if(r==board.length){
            return 1;
        }
        int count=0;
        for (int i=0;i<board.length;i++){
            if (addQueen(r,i)){
                count+=countingSol(r+1);
                removeQueen(r,i);
            }
        }
        return count;
    }
    public static void runTest(int i){
        QueenBoard b;
        int[]tests =   {1,2,3,4,5,8,14};
        int[]answers = {1,0,0,2,10,92,724};
        if(i >= 0 && i < tests.length ){
            int size = tests[i];
            int correct = answers[i];
            b = new QueenBoard(size);
            int ans  = b.countSolutions();

            if(correct==ans){
                System.out.println("PASS board size: "+tests[i]+" "+ans);
            }else{
                System.out.println("FAIL board size: "+tests[i]+" "+ans+" vs "+correct);
            }
        }
    }

    public static void main(String[] args) {
        long startTime = System.nanoTime();
        runTest(6);
        long endTime = System.nanoTime();

        double time = (endTime - startTime) * Math.pow(10, -9);
        System.out.println("Took " + time + " seconds");
    }
}