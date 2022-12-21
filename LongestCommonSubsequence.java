import java.lang.*;
import java.util.Scanner;
public class LongestCommonSubsequence
{
    public static void main(String[] args)
    {
	Scanner in = new Scanner(System.in);
	String X = in.nextLine();
	String Y = in.nextLine();
	int m = X.length(),n = Y.length();
	int[][] C = new int[m+1][n+1];
	LCS_BottomUp(X,Y,m,n,C);
	PrintLCS(X,Y,m,n,C);
	C = null;
	in.close();	
        return;
    }
    public static int LCS_TopDown(String X,String Y,int m,int n,int[][] C)
    {
        if(m < 0 || n < 0)return 0;
        if(C[m][n] != -1)return C[m][n];
        if(X.charAt(m) == Y.charAt(n))
            C[m][n] = 1+LCS_TopDown(X,Y,m-1,n-1,C);
        else
            C[m][n] = Math.max(LCS_TopDown(X,Y,m-1,n,C),LCS_TopDown(X,Y,m,n-1,C));
        return C[m][n]; 
    }
    public static int LCS_BottomUp(String X,String Y,int m,int n,int[][] C)
    {
	    //LCS(Xm,Yn) is stored in C[m+1][n+1] due to zero base indexing
	    for(int i=0;i<=m;i++)
	    {
		    for(int j=0;j<=n;j++)
		    {
			if(i == 0 || j == 0)
				C[i][j] = 0;
			else if(X.charAt(i-1) == Y.charAt(j-1))
				C[i][j] = 1 + C[i-1][j-1];
			else
				C[i][j] = Math.max(C[i][j-1],C[i-1][j]);
		    }
	    }
	    return C[m][n];
    }
    public static void PrintLCS(String X,String Y,int i,int j,int[][] C)
    {	
    	if(i == 0 || j == 0)return;	    
	else if(X.charAt(i-1) == Y.charAt(j-1))
	{
		PrintLCS(X,Y,i-1,j-1,C);
		System.out.print(X.charAt(i-1));
	}
	else //Check which subproblem gave the optimal solution
	{	
		if(C[i][j] == C[i-1][j])
			PrintLCS(X,Y,i-1,j,C);
		else
			PrintLCS(X,Y,i,j-1,C);
	}
	return;
    }
}
