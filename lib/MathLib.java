import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;

public class MathLib {

  static final long MOD = 1000000000L;
  static final int MAXVAR = 100;
  static final int MAXCONSTRAINT = 100;
  static final double EPS = 1e-7;
  static BigInteger[][] fib0 = {{BigInteger.ZERO, BigInteger.ONE}, {BigInteger.ONE, BigInteger.ONE}};
  static BigInteger[][] eye = {{BigInteger.ONE, BigInteger.ZERO}, {BigInteger.ZERO, BigInteger.ONE}};

  static long choose(long n, long k, long m) {
    if (n < k)
      return 0;
    if (k > n - k)
      k = n - k;
    long ans = 1;
    BigInteger M = new BigInteger(m + "");
    for (int i = 1; i <= k; i++) {
      ans *= (n - i + 1);
      ans %= m;
//			ans *= inverse[i];
      ans *= new BigInteger(i + "").modInverse(M).longValue(); // preprocess this
      ans %= m;
    }
    return ans;
  }

  static long chooseLucas(long n, long k, long m) {
    if (n < k || k < 0)
      return 0;
    if (n == k)
      return 1;
    long ans = (choose(n % m, k % m, m) * chooseLucas(n / m, k / m, m)) % m;
    return ans;
  }

  /**
   * Determina si un numero N es primo o no.
   * Complejidad:		O(n^0.5)
   *
   * @param N Numero a verificar.
   * @return Si N es primo o no.
   */
  static boolean isPrime(long N) {
    if (N > 2 && (N & 1) == 0)
      return false;
    long top = (long) Math.sqrt(N) + 1;
    for (long d = 3; d <= top; d += 2)
      if (N % d == 0)
        return false;
    return true;
  }

  /**
   * Determina el numero de factores k que tiene el factorial de n (n!).
   * Complejidad:		~ O(lg n)
   *
   * @param n El factorial de este numero es el que se factorizara.
   * @param k Es el unico factor que se busca en n!
   * @return El maximo valor de a tal que [ n! % k^a = 0 ]
   */
  static int factorialFactorsN(int n, int k) { // Numero de factores k de n!
    int w = 0;
    do
      w += n /= k;
    while (n > 0);
    return w;
  }

  /**
   * Calcula el valor de n^k, tomando en cuenta que n*n es constante.
   * Complejidad:		O(lg k)
   *
   * @param n Valor a elevar a la potencia k.
   * @param k Potencia de n.
   * @return n^k .
   */
  static long power(int n, int k) {
    long y = 1;
    long f = n;
    while (k > 0) {
      if ((k & 1) != 0)
        y *= f;
      k >>= 1;
      f *= f;
    }
    return y;
  }

  /**
   * Calcula los numeros primos menores o iguales a N.
   * Complejidad:		~ O(n lg n)
   *
   * @param N      Tope del calculo de primos.
   * @param primes Arreglo donde guardar los primos, primes.lenght >= N / log N .
   * @return Numero de primos encontrados <= N.
   */
  static int primes(int N, int[] primes) {
    int p = 1, k, i;
    if (N <= 1)
      return 0;
    primes[0] = 2;
    loop:
    for (i = 3; i <= N; i += 2) {
      for (k = 0; k < p && primes[k] * primes[k] <= i; k++)
        if (i % primes[k] == 0)
          continue loop;
      primes[p++] = i;
    }
    return p;
  }

  /**
   * Calcula los numeros primos menores o iguales a N, usando la criba de Eratostenes.
   * Complejidad:		O(n) + ~ O(n^0.5 lg  n)
   *
   * @param N      Tope del calculo de primos.
   * @param primes Arreglo donde guardar los primos, primes.lenght >= N / log N .
   * @return Numero de primos encontrados <= N.
   * @see        eratostenesSieve(int N)
   */
  static int sievePrimes(int N, int[] primes) {
    int p = 0;
    boolean[] isPrime = eratostenesSieve(N);
    for (int i = 0; i <= N; i++)
      if (isPrime[i])
        primes[p++] = i;
    return p;
  }

  /**
   * Calcula la criba de Eratostenes hasta N.
   * Complejidad:		~ O(n^0.5 lg n)
   *
   * @param N El tope del calculo de la criba de Eratostenes.
   * @return Un arreglo cuya posicion k indica que el entero k es primo.
   */
  static boolean[] eratostenesSieve(int N) {
    boolean[] isPrime = new boolean[N + 1];
    Arrays.fill(isPrime, true);
    isPrime[0] = isPrime[1] = false;
    int k, p, top = (int) Math.sqrt(N) + 1;
    for (p = 0; p <= top; p++)
      if (isPrime[p])
        for (k = p + p; k <= N; k += p)
          isPrime[k] = false;
    return isPrime;
  }

