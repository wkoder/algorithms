#include <stdio.h>
#include <algorithm>

using namespace std;

#define M  1000000007

int pascal[10010][10010];
int n, k, a[10010], T;

int main() {
	pascal[0][0] = pascal[1][0] = pascal[1][1] = 1;
	for (int i = 2; i < 10005; i++) {
		pascal[i][0] = pascal[i][i] = 1;
		
		for (int j = 1; j < i; j++) {
			pascal[i][j] = (pascal[i-1][j-1] + pascal[i-1][j]) % M;
		}
	}
	
	scanf("%d\n", &T);
	
	for (int nCase = 1; nCase <= T; nCase++) {
		scanf("%d %d\n", &n, &k);
		
		for (int i = 0; i < n; i++) {
			scanf("%d", &a[i]);
		}
		
		sort(a, a + n);
		
	
		long long total = 0;
	
		for (int i = n-1; i >= k - 1; i--) {
			total = (total + (a[i] % M * (long long)pascal[i][k-1])) % M;
		}
		
		printf("Case #%d: %lld\n", nCase, total);
	}
}
