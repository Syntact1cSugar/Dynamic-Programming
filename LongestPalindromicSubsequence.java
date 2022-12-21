import java.lang.*;
import java.util.Scanner;
public class LongestPalindromicSubsequence
{
	public static void main(String[] args)
	{
		Scanner in = new Scanner(System.in);
		String X = in.next();
		int n = X.length();
		int[][] t = null;
		t = BOTTOM_UP_LPS(X,t);
		System.out.println(t[0][n-1]);
		PRINT_PALINDROME(X,0,n-1,t);
		in.close();
		return;
	}
	//Top Down Appraoch with Memoization
	public static int TOP_DOWN_LPS(String X,int i,int j,int[][] t)//t[i][j] gives TOP_DOWN_LPS(i,j)
	{
		if(i > j)return 0;//Even Palindromic
		if(t[i][j] != 0)//return Memoized value
			return t[i][j];
		if(i == j)
			t[i][j] = 1;//Odd Palindromic
		else if(X.charAt(i) == X.charAt(j))
			t[i][j] = 2 + TOP_DOWN_LPS(X,i+1,j-1,t);//Found one element belonging to the palindromic sequence
		//Pick only the optimum subproblem(Optimal Substructure)
		else 
			t[i][j] = Math.max(TOP_DOWN_LPS(X,i,j-1,t),TOP_DOWN_LPS(X,i+1,j,t));
		return t[i][j];
	}
	//Bottom Up Approach/Table Filling/Tabulation
	public static int[][] BOTTOM_UP_LPS(String X,int[][] t)
	{
		int n = X.length();
		t = new int[n+1][n+1];
		//t[i][j] gives the length of longest palindromic subsequence from Xi to Xj
		for(int i=n-1;i>=0;i--)
		{
			for(int j=i;j<n;j++)
			{
				if(i == j)
					t[i][j] = 1;
				else if(X.charAt(i) == X.charAt(j))
					t[i][j] = (i+1 <= j-1)? 2 + t[i+1][j-1] : 2;
				else
					t[i][j] = t[i][j-1] > t[i+1][j] ? t[i][j-1] : t[i+1][j];
			}
		}
		return t;
	}
	public static void PRINT_PALINDROME(String X,int i,int j,int[][] t)
	{
		if(i > j)
			return;//Even Palindromic
		else if(i == j)
			System.out.print(X.charAt(i));
		else if(X.charAt(i) == X.charAt(j))
		{
			System.out.print(X.charAt(i));
			PRINT_PALINDROME(X,i+1,j-1,t);
			System.out.print(X.charAt(j));
		}
		else if(t[i][j] == t[i+1][j])
			PRINT_PALINDROME(X,i+1,j,t);
		else if(t[i][j] == t[i][j-1])
			PRINT_PALINDROME(X,i,j-1,t);
		return;
	}
}