  /**
   * Determina los factores primos de N.
   * Complejidad:		~ O(n^0.5)
   *
   * @param N Numero a factorizar.
   * @return Factores primos de N, incluye repeticiones.
   */
  static long[] factors(long N) {
    ArrayList<Long> facts = new ArrayList<Long>();
    long d, top;
    int i;
    while ((N & 1) == 0) {
      N >>= 1;
      facts.add(2L);
    }
    d = 3;
    top = (long) Math.sqrt(N) + 1;
    while (d <= top) {
      if (N % d == 0) {
        facts.add(d);
        N /= d;
        top = (long) Math.sqrt(N) + 1;
      } else
        d += 2;
    }
    if (N > 1)
      facts.add(N);
    long[] ans = new long[facts.size()];
    for (i = 0; i < ans.length; i++)
      ans[i] = facts.get(i);
    return ans;
  }

  /**
   * Determina los factores primos de N y su exponente.
   * Complejidad		~ O(n^0.5)
   *
   * @param N El valor a factorizar.
   * @return Los factores primos de N en la posicion 0, y en la posicion 1 su exponente.
   */
  static int[][] powerFactors(int N) {
    ArrayList<Integer> facts = new ArrayList<Integer>();
    int d, top, p;
    int i;
    if ((N & 1) == 0) {
      facts.add(2);
      p = 0;
      while ((N & 1) == 0) {
        N >>= 1;
        p++;
      }
      facts.add(p);
    }
    d = 3;
    top = (int) Math.sqrt(N) + 1;
    while (d <= top) {
      if (N % d == 0) {
        facts.add(d);
        p = 0;
        while (N % d == 0) {
          N /= d;
          p++;
        }
        facts.add(p);
        top = (int) Math.sqrt(N) + 1;
      }
      d += 2;
    }
    if (N > 1) {
      facts.add(N);
      facts.add(1);
    }
    int[][] ans = new int[facts.size() / 2][2];
    for (i = 0; i < ans.length; i++) {
      ans[i][0] = facts.get(i * 2);
      ans[i][1] = facts.get(i * 2 + 1);
    }
    return ans;
  }

  /**
   * Calcula los factores primos de N, sin repetir.
   * Complejidad		~ O(n^0.5)
   *
   * @param N Numero a factorizar.
   * @return Factores primos unicos de N.
   */
  static int[] uniqueFactors(int N) {
    ArrayList<Integer> facts = new ArrayList<Integer>();
    int d, top;
    int i;
    if ((N & 1) == 0) {
      facts.add(2);
      while ((N & 1) == 0)
        N >>= 1;
    }
    d = 3;
    top = (int) Math.sqrt(N) + 1;
    while (d <= top) {
      if (N % d == 0) {
        facts.add(d);
        while (N % d == 0)
          N /= d;
        top = (int) Math.sqrt(N) + 1;
      }
      d += 2;
    }
    if (N > 1)
      facts.add(N);
    int[] ans = new int[facts.size()];
    for (i = 0; i < ans.length; i++)
      ans[i] = facts.get(i);
    return ans;
  }

  /**
   * Calcula la suma del GCD de cada par de numeros diferentes de 1 a N.
   *
   * @param N Limite del calculo
   * @return Suma de los GCDs
   */
  static long sumOfGCD(int N) {
    int[] phi = new int[N + 1];
    long sum = 0;
    int i, j;
    for (i = 1; i <= N; i++)
      phi[i] = phi(i);
    for (i = 1; i <= N; i++)
      for (j = 2; j * i <= N; j++)
        sum += phi[j] * i;
    return sum;
  }

  /**
   * Funcion phi de Leonard Euler, cuyo valor retorna el numero de enteros menores a N y relativamente primos a N.
   * Complejidad:		~ O(n^0.5)
   *
   * @param n Valor a evaluar.
   * @return Numero de valores menores a N relativamente primos a N.
   * @see      uniqueFactors(N)
   */
  static int phi(int N) {
    int[] f = uniqueFactors(N);
    for (int i = 0; i < f.length; i++) {
      N /= f[i];
      N *= f[i] - 1;
    }
    return N;
  }

