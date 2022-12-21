import java.lang.*;
import java.util.Scanner;
public class MinimumDeletionToMakeStringPalindromic
{
	public static void main(String[] args)
	{
		Scanner in = new Scanner(System.in);
		String X = in.next();
		int n = X.length();
		int[][] t = null;
		t = BOTTOM_UP_MinDelete(X,n,t);
		PRINT_PALINDROMIC_SEQUENCE(X,0,n-1,t);
		in.close();
		t = null;
		return;
	}
	public static int TOP_DOWN_MinDelete(String X,int i,int j,int[][] t)
	{
		if(i >= j)
			return 0;
		if(t[i][j] != 0)return t[i][j];
		else if(X.charAt(i) == X.charAt(j))
			t[i][j] = TOP_DOWN_MinDelete(X,i+1,j-1,t);
		else
			t[i][j] = Math.min(TOP_DOWN_MinDelete(X,i,j-1,t),TOP_DOWN_MinDelete(X,i+1,j,t))+1;
		return t[i][j];
	}
	public static int[][] BOTTOM_UP_MinDelete(String X,int n,int[][] t)
	{
		t = new int[n][n];
		for(int i=n-1;i>=0;i--)
		{
			for(int j=i;j<n;j++)
			{
				if(i == j)
					t[i][j] = 0;
				else if(X.charAt(i) == X.charAt(j))
					t[i][j] = i+1 < j-1 ? t[i+1][j-1] : 0;
				else 
					t[i][j] = 1 + Math.min(t[i+1][j],t[i][j-1]);
			}
		}
		return t;
	}
	public static void PRINT_PALINDROMIC_SEQUENCE(String X,int i,int j,int[][] t)
	{
		if(i > j)
			return;
		if(i == j)
		{
			System.out.print(X.charAt(i));
			return;
		}
		else if(X.charAt(i) == X.charAt(j))
		{
			System.out.print(X.charAt(i));
			PRINT_PALINDROMIC_SEQUENCE(X,i+1,j-1,t);
			System.out.print(X.charAt(j));
		}
		else if(t[i][j]-1 == t[i+1][j])
			PRINT_PALINDROMIC_SEQUENCE(X,i+1,j,t);
		else 
			PRINT_PALINDROMIC_SEQUENCE(X,i,j-1,t);
		return;
	}
}
