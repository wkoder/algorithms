public class OtherLib {

  /**
   * Determina el valor de la posicion k del arreglo array despues de ser ordenado.
   *
   * @param array Arreglo de elementos.
   * @param start Inicio del ordenamiento.
   * @param end   Fin del ordenamiento.
   * @param k     Posicion k a determinar.
   * @return Valor de la posicion k.
   */
  static int getK(int[] array, int start, int end, int k) {
    int p = partition(array, start, end);
    if (p == k)
      return array[k];
    else if (p < k)
      return getK(array, p + 1, end, k);
    return getK(array, start, p - 1, k);
  }

  static void swap(int[] array, int a, int b) {
    int tmp = array[a];
    array[a] = array[b];
    array[b] = tmp;
  }

  static int partition(int[] array, int start, int end) {
    int index = (start + end) / 2;
    int pivot = array[index];
    swap(array, index, end);
    for (int x = index = start; x < end; x++)
      if (array[x] <= pivot)
        swap(array, index++, x);
    swap(array, index, end);
    return index;
  }

  static int[] periods(String s) {
    int N = s.length(), i, b;
    int[] B = new int[N + 1];
    for (i = 0; i < N; i++) {
      b = B[i];
      while (b > 0 && s.charAt(i) != s.charAt(b - 1))
        b = B[b + 1];
      if (s.charAt(i) == s.charAt(b - 1))
        B[i + 1] = b + 1;
      else
        B[i + 1] = 0;
    }
    return B;
  }

  static boolean nextPermutation(int[] arr, int first, int last) {
    int i, ii, j;
    int t;
    if (first == last || first + 1 == last)
      return false;
    i = last - 1;
    for (; ; ) {
      ii = i--;
      if (arr[i] < arr[ii]) {
        j = last;
        while (arr[i] >= arr[--j]) ;
        t = arr[i]; // swap
        arr[i] = arr[j];
        arr[j] = t;
        last--; // reverse
        for (; ; ) {
          if (ii >= last)
            break;
          t = arr[ii];
          arr[ii] = arr[last];
          arr[last] = t;
          ii++;
          last--;
        }
        return true;
      }
      if (i == first) {
        last--; // reverse
        for (; ; ) {
          if (first >= last)
            break;
          t = arr[first];
          arr[first] = arr[last];
          arr[last] = t;
          first++;
          last--;
        }
        return false;
      }
    }
  }
}
