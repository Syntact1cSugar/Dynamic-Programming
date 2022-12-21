import java.lang.*;
public class BinaryKnapSackBottomUp
{
	public static void main(String[] args)
	{

		int W = 6;
		           //i1,i2,i3,i4
		int[] p = {0,25,30,15,40};
		int[] wt =  {0,3,2,1,4};
		int n = 4;
		int[][] t = knapSack(n,wt,p,W);
		int[] solution = new int[n+1];
		System.out.println(t[n][W]);
		GetSolution(solution,n,W,wt,p,t);
		for(int i=1;i<=n;i++)
			System.out.print(solution[i]+"\t");
		t = null;
		return;
	}
	public static int[][] knapSack(int n,int[] wt,int[] p,int W)
	{
		int[][] t = new int[n+1][W+1];
		for(int i=0;i<n+1;i++)
		{
			for(int w=0;w<W+1;w++)
			{
				if(i == 0 || w == 0)
					t[i][w] = 0;
				else if(wt[i] > w)
					t[i][w] = t[i-1][w];
				else if(wt[i] <= w)
					t[i][w] = Math.max(p[i]+t[i-1][w-wt[i]],t[i-1][w]);
			}
		}
		return t;
	}
	public static void GetSolution(int[] solution,int n,int W,int[] wt,int[] p,int[][] t)
	{
		if(n == 0 || W == 0)
			return;
		if(wt[n] > W || t[n][W] == t[n-1][W])
		{
			solution[n] = 0;
			GetSolution(solution,n-1,W,wt,p,t);
		}
		else if(t[n][W] == p[n]+t[n-1][W-wt[n]])
		{
			solution[n] = 1;
			GetSolution(solution,n-1,W-wt[n],wt,p,t);
		}
		return;
	}

}
