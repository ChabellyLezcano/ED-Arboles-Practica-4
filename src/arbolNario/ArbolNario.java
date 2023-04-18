package arbolNario;

public class ArbolNario {

	private NodoArbolNario raiz;

	public ArbolNario(int dato) {
		raiz = new NodoArbolNario(dato);
	}

	public void insertar(ArbolNario arbol) {
		raiz.getHijos().insertar(arbol.raiz);
	}

	// ------------------------------------------------------------------------
	// TODO 2.2: Mostrar el arbol recorriendo en profundidad de forma RECURSIVA
	public void mostrarProfundidadRecursivo() {
		System.out.print("Profundidad Recursivo: ");
		mostrarProfundidadRecursivo(raiz);
		System.out.println();
	}

	private void mostrarProfundidadRecursivo(NodoArbolNario nodo) {
		System.out.print(nodo.getDato() + " ");
		ListaNodosArbolNario hijos = nodo.getHijos();
		for (int i = 0; i < hijos.getNumElementos(); i++) {
			NodoArbolNario hijo = hijos.getElemento(i);
			mostrarProfundidadRecursivo(hijo);
		}
	}

	// ------------------------------------------------------------------------
	// TODO 2.3: Mostrar el arbol recorriendo en profundidad de forma ITERATIVA
	public void mostrarProfundidadIterativo() {
		System.out.print("Profundidad Iterativo: ");
		PilaNodosArbolNario pila = new PilaNodosArbolNario();
		pila.apilar(raiz);
		while (!pila.vacia()) {
			NodoArbolNario nodo = pila.desapilar();
			System.out.print(nodo.getDato() + " ");
			ListaNodosArbolNario hijos = nodo.getHijos();
			PilaNodosArbolNario pilaHijos = new PilaNodosArbolNario();
			IteradorAdelanteListaNodosArbolNario iterador = hijos.getIteradorAdelante();
			while (iterador.hasNext()) {
				pilaHijos.apilar(iterador.next());
			}
			while (!pilaHijos.vacia()) {
				pila.apilar(pilaHijos.desapilar());
			}
		}
		System.out.println();
	}


	// ------------------------------------------------------------------------
	// TODO 2.4: Mostrar el arbol recorriendo en amplitud de forma ITERATIVA
	public void mostrarAmplitud() {
		System.out.print("Amplitud: ");
		ColaNodosArbolNario cola = new ColaNodosArbolNario();
		cola.encolar(raiz);
		while (!cola.vacia()) {
			NodoArbolNario nodo = cola.desencolar();
			System.out.print(nodo.getDato() + " ");
			ListaNodosArbolNario hijos = nodo.getHijos();
			IteradorAdelanteListaNodosArbolNario iterador = hijos.getIteradorAdelante();
			while (iterador.hasNext()) {
				cola.encolar(iterador.next());
			}
		}
		System.out.println();
	}
}
