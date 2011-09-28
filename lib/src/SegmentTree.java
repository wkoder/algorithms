class SegmentTree {
	
	int N;
	int[] t;
	
	/**
	 * Crea un SegmentTree para que soporte operaciones en el rango [0, size], inclusivo.
	 * @param size		La posicion maxima que manejara el arbol.
	 */
	SegmentTree(int size) {
		N = 1;
		while (N < size)
			N <<= 1;
		t = new int[2 * N - 1];
	}
	
	/**
	 * Agrega, o elimina, n puntos en la posicion indicada. Si n < 0, entonces esta eliminando.
	 * Complejidad:		O(lg n)
	 * @param pos		Posicion del punto a agregar.
	 * @param n			Numero de puntos a agregar, o eliminar.
	 */
	void add(int pos, int n) {
		pos += N;
		while (pos > 0) {
			t[pos] += n;
			pos >>= 1;
		}
    }
	
	/**
	 * Cuenta el numero de puntos que estan dentro del rango [l, r], inclusivo.
	 * Complejidad:		O(lg l + lg r)
	 * @param l			Inicio del rango.
	 * @param r			Fin del rango.
	 * @return			Numero de puntos que contiene el rango especificado.
	 */
	int getCount(int l, int r) {
		l += N;
		r += N;
		if (r >= t.length)
			r = t.length - 1;
		int ans = 0;
		while (l <= r) {
			if (l == r) {
				ans += t[l];
				break;
			}
			if ((l & 1) == 1)
				ans += t[l++];
			if ((r & 1) == 0)
				ans += t[r--];
			l >>= 1;
			r >>= 1;
		}
		return ans;
	}
}
