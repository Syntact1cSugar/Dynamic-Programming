#include<iostream>

int canSum(int*A,int n,int target,int* t)
{
	if(target == 0)return true;
	if(t[target] != -1)return t[target];
	for(int i=0;i<n;i++)
	{
		if(A[i] < target && canSum(A,n,target-A[i],t) == 1)
		{
			t[target] = 1;
			return t[target];
		}
	}
	t[target] = 0;
	return t[target];
}
int main()
{
	int A[] = {2,4},n = 2,target = 99;
	int* t = new int[target+1];//t[target] = 1=>canSum valid,0=>canSum not valid,-1=>NC
	for(int i=0;i<=target;i++)
		t[i] = -1;
	std::cout << canSum(A,n,target,t) << std::endl;
	delete[] t;
	return 0;
}
