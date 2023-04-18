package arbolBusqueda;

public class ArbolBinarioBusqueda {

	private NodoArbol raiz;

	public ArbolBinarioBusqueda() {
		raiz = null;
	}

	// Muestra los elementos del arbol binario en orden central ---------------
	public void mostrar() {
		this.mostrar(raiz, "  ");
	}

	private void mostrar(NodoArbol nodo, String espacios) {
		if (nodo != null) {
			this.mostrar(nodo.getIzquierdo(), espacios + "    ");
			System.out.print(espacios);
			nodo.getDato().mostrar();
			this.mostrar(nodo.getDerecho(), espacios + "    ");
		}
	}

	// Inserta un elemento con una cierta clave -------------------------------
	public void insertar(Alumno dato) {
		raiz = this.insertarRec(raiz, dato);
	}

	private NodoArbol insertarRec(NodoArbol nodo, Alumno dato) {
		if (nodo == null) {     // Crear nuevo nodo
			nodo = new NodoArbol(dato);
		} else if (dato.getMatricula() < nodo.getDato().getMatricula()) {    // Subárbol izquierdo
			NodoArbol nuevoIzq = this.insertarRec(nodo.getIzquierdo(), dato);
			nodo.setIzquierdo(nuevoIzq);
		} else if (dato.getMatricula() > nodo.getDato().getMatricula()) {    // Subárbol derecho
			NodoArbol nuevoDer = this.insertarRec(nodo.getDerecho(), dato);
			nodo.setDerecho(nuevoDer);
		} else {      // Clave repetida
			System.out.println("Error. El alumno con matrícula " + dato.getMatricula() + " ya existe");
		}
		return nodo;    // Devolver la nueva raíz del subárbol
	}


	// ------------------------------------------------------------------------
	// TODO 3.2: Devuelve el numero de nodos del arbol de forma RECURSIVA
	public int getNumElementos() {
		return getNumElementosRec(raiz);
	}

	private int getNumElementosRec(NodoArbol nodo) {
		if (nodo == null) {
			return 0;
		} else {
			int numIzq = getNumElementosRec(nodo.getIzquierdo());
			int numDer = getNumElementosRec(nodo.getDerecho());
			return 1 + numIzq + numDer;
		}
	}


	// ------------------------------------------------------------------------
	// TODO 3.3: Devuelve el numero de nodos del arbol con clave
	// menor a una clave dada de forma RECURSIVA
	public int getNumMenores(int clave) {
		return getNumMenoresRec(raiz, clave);
	}

	private int getNumMenoresRec(NodoArbol nodo, int clave) {
		if (nodo == null) {
			return 0;
		} else if (nodo.getDato().getMatricula() < clave) {
			return 1 + getNumMenoresRec(nodo.getDerecho(), clave) + getNumMenoresRec(nodo.getIzquierdo(), clave);
		} else {
			return getNumMenoresRec(nodo.getIzquierdo(), clave);
		}
	}


	// ------------------------------------------------------------------------
	// TODO 3.4: Devuelve el elemento con la menor clave de forma RECURSIVA
	public Alumno getMenorElemento() {
		NodoArbol nodoActual = raiz;
		while (nodoActual.getIzquierdo() != null) {
			nodoActual = nodoActual.getIzquierdo();
		}
		return nodoActual.getDato();
	}


	// ------------------------------------------------------------------------
	// TODO 3.5: Devuelve el número de nodos del árbol con clave mayor que
	// claveMinimo y menor que claveMaximo
	public int getNumIntermedios(int claveMinimo, int claveMaximo) {
		return getNumIntermediosRec(raiz, claveMinimo, claveMaximo);
	}

	private int getNumIntermediosRec(NodoArbol nodo, int claveMinimo, int claveMaximo) {
		if (nodo == null) {
			return 0;
		}
		if (claveMinimo >= claveMaximo) { // caso base 1
			return 0;
		}
		if (nodo.getDato().getMatricula() < claveMinimo) {
			return getNumIntermediosRec(nodo.getDerecho(), claveMinimo, claveMaximo);
		}
		if (nodo.getDato().getMatricula() >= claveMaximo) { // caso base 2
			return getNumIntermediosRec(nodo.getIzquierdo(), claveMinimo, claveMaximo);
		}
		int numIzq = getNumIntermediosRec(nodo.getIzquierdo(), claveMinimo, claveMaximo);
		int numDer = getNumIntermediosRec(nodo.getDerecho(), claveMinimo, claveMaximo);
		return 1 + numIzq + numDer;
	}

}
