import java.lang.*;
import java.util.Scanner;


public class GridTraveller
{
	public static void main(String[] args)
	{
		Scanner in = new Scanner(System.in);
		int m,n;
		m = in.nextInt();
		n = in.nextInt();
		int[][] t = new int[m+1][n+1];
		System.out.println(GT_BottomUp(m,n,t));
		t = null;
		in.close();
		return;
	}
	public static int GT_TopDown(int m,int n,int[][] t)//Top Down with memoization
	{
		if(m == 1 || n == 1)return 1;
		if(t[m][n] != 0)return t[m][n];
		t[m][n] = GT_TopDown(m-1,n,t) + GT_TopDown(m,n-1,t);
		return t[m][n];
	}
	public static int GT_BottomUp(int m,int n,int[][] t)//Bottom Up (Table Filling)
	{
		for(int i=1;i<=m;i++)
		{
			for(int j=1;j<=n;j++)
			{
				if(i == 1 && j == 1)continue;
				if(i == 1 || j == 1)
					t[i][j] = 1;
				else
					t[i][j] = t[i-1][j] + t[i][j-1];
			}
		}
		return t[m][n];
	}
}
