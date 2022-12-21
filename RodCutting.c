#include<stdio.h>
#include<limits.h>


int RodCutting(int n,int* p,int* t)
{
	if(n == 0)return 0;
	if(n == 1)return p[n];
	if(t[n] != INT_MIN)return t[n];
	for(int i=1;i<=n;i++)
	{
		int currentProfit = p[i] + RodCutting(n-i,p,t);
		t[n] = currentProfit > t[n]? currentProfit : t[n];
	}
	return t[n];
}

int main()
{
	int n = 10;
	int p[] = {0,1,5,8,9,10,17,17,20,24,30};
	int t[n+1];
	t[0] = 0;
	for(int i=1;i<=n+1;i++)
		t[i] = INT_MIN;
	printf("%d\n",RodCutting(n,p,t));
	return 0;
}