  /**
   * Calcula el numero de factores, no necesariamente primos, de N.
   * Complejidad:		~ O(n^0.5)
   *
   * @param N Valor a factorizar.
   * @return Numero de distintos factores que tiene N.
   * @see        powerFactors(N)
   */
  static int numOfDivisors(int N) {
    int[][] f = powerFactors(N);
    int i, div = 1;
    for (i = 0; i < f.length; i++)
      div *= f[i][1] + 1;
    return div;
  }

  /**
   * Calcula la suma de todos los divisores de N.
   * Complejidad:		~ O(n^0.5) + O(m lg k)
   *
   * @param N Valor a factorizar.
   * @return Suma de los divisores de N.
   * @see        power(n, k)
   */
  static int sumOfDivisors(int N) {
    int[][] f = powerFactors(N);
    int i, div = 1;
    for (i = 0; i < f.length; i++)
      div *= (power(f[i][0], f[i][1] + 1) - 1) / (f[i][0] - 1);
    return div;
  }

  /**
   * Calcula la multiplicacion de todos los divisores de N.
   * Complejidad:		~ O(n^0.5)
   *
   * @param N Valor a factorizar.
   * @return Multiplicacion de los divisores de N.
   * @see        power(n, k)
   */
  static long multOfDivisors(int N) {
    return power(N, numOfDivisors(N) / 2);
  }

  /**
   * Calcula el numero de divisores que hay para cada valor en el rango [1, x].
   * Complejidad:		O(n^0.5)
   *
   * @param x Tope del rango.
   * @return Sumatoria de numOfDivisors(i) para 0 < i < x+1 .
   */
  static long sumOfNumOfDivisorsTo(long x) {
    if (x == 0)
      return 0;
    long d = 1, ans = 0;
    while (d * d <= x) {
      ans += (x - d * d) / d * 2 + 1;
      d++;
    }
    return ans;
  }

  /**
   * Calcula el maximo comun divisor.
   * Complejidad:		O(lg a)
   *
   * @param a Entero a.
   * @param b Entero b.
   * @return Maximo comun divisor de a y b.
   */
  static long gcd(long a, long b) {
    long t;
    while (b != 0) {
      t = b;
      b = a % b;
      a = t;
    }
    return a;
  }

  /**
   * Calcula el minimo comun multiplo entre dos numeros.
   * Complejidad:		O(lg a)
   *
   * @param a Entero a.
   * @param b Entero b.
   * @return Minimo comun multiplo de a y b.
   * @see        gcd(a, b)
   */
  static long lcm(long a, long b) {
    return a * b / gcd(a, b);
  }

  static BigInteger[][] fastFibo(int n) {
    if (n == 0)
      return eye;
    BigInteger[][] fib1, fib2;
    BigInteger[][] ans = new BigInteger[2][2];
    if (n % 2 == 0) {
      fib2 = fib1 = fastFibo(n / 2);
    } else {
      fib1 = fastFibo(n - 1);
      fib2 = fib0;
    }
    ans[0][0] = fib1[0][0].multiply(fib2[0][0]).add(fib1[0][1].multiply(fib2[1][0]));
    ans[0][1] = fib1[0][0].multiply(fib2[0][1]).add(fib1[0][1].multiply(fib2[1][1]));
    ans[1][0] = fib1[1][0].multiply(fib2[0][0]).add(fib1[1][1].multiply(fib2[1][0]));
    ans[1][1] = fib1[1][0].multiply(fib2[0][1]).add(fib1[1][1].multiply(fib2[1][1]));
    return ans;
  }

  /**
   * Determina el valor n de la serie de Fibonacci.
   * Complejidad:		O(lg n)
   *
   * @param n Valor de la serie a calcular.
   * @return El fibonacci correspondiente a la posicion N en la serie.
   * @see        fastFibo(N)
   */
  static BigInteger fibonacci(int N) {
    return fastFibo(N)[1][0];
  }

  /**
   * Determina el area de un poligono en base al Teorema de Pick.
   * Complejidad:			O(1)
   *
   * @param interior Numero de puntos latices dentro del poligono.
   * @param boundary Numero de puntos latices en las aristas del poligono.
   * @return Area del poligono.
   */
  static double areaLattice(int interior, int boundary) {
    return interior + boundary / 2.0 - 1;
  }

