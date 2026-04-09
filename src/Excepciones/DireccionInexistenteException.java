package Excepciones;

public class DireccionInexistenteException extends Exception {
	public DireccionInexistenteException(int id) {
		super("La direccion con ID " + id + " no existe.");
	}
}
