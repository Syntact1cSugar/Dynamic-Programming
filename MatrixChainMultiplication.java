import java.lang.*;


public class MatrixChainMultiplication
{
	public static void main(String[] args)
	{
		int[] p = {5,10,3,12,5,50,6,7,3,1,3,4,1,1};
		int[][] t = null;
		t = MATRIX_CHAIN_BOTTOM_UP(p,p.length-1,t);
		PRINT_SEQUENCE(p,0,p.length-2,t);
		return;
	}
	public static int MATRIX_CHAIN_TOP_DOWN(int[] p,int i,int j,int t[][])//Top Down Approach with memoization
	{

		if(t[i][j] != 0)return t[i][j];
		t[i][j] = ((~1)|1)^(1<<31);//Integer.MAX_VALUE
		if(i == j)t[i][j] = 0;
		else
			for(int k=i;k<j;k++)
				t[i][j] = Math.min(t[i][j],MATRIX_CHAIN_TOP_DOWN(p,i,k,t)+MATRIX_CHAIN_TOP_DOWN(p,k+1,j,t)+p[i]*p[k+1]*p[j+1]);
		return t[i][j];
	}
	public static int[][] MATRIX_CHAIN_BOTTOM_UP(int[] p,int n,int[][] t)//Tabulation Method
	{
		//t[i][j] gives the min. cost for computing product Ai....Aj
		t = new int[p.length-1][p.length-1];
		for(int i=n-1;i>=0;i--)
		{
			for(int j=i;j<n;j++)
			{
				t[i][j] = ((~1)|1)^(1<<31);
				if(i == j)t[i][j] = 0;
				else
				{
					for(int k=i;k<j;k++)
					{
						int q = t[i][k] + t[k+1][j] + p[i] * p[k+1] * p[j+1];
						t[i][j] = q < t[i][j]? q : t[i][j];
					}
				}
			}
		}
		return t;
	}
	public static void PRINT_SEQUENCE(int[] p,int i,int j,int[][] t)
	{
		if(i == j)
		{
			System.out.print("A"+i);
			return;
		}
		System.out.print('(');
		//Find the choosen subproblem
		for(int k=i;k<j;k++)
		{
			if(t[i][j] == t[i][k] + t[k+1][j] + p[i] * p[k+1] * p[j+1])
			{
				PRINT_SEQUENCE(p,i,k,t);//Optimal SubProblem 1(Ai...Ak)
				PRINT_SEQUENCE(p,k+1,j,t);//Optimal SubProblem 2(Ak+1...Aj)
			}
			
		}
		System.out.print(')');
		return;
	}
}