  /**
   * Determina el numero de caras que posee un grafo plano.
   * Complejidad:			O(1)
   *
   * @param vertices Numero de vertices que posee el grafo.
   * @param edges    Numero de aristas que tiene el grafo.
   * @return Numero de caras que tiene el grafo.
   */
  static int eulerFaces(int vertices, int edges) {
    return 2 - vertices + edges;
  }

  /**
   * Determina el numero de permutaciones de k elementos que se pueden tomar de un total de n.
   * Complejidad:		O(k)
   *
   * @param n Numero total de elementos.
   * @param k Numero de elementos a tomar.
   * @return Numero de permutaciones k de n.
   */
  static long nPermuteK(int n, int k) {
    long res = 1;
    for (int i = 0; i < k; i++)
      res = res * (n - i);
    return res;
  }

  /**
   * Determina el numero de combinaciones de k elementos que se pueden tomar de un total de n.
   * Complejidad:		O(k)
   *
   * @param n Numero total de elementos.
   * @param k Numero de elementos a tomar.
   * @return Numero de combinaciones k de n.
   */
  static long nChooseK(int n, int k) {
    if (k > n - k)
      k = n - k;
    long res = 1;
    for (int i = 0; i < k; i++)
      res = res * (n - i) / (i + 1);
    return res;
  }

  /**
   * Determina el numero de combinaciones con repeticiones de k elementos de un total de n.
   * Complejidad:		O(k)
   *
   * @param n Numero total de elementos.
   * @param k Numero de elementos a tomar.
   * @return Numero de combinaciones con repeticiones k de n.
   * @see        nChooseK(n, k)
   */
  static long nChooseKRep(int n, int k) {
    return nChooseK(n + k - 1, k);
  }

  /**
   * Determina el numero de combinaciones de k elementos que se pueden tomar de un total de n.
   * Complejidad:		O(nk)
   *
   * @param n Numero total de elementos.
   * @param k Numero de elementos a tomar.
   * @return Numero de combinaciones k de n.
   */
  static long nChooseKDP(int n, int k) {
    long[][] choose = new long[n + 1][k + 1];
    int i, j;
    for (i = 0; i <= n; i++)
      choose[i][0] = choose[i][i] = 1;
    for (i = 1; i <= n; i++)
      for (j = 1; j < i; j++)
        choose[i][j] = choose[i - 1][j - 1] + choose[i - 1][j];
    return choose[n][k];
  }

  /**
   * Determina el numero de combinaciones de k elementos que se pueden tomar de un total de n.
   * Solo determina hasta LIM combinaciones.
   * Complejidad:		O(k)
   *
   * @param n   Numero total de elementos.
   * @param k   Numero de elementos a tomar.
   * @param LIM Limite de combinaciones a calcular.
   * @return Numero de combinaciones k de n, si este valor es mayor a LIM, regresa LIM.
   */
  static long nChooseK_Limit(long n, long k, long LIM) {
    long res = 1;
    long d = 1;
    while (n > k) {
      res *= n;
      res /= d;
      n--;
      d++;
      if (res > LIM)
        return LIM;
    }
    return res;
  }

  /**
   * Calcula el numero de particiones enteras que pueden hacer de n elementos, en partes no mayores a k elementos.
   * Complejidad:		O(nk)
   *
   * @param n Numero total de elementos.
   * @param k Maximo numero de elementos en una particion.
   * @return Numero de particiones de n elementos.
   */
  static long integerPartitions(int n, int k) {
    long[][] part = new long[n + 1][k + 1];
    int i, j;
    part[1][1] = 1;
    for (i = 2; i <= n; i++)
      for (j = 1; j <= i && j <= k; j++)
        part[i][j] = part[i - j][j] + part[i][j - 1];
    return part[n][k];
  }

  /**
   * Calcula el valor N Catalan. Una de sus aplicaciones es determinar el numero de
   * distintos arboles binarios con N hojas.
   * Complejidad:		O(n)
   *
   * @param N Valor de Catalan a calcular.
   * @return Valor N de la serie Catalan.
   * @see        nChooseK(n, k)
   */
  static long catalan(int N) {
    return nChooseK(2 * N, N) / (N + 1);
  }

  /**
   * Calcula el valor N Catalan. Una de sus aplicaciones es determinar el numero de
   * distintos arboles binarios con N hojas.
   * Complejidad:		O(n)
   *
   * @param N Valor de Catalan a calcular.
   * @return Valor N de la serie Catalan.
   */
  static long catalanDP(int N) {
    int i;
    long[] catalan = new long[N + 1];
    catalan[0] = 1;
    for (i = 0; i < N; i++)
      catalan[i + 1] = 2 * (2 * i + 1) * catalan[i] / (i + 2);
    return catalan[N];
  }

