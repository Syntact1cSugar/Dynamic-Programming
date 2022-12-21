import java.lang.*;
public class BinaryKnapMemoization
{
	//Top Down With Memoization
	public static void main(String[] args)
	{
		int[] val = {10,20,40,100,1,5,10,23,3,2,10,70,100,34,89,12,78,13,4,3,87,12,10,89,2,98,2,1,3,2};
		int[] w = {30,20,20,20,20,20,100,30,100,30,20,20,100,20,30,80,20,10,78,3,20,20,10,89,100,1,1,23,4,34};
		int W = 1000;
		int n = val.length;
		int[][] t = new int[n][W+1];
		System.out.println(KS(val,w,W,val.length-1,t));
		return;
	}
	public static int KS(int[] val,int[] w,int W,int i,int[][] t)
	{
		if(i < 0 || W == 0)return 0;
		else if(t[i][W] != 0)return t[i][W];
		else if(w[i] >  W)//Item cannot be included
			t[i][W] = KS(val,w,W,i-1,t);
		//Choice for item's inclusion
		else if(w[i] <= W)
			t[i][W] = Math.max(val[i]+KS(val,w,W-w[i],i-1,t),0+KS(val,w,W,i-1,t));//Max between selecting and not selecting
		return t[i][W];
	}
}