  /**
   * Calcula el area de un triangulo en base a la medida de sus lados.
   * Complejidad: O(1)
   *
   * @param a Lado
   * @param b Lado
   * @param c Lado
   * @return Area del triangulo
   */
  static double areaHeron(int a, int b, int c) {
    double s = (a + b + c) / 2.0;
    return Math.sqrt(s * (s - a) * (s - b) * (s - c));
  }

  /**
   * Calcula el siguiente conjunto con el mismo numero de elementos en orden lexicografico.
   * En otras palabras, regresa el siguiente entero con el mismo numero de bits encendidos.
   * Complejidad:		O(lg n)
   *
   * @param x Conjunto actual.
   * @return Siguiente conjunto.
   */
  static int next(int x) {
    int y = x + Integer.lowestOneBit(x);
    x = x & ~y;
    while ((x & 1) == 0)
      x >>= 1;
    x >>= 1;
    return y | x;
  }

  /**
   * Calcula la fraccion continua del numero racional m/n.
   * Complejidad:		O(lg n)
   *
   * @param m Dividendo
   * @param n Divisor
   * @return Fraccion continua de m/n
   */
  static int[] continuedFractionOfRationals(int m, int n) {
    ArrayList<Integer> arr = new ArrayList<Integer>();
    int i;
    while (n != 0) {
      arr.add(m / n);
      m %= n;
      m ^= n ^= m ^= n;
    }
    int[] ans = new int[arr.size()];
    for (i = 0; i < ans.length; i++)
      ans[i] = arr.get(i);
    return ans;
  }

  /**
   * Dados a y b (no negativos), calcula d = gcd(a, b) asi como x e y, tal que d = ax + by .
   * Complejidad:		O(lg a)
   *
   * @param a Entero a
   * @param b Entero b
   * @return La tripleta (d, x, y)
   */
  static Triple egcd(int a, int b) {
    if (b == 0)
      return new Triple(a, 1, 0);
    Triple q = egcd(b, a % b);
    return new Triple(q.d, q.y, q.x - a / b * q.y);
  }

  /**
   * Resuelve la ecuacion ax = b (mod n) para x.
   * Complejidad:		O(n) + O(lg a)
   *
   * @param a a
   * @param b b
   * @param n n
   * @return Soluciones posibles para x, menores a n y ordenadas.
   * @see        egcd(a, b)
   */
  static int[] modularSolver(int a, int b, int n) {
    int i, x;
    n = Math.abs(n);
    Triple t = egcd(a, n);
    if (b % t.d != 0)
      return new int[0];
    int[] r = new int[t.d];
    x = (b / t.d * t.x + n) % n;
    for (i = 0; i < t.d; i++)
      r[i] = ((x + i * n / t.d) % n);
    return r;
  }

  /**
   * Resuelve ecuaciones enteras de la forma ax + by = c para x e y.
   * Complejidad:		O(lg a)
   *
   * @param a a
   * @param b b
   * @param c c
   * @return Respuesta (en .x e .y) y bandera (en .d). Si la bandera es cero no hay solucion.
   * De otra manera, hay infinitas de la forma:
   * x = t.x + k * b / t.d;
   * y = t.y - k * a / t.d;
   * Donde t es la tripleta devuelta, y k cualquier entero.
   * @see        egcd(a, b)
   */
  static Triple linearDiophSolver(int a, int b, int c) {
    Triple t = egcd(a, b);
    if (c % t.d != 0)
      return new Triple(0, 0, 0);
    t.x *= c / t.d;
    t.y *= c / t.d;
    return t;
  }

  /**
   * Resuelve ax = 1 (mod n)
   * Complejidad:		O(lg a)
   *
   * @param a a
   * @param n n
   * @return La solucion de x en ax = 1 (mod n), 0 si no hay solucion.
   */
  static int modularInverse(int a, int n) {
    Triple t = egcd(a, n);
    if (t.d > 1)
      return 0;
    return (t.x + n) % n;
  }

  /**
   * Maximiza CX sujeto a AX <= B con X >= 0, usando el metodo Simplex.
   *
   * @param variables   Numero de variables
   * @param constraints Numero de restricciones
   * @param C           Vector C[MAXVAR]
   * @param A           Vector A[MAXCONSTRAINT][MAXVAR]
   * @param B           Vector B[MAXCONSTRAINT]
   * @return Valor de CX
   */
  static double simplex(int variables, int constraints, double[] C, double[][] A, double[] B) {
    int i, j;
    double[] X = new double[MAXVAR]; // solution
    double[][] tableau = new double[MAXCONSTRAINT + 1][MAXVAR + MAXCONSTRAINT + 2]; // temporal
    // Initialize tablueau
    int rows = constraints; //Note rows and cols doesn't count the outermost row/col for convenience
    int cols = variables + constraints + 1;
    for (i = 0; i < constraints; i++) {
      for (j = 0; j < variables; j++)
        tableau[i][j] = A[i][j];
      for (j = 0; j < constraints; j++)
        tableau[i][j + variables] = i == j ? 1 : 0;
      tableau[i][variables + constraints] = 0;
      tableau[i][variables + constraints + 1] = B[i];
    }
    for (j = 0; j < variables; j++) {
      tableau[constraints][j] = C[j] == 0 ? 0 : -C[j];
      tableau[constraints][j + variables] = 0;
    }
    tableau[constraints][variables + constraints] = 1;
    tableau[constraints][variables + constraints + 1] = 0;
    // Pivot until done
    while (true) {
      // Select pivot column
      int pivot_col = 0;
      for (i = 1; i < cols; i++)
        if (tableau[rows][i] < tableau[rows][pivot_col])
          pivot_col = i;
      //Check for finishing condition
      if (tableau[rows][pivot_col] >= 0)
        break;
      //Select pivot row
      int pivot_row = 0;
      for (i = 1; i < rows; i++)
        if (tableau[i][pivot_col] >= EPS)
          if (tableau[pivot_row][pivot_col] < EPS || tableau[i][cols] / tableau[i][pivot_col] < tableau[pivot_row][cols] / tableau[pivot_row][pivot_col])
            pivot_row = i;
      // Perform pivot
      for (i = 0; i <= rows; i++) {
        if (i == pivot_row)
          continue;
        double ratio = tableau[i][pivot_col] / tableau[pivot_row][pivot_col];
        for (j = 0; j <= cols; j++)
          tableau[i][j] -= ratio * tableau[pivot_row][j];
        tableau[i][pivot_col] = 0; //should already be zero, just to keep things precise
      }
      // Normalize table
      for (i = 0; i <= rows; i++) {
        double max = 0;
        for (j = 0; j <= cols; j++)
          max = Math.max(max, Math.abs(tableau[i][j]));
        for (j = 0; j <= cols; j++)
          tableau[i][j] /= max;
      }
    }
    // Extract X vector
    for (i = 0; i < variables; i++) {
      boolean found = false;
      for (j = 0; j < constraints; j++) {
        if (tableau[j][i] != 0) {
          if (!found) {
            X[i] = tableau[j][cols] / tableau[j][i];
            found = true;
          } else {
            X[i] = 0;
            break;
          }
        }
      }
    }
    return tableau[rows][cols] / tableau[rows][cols - 1];
  }

  static int congruenceSolver(int[] a, int[] mod, int N) {
    int m = mod[0], x = a[0];
    Triple t;
    for (int i = 1; i < N; i++) {
      t = egcd(m, mod[i]);
      x = t.x * m * a[i] + t.y * mod[i] * x;
      m *= mod[i];
      x %= m;
    }
    return x;
  }

  static boolean linearSolver(double[][] eqs, int N, int M) {
    int i, j, k;
    double mul;
    for (i = 0; i < N; i++) {
      if (!fetch(eqs, i, N, M))
        return false;
      for (j = 0; j < N; j++)
        if (i != j) {
          mul = eqs[j][i];
          for (k = i; k < M; k++)
            eqs[j][k] -= mul * eqs[i][k];
        }
    }
    return true;
  }

  static boolean fetch(double[][] eqs, int row, int N, int M) {
    double[] tmp;
    double mul;
    for (int i = row; i < N; i++) {
      if (Math.abs(eqs[i][row]) < EPS)
        continue;
      tmp = eqs[row];
      eqs[row] = eqs[i];
      eqs[i] = tmp;
      mul = 1.0 / eqs[row][row];
      for (int j = row; j < eqs[row].length; j++)
        eqs[row][j] *= mul;
      return true;
    }
    return false;
  }
	
	/* TODO:
		Pell's equations
		
	*/
}

class Triple {
  int d, x, y;

  Triple(int _d, int _x, int _y) {
    d = _d;
    x = _x;
    y = _y;
  }
}
